package com.stackroute.userservice.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import io.jsonwebtoken.Jwts;

@WebFilter(filterName = "JwtFilter")
public class JwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String authorizationHeaders = httpServletRequest.getHeader("Authorization");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        if (authorizationHeaders==null || !authorizationHeaders.startsWith("Bearer ")){
            outputStream.print("Authorization headers are missing");
            outputStream.close();
        }else {
            String substring = authorizationHeaders.substring(7);
            String secretKey = Jwts.parser().setSigningKey("secret69").parseClaimsJws(substring).getBody().getSubject();
            httpServletRequest.setAttribute("emailId",secretKey);
        }
        chain.doFilter(request, response);
    }
}
