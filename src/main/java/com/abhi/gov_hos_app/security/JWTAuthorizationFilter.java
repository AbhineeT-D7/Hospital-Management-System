package com.abhi.gov_hos_app.security;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter  extends BasicAuthenticationFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JwtTokenProvider tokenProvider) {
        super(authenticationManager);
        jwtTokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain)
            throws IOException, ServletException {
        Authentication authentication = jwtTokenProvider.getAuthentication(request);

        if(authentication !=null && jwtTokenProvider.validateToken(request)){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        try {
            chain.doFilter(request, response);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }

    }
}
