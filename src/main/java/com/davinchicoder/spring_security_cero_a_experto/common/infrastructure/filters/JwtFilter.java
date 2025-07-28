package com.davinchicoder.spring_security_cero_a_experto.common.infrastructure.filters;

import com.davinchicoder.spring_security_cero_a_experto.common.infrastructure.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("No token found");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(7);

        try {
            boolean isTokenExpired = jwtService.isTokenExpired(token);
            boolean canBeTokenRenewed = jwtService.canBeTokenRenewed(token);

            if (isTokenExpired && !canBeTokenRenewed) {
                log.error("Token expired");
                filterChain.doFilter(request, response);
                return;
            }

            String username = jwtService.getUsername(token);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            boolean isValidToken = jwtService.isValidToken(token, userDetails);

            if (!isValidToken || SecurityContextHolder.getContext().getAuthentication() != null) {
                log.error("Invalid token or user already authenticated");
                filterChain.doFilter(request, response);
                return;
            }

            if (isTokenExpired && canBeTokenRenewed) {
                String renewToken = jwtService.renewToken(token, userDetails);
                response.setHeader("Authorization", "Bearer " + renewToken);
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.error("Error validating token: {}", e.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, e);
        }

        filterChain.doFilter(request, response);
    }
}
