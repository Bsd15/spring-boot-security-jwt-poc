package com.bsd.springbootsecurityjwtpoc.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bsd.springbootsecurityjwtpoc.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private UserDetailsService userDetailsService;
	private JwtUtil jwtUtil;
	
	@Autowired
	public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			log.debug("Request inside JwtRequestFilter");
			final String requestTokenHeader = request.getHeader("Authorization");
			String userName = null;
			String jwtToken = null;
			log.debug(requestTokenHeader);
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				userName = jwtUtil.extractUsername(jwtToken);
				log.debug("JWT TOKEN From request: " + jwtToken);
				log.debug("Extracted username from JWT: " + userName);
			}
			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
				log.debug(userDetails.toString());
				if (jwtUtil.validateToken(jwtToken, userDetails)) {
					log.debug("JWT Token validated");
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
						= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request)
							);
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			
			filterChain.doFilter(request, response);
	}

}
