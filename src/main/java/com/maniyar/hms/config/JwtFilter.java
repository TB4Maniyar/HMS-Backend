package com.maniyar.hms.config;

import com.maniyar.hms.service.CustomUserDetailsService;
import com.maniyar.hms.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if (header != null && header.startsWith("Bearer ")) {

            token = header.substring(7);

            try {
                username = jwtUtil.extractUsername(token);
            } catch (Exception e) {
                System.out.println("Invalid JWT Token");
            }
        }

        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            var userDetails =
                    userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, username)) {

                var authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null,
                                userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);
            }
        }

        chain.doFilter(request, response);
    }
}