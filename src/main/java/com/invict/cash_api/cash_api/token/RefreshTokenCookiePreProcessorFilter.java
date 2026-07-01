package com.invict.cash_api.cash_api.token;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

                HttpServletRequest req = (HttpServletRequest) request;
                if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
                        && "refresh_token".equals(req.getParameter("grant_type"))
                        && req.getCookies() != null) {   
                    for (var cookie : req.getCookies()) {
                        if ("refreshToken".equals(cookie.getName())) {
                            String refreshToken = cookie.getValue();  
                            request = new MyServletRequestWrapper(req, refreshToken);
                        } 
            }
        }
        chain.doFilter(request, response);
    }

    static class MyServletRequestWrapper extends jakarta.servlet.http.HttpServletRequestWrapper {
        private String refreshToken;

        public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }

        @Override
        public String getParameter(String name) {
            if ("refresh_token".equals(name)) {
                return refreshToken;
            }
            return super.getParameter(name);
        }

        public Map<String, String[]> getParameterMap() {
            Map<String, String[]> map = super.getParameterMap();
            map.put("refresh_token", new String[] { refreshToken });
            return map;
        }
    }
}