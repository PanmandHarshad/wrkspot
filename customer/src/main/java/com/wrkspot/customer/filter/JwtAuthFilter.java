package com.wrkspot.customer.filter;

import com.wrkspot.customer.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.wrkspot.customer.service.UserInfoUserDetailsService;

import java.io.IOException;
import java.util.Optional;

/**
 * JWT Authentication Filter that extends {@link OncePerRequestFilter} to ensure
 * the filter is executed once per request.
 * <p>
 * This filter checks the Authorization header for a JWT token and sets the authentication
 * context if the token is valid.
 * </p>
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoUserDetailsService userDetailsService;

    /**
     * Filters incoming requests to check for a valid JWT token in the Authorization header.
     * <p>
     * If a valid JWT token is found, it extracts the username, loads the user details,
     * and sets the authentication in the security context.
     * </p>
     *
     * @param request     the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response    the HttpServletResponse object that contains the response the servlet sends to the client
     * @param filterChain the FilterChain for invoking the next filter or the resource at the end of the chain
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Extract the Authorization header from the request
        String authHeader = request.getHeader("Authorization");
        // Extract the token and username using Optional and lambda expressions
        String token = Optional.ofNullable(authHeader)
                .filter(header -> header.startsWith("Bearer "))
                .map(header -> header.substring(7))
                .orElse(null);

        String username = Optional.ofNullable(token)
                .map(jwtService::extractUsername)
                .orElse(null);

        // If username is extracted and no authentication is set in the security context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details from the userDetailsService
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // Validate the token with the extracted user details
            if (jwtService.validateToken(token, userDetails)) {
                // Create an authentication token with the user details and set it in the security context
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
