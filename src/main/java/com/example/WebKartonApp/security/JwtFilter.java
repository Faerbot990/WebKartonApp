package com.example.WebKartonApp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {
    private final JwtProvider jwtProvider;
    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtProvider.resolveToken((HttpServletRequest) servletRequest);
        try {
            if (token != null && jwtProvider.validateToken(token.replace("Bearer ", ""))) {
                Authentication authentication = jwtProvider.getAuthentication(token.replace("Bearer ", ""));
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthException e) {
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());
            try {
                throw new JwtAuthException("JWT token is expired or invalid");
            } catch (JwtAuthException jwtAuthException) {
                jwtAuthException.printStackTrace();
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

