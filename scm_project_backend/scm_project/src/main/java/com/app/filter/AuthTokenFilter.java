package com.app.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.jwtutils.JwtUtils;
import com.app.service.CustomUserDetailsServiceImpl;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private CustomUserDetailsServiceImpl customUserDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				//first get username
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				//get all userdetails by username
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				//set authentication details
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}
		//everything is OK Let request in
		filterChain.doFilter(request, response);

		
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

}
