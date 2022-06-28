package com.example.co227_project.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

@Component
@Slf4j
@Order(1)
public class RequestResponseLoggers implements Filter {

    private String requestURI;
    private String requestMethod;
    private String requestBody;
    private int responseStatus;
    private String responesBody;

    private final LogMsgRepository logMsgRepository;

    public RequestResponseLoggers(LogMsgRepository logMsgRepository) {
        this.logMsgRepository = logMsgRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        MyCustomHttpRequestWrapper  requestWrapper = new MyCustomHttpRequestWrapper ((HttpServletRequest) servletRequest);

        requestURI = requestWrapper.getRequestURI();
        requestMethod = requestWrapper.getMethod();
        requestBody = new String(requestWrapper.getByteArray()) ;

        log.info("Request URI: {}",requestURI);
        log.info("Request Method: {}",requestMethod);
        log.info("Request Body: {}", requestBody);

        MyCustomHttpResponseWrapper responseWrapper = new MyCustomHttpResponseWrapper((HttpServletResponse)servletResponse);

        filterChain.doFilter(requestWrapper , responseWrapper);

        responseStatus = responseWrapper.getStatus();
        responesBody =  new String(responseWrapper.getBaos().toByteArray());

        log.info("Response status - {}", responseStatus);
        log.info("Response Body - {}", responesBody);

        LogMsg logMsg = new LogMsg();
        logMsg.setRequestBody(requestBody);
        logMsg.setRequestMethod(requestMethod);
        logMsg.setRequestURI(requestURI);
        logMsg.setResponseBody(responesBody);
        logMsg.setResponseStatus(String.valueOf(responseStatus));

        this.logMsgRepository.save(logMsg);
    }

    private class MyCustomHttpRequestWrapper extends HttpServletRequestWrapper {

        private byte[] byteArray;
        public MyCustomHttpRequestWrapper(HttpServletRequest request) {

            super(request);
            try {
                byteArray = IOUtils.toByteArray(request.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException("Issue while reading the request stream");
            }
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {

            return new MyDelegatingServletInputStream(new ByteArrayInputStream(byteArray));

        }

        public byte[] getByteArray() {
            return byteArray;
        }
    }

    private class MyCustomHttpResponseWrapper extends HttpServletResponseWrapper {

        private ByteArrayOutputStream baos =new ByteArrayOutputStream();

        private PrintStream printStream = new PrintStream(baos);

        public ByteArrayOutputStream getBaos() {
            return baos;
        }

        public MyCustomHttpResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new MyDelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(),printStream)) ;

        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(new TeeOutputStream(super.getOutputStream(),printStream));
        }
    }
}
