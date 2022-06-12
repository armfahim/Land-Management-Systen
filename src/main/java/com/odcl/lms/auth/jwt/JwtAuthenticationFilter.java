package com.odcl.lms.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.odcl.lms.auth.service.LmsUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private LmsUserDetailsService lmsUserDetailsServiceImpl;

	@Autowired
	private JwtUtils jwtUtils;

	@SneakyThrows
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		log.debug("Token Header: {}", requestTokenHeader);
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			// yes valid
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = this.jwtUtils.extractUsername(jwtToken);
			} catch (ExpiredJwtException e) {
				log.info("SETTING {}", e.getMessage());

				request.setAttribute("Inside JwtAuthenticationFilter class ::expired", e.getMessage());
				// e.printStackTrace();
				log.info("jwt token has expired");
				throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "jwt token has expired");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			log.info("Inside JwtAuthenticationFilter class :: Invalid token, not start with bearer string");
		}

		// validated
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails userDetails = this.lmsUserDetailsServiceImpl.loadUserByUsername(username);
			if (this.jwtUtils.validateToken(jwtToken, userDetails)) {
				// token is valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		} else {
			log.info("Jwt token has expired");
		}

		filterChain.doFilter(request, response);

	}

}