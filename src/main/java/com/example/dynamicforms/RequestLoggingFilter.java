package com.example.dynamicforms;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Wrap request to allow multiple reads
        ContentCachingRequestWrapper cachingRequest = new ContentCachingRequestWrapper(request);

        // Proceed with chain
        filterChain.doFilter(cachingRequest, response);

        // After processing, read body
        String body = new String(cachingRequest.getContentAsByteArray(), StandardCharsets.UTF_8);

        System.out.println("Request " + request.getMethod() + " " + request.getRequestURI());
        if (!body.isEmpty()) {
            System.out.println("Body: " + body);
        }
    }
}
