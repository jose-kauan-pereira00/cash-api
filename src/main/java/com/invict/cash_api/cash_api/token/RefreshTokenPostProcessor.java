package com.invict.cash_api.cash_api.token;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessTokenResponse> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().getName().equals("postAccessToken");
    }

    @Override
    public @Nullable OAuth2AccessTokenResponse beforeBodyWrite(
            @Nullable OAuth2AccessTokenResponse body, 
            MethodParameter returnType,
            MediaType selectedContentType, 
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, 
            ServerHttpResponse response) {

        if (body == null || body.getRefreshToken() == null) {
            return body;
        }

        String refreshToken = body.getRefreshToken().getTokenValue();

        HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();

        adicionarRefreshTokenNoCookie(refreshToken, req, resp);

        return OAuth2AccessTokenResponse.withToken(body.getAccessToken().getTokenValue())
                .tokenType(body.getAccessToken().getTokenType())
                .expiresIn(body.getAccessToken().getExpiresAt().getEpochSecond() - body.getAccessToken().getIssuedAt().getEpochSecond())
                .scopes(body.getAccessToken().getScopes())
                .additionalParameters(body.getAdditionalParameters())
                .build(); 
    }

    private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(false); // Obs: Mudar para true em produção, isso ai so funciona em desenvolvimento, para não gerar erro de segurança
                                                    // Obs: NÃO ESQUECER DE MUDAR ISSO, EM HIPOTESE NEHUMA
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token"); 
        refreshTokenCookie.setMaxAge(2592000); 
        resp.addCookie(refreshTokenCookie);
    }
}