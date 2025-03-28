package com.dev5.backenddev5.Filter;

import com.dev5.backenddev5.Config.PublicRoutesConfig;
import com.dev5.backenddev5.Service.JwtService;
import com.dev5.backenddev5.Service.TokenBlacklistService;
import com.dev5.backenddev5.Service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioService userDetailService;
    private final TokenBlacklistService tokenBlacklistService;
    private final PublicRoutesConfig publicRoutesConfig;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UsuarioService userDetailService,
            TokenBlacklistService tokenBlacklistService,
            PublicRoutesConfig publicRoutesConfig) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
        this.tokenBlacklistService = tokenBlacklistService;
        this.publicRoutesConfig = publicRoutesConfig;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();

        // Early return si la ruta es pública, no necesita el token en los headers
        if (isPublicRoute(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Authorization header missing or invalid!");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        // Check if token is blacklisted before proceeding
        if (tokenBlacklistService.isBlacklisted(token)) {
            System.out.println("Token is blacklisted (user has logged out)");
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailService.loadUserByUsername(username);

            if (jwtService.isValid(token, userDetails)) {
                System.out.println("Token valid for user: " + username);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                System.out.println("Token invalid or expired for user: " + username);
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicRoute(String requestURI) {
        return publicRoutesConfig.getPublicPaths().stream()
                .anyMatch(pattern -> matchRoute(requestURI, pattern));
    }

    private boolean matchRoute(String requestURI, String pattern) {
        if (pattern.endsWith("/**")) {
            return requestURI.startsWith(pattern.substring(0, pattern.length() - 3));
        }
        return requestURI.equals(pattern);
    }

}