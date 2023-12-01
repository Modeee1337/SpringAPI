package com.ktu.csgo.insight.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

public class InsightAuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    private final Logger log = LoggerFactory.getLogger(InsightAuthenticationEntryPoint.class);

    /**
     * Always returns a 401 error code to the client.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {

        log.debug("Pre-authenticated entry point called. Rejecting access");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }
}
