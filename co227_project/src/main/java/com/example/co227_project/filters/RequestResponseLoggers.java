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


    private String clientTime;
    private String server_time;
    private String ip;
    private String type;
    private String serverity = new String("");
    private String keyword;
    private String description;
    private String generated_from;
    private String remarks;
    private Object requestObject;
    //private String requestId;

    private final LogMsgRepository logMsgRepository;

    public RequestResponseLoggers(LogMsgRepository logMsgRepository) {
        this.logMsgRepository = logMsgRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        MyCustomHttpRequestWrapper  requestWrapper = new MyCustomHttpRequestWrapper ((HttpServletRequest) servletRequest);

        requestURI = requestWrapper.getRequestURI() ;
        requestMethod = requestWrapper.getMethod() ;
        requestBody = new String(requestWrapper.getByteArray()) ;
        ip = requestWrapper.getRemoteAddr() ;


        //log.info("Request Id: {}", requestId);
        MyCustomHttpResponseWrapper responseWrapper = new MyCustomHttpResponseWrapper((HttpServletResponse)servletResponse);

        filterChain.doFilter(requestWrapper , responseWrapper);
        responseStatus = responseWrapper.getStatus();
        responesBody =  new String(responseWrapper.getBaos().toByteArray());


        String ans = new String();
        LogMsg logMsg = new LogMsg();

        if(requestMethod.equals("POST")){

            String[] lst = requestBody.split("[,\n] ",-2);
            for(int i=0;i<lst.length;i++) {
                if(lst[i].matches("\\s+\"Serverity\\W+\\w+\\W+")) {
                    String[] zz= lst[i].split("[:}\\s+]",-1);
                    ans = zz[6];
                }
            }
            serverity = ans.substring(1,ans.length() - 1);


            String info = new String("INFO");
            String error = new String("ERROR");
            String warn = new String("WARN");
            String trace = new String("TRACE");
            String debug = new String("DEBUG");
            String fatal = new String("FATAL");



            if(serverity.equalsIgnoreCase(info)){

                log.info("Serverity: {}",serverity);

                log.info("Request URI: {}",requestURI);
                log.info("Request Method: {}",requestMethod);
                log.info("Request Body: {}", requestBody);
                log.info("IP: {}", ip);

                log.info("Response status - {}", responseStatus);
                log.info("Response Body - {}", responesBody);
                logMsg.setServerity(serverity);

            }else if (serverity.equalsIgnoreCase(error) || serverity.equalsIgnoreCase(fatal)){
                log.error("Serverity: {}",serverity);

                log.error("Request URI: {}",requestURI);
                log.error("Request Method: {}",requestMethod);
                log.error("Request Body: {}", requestBody);
                log.error("IP: {}", ip);

                log.error("Response status - {}", responseStatus);
                log.error("Response Body - {}", responesBody);
                logMsg.setServerity(serverity);

            }else if(serverity.equalsIgnoreCase(warn)){
                log.warn("Serverity: {}",serverity);

                log.warn("Request URI: {}",requestURI);
                log.warn("Request Method: {}",requestMethod);
                log.warn("Request Body: {}", requestBody);
                log.warn("IP: {}", ip);

                log.warn("Response status - {}", responseStatus);
                log.warn("Response Body - {}", responesBody);
                logMsg.setServerity(serverity);

            }else if(serverity.equalsIgnoreCase(trace)){
                log.trace("Serverity: {}",serverity);

                log.trace("Request URI: {}",requestURI);
                log.trace("Request Method: {}",requestMethod);
                log.trace("Request Body: {}", requestBody);
                log.trace("IP: {}", ip);

                log.trace("Response status - {}", responseStatus);
                log.trace("Response Body - {}", responesBody);
                logMsg.setServerity(serverity);

            }else if(serverity.equalsIgnoreCase(debug)){
                log.debug("Serverity: {}",serverity);

                log.debug("Request URI: {}",requestURI);
                log.debug("Request Method: {}",requestMethod);
                log.debug("Request Body: {}", requestBody);
                log.debug("IP: {}", ip);

                log.debug("Response status - {}", responseStatus);
                log.debug("Response Body - {}", responesBody);
                logMsg.setServerity(serverity);

            }else {
                log.warn("Serverity: {}",warn);

                log.warn("Request URI: {}",requestURI);
                log.warn("Request Method: {}",requestMethod);
                log.warn("Request Body: {}", requestBody);
                log.warn("IP: {}", ip);

                log.warn("Response status - {}", responseStatus);
                log.warn("Response Body - {}", responesBody);
                logMsg.setServerity(warn);

            }
        }else if(requestMethod.equals("GET")){
            serverity = "INFO" ;
            log.info("Serverity: {}",serverity);

            log.info("Request URI: {}",requestURI);
            log.info("Request Method: {}",requestMethod);
            log.info("Request Body: {}", requestBody);
            log.info("IP: {}", ip);

            log.info("Response status - {}", responseStatus);
            log.info("Response Body - {}", responesBody);
            logMsg.setServerity(serverity);
        }


        logMsg.setRequestBody(requestBody);
        logMsg.setRequestMethod(requestMethod);
        logMsg.setRequestURI(requestURI);
        logMsg.setResponseBody(responesBody);
        logMsg.setResponseStatus(String.valueOf(responseStatus));
        logMsg.setIp(ip);


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
