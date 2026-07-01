package com.invict.cash_api.cash_api.cors;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private String originPermitida = "http://localhost:8000"; // Substitua pelo domínio do seu front-end, LEMBRAR DE FAZER ISSO Quando for pra produção

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                HttpServletResponse httpResponse = (HttpServletResponse) response;

                if("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod()) 
                    && originPermitida.equals(((HttpServletRequest) request).getHeader("Origin"))) {
                    
                    httpResponse.setHeader("Access-Control-Allow-Origin", originPermitida);
                    httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
                    httpResponse.setHeader("Access-Control-Max-Age", "3600");
                    httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
                    httpResponse.setStatus(HttpServletResponse.SC_OK);
                } else {
                    chain.doFilter(request, response);
                }
    }

}
