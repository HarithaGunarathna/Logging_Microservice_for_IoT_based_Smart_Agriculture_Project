package com.example.co227_project.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        log.info("Request Body: {}",httpServletRequest.getInputStream().toString());
        filterChain.doFilter(servletRequest , servletResponse);

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse ;
    }
}
