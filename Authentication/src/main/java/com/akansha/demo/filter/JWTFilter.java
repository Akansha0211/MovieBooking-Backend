package com.akansha.demo.filter;

import java.io.IOException;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.GenericFilter;
import org.springframework.web.filter.GenericFilterBean;

public class JWTFilter extends GenericFilterBean implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		// checking request is coming with authorization header or not
		String authHeader = httpReq.getHeader("authorization");
		
		// authorization type of bearer
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			throw new ServletException("Missing or invalid authentication header");
		}
		
		// if request comes with proper authorization header that starts with Bearer
		// in that case the token should be created 
		String jwtToken = authHeader.substring(7); // Bearer 0273hdbssna.....
		
		// use cLaim to sign it
		// cliam is key value pair type of entity (as json value)
		// we associate token with username(any unique credential part u r using)
		// associate username with claim
		// claim is combination of token fetched from request header & signed by secret key
		// and this combined value is assocated with username
		Claims claims = Jwts.parser().setSigningKey("secret key").parseClaimsJws(jwtToken).getBody();
		
		httpReq.setAttribute("username", claims);
		chain.doFilter(request, response);
		
		// till now i can access any endpoints
		// this filter created above is not registered or implemented in way till now
	}
	
}
