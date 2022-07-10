package com.grupo16.src.utils;

import com.grupo16.src.models.entities.User;
import com.grupo16.src.services.UserService;
import com.grupo16.src.services.implementations.AuthUserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private AuthUserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            token = tokenHeader.substring(7);

            try {
                username = tokenManager.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT TOKEN has expired");
            } catch (MalformedJwtException e) {
                System.out.println("JWT Malformado");
            }
        } else {
            System.out.println("Bearer string not found");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                User userFound = userService.findOneByIdentifier(username);

                if(userFound != null) {
                    boolean isTokenRegistered = userService.isTokenValid(userFound, token);

                    if(isTokenRegistered) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                        authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder
                                .getContext()
                                .setAuthentication(authToken);
                    }
                }

            } catch(Exception e) {
                System.err.println(e);
                System.out.println("Error in token verification");
            }
        }

        filterChain.doFilter(request, response);
    }
}
