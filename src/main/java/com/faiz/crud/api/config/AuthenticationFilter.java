package com.faiz.crud.api.config;

import com.faiz.crud.api.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String username = null;
            String jwtToken = request.getHeader("Authorization");
            if (jwtToken!=null && jwtToken.startsWith("Bearer ")){
                jwtToken = jwtToken.substring(7);   //7 is the starting index of token after bearer

                username = jwtUtils.extractUsername(jwtToken);   //extracting username from jwt token.

                if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
                    UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);    //Authenticating request
                }
            }
        }catch (Exception e){
            System.out.println("error");
        }

        filterChain.doFilter(request,response);    //forwarding request for further processing.
    }
}
