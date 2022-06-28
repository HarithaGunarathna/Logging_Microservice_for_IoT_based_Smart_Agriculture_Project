package com.example.co227_project.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
@Slf4j
@Order(1)
public class RequestResponseLoggers implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        log.info("Request URI: {}",httpServletRequest.getRequestURI());
        log.info("Request Method: {}",httpServletRequest.getMethod());
        log.info("Request Body: {}", new String(IOUtils.toByteArray(httpServletRequest.getInputStream())));
        filterChain.doFilter(servletRequest , servletResponse);

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse ;
    }

    private class MyCustomHttpRequestWrapper extends HttpServletRequestWrapper {

        byte[] byteArray;
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
    }
}
