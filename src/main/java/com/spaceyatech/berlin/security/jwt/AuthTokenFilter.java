package com.spaceyatech.berlin.security.jwt;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    //filter that executes once per request.


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            //get JWT from the Authorization header (by removing Bearer prefix)
            String jwt = parseJwt(request);



            //if the request has JWT, validate it, parse username from it
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

                //parsing  username from it
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                log.info("username:-->{}",username);

                //from username, get UserDetails to create an Authentication object

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                log.info("userDetails:-->{}",userDetails);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                log.info("authentication:-->{}",authentication.toString());

                //set the current UserDetails in SecurityContext
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication:--> {}",e.getMessage());
            response.setHeader("error",e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        //removing Bearer prefix
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}
