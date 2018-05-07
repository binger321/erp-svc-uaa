package com.binger.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zhuzhixian on 17-6-26.
 */

@Component
public class AuthenticationTokenFilter extends GenericFilterBean{

    private TokenExtractor tokenExtractor = new BearerTokenExtractor();

    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Authentication preAuthenticatedAuthenticationToken = tokenExtractor.extract(httpRequest);
        if(preAuthenticatedAuthenticationToken!=null) {
            OAuth2Authentication oAuth2Authentication = null;
            try {
                oAuth2Authentication = tokenServices.loadAuthentication((String) preAuthenticatedAuthenticationToken.getPrincipal());
            }catch (Exception e){
            }
            if (SecurityContextHolder.getContext().getAuthentication() == null && oAuth2Authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(oAuth2Authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
