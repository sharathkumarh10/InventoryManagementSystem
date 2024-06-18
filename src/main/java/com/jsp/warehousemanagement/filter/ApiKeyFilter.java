package com.jsp.warehousemanagement.filter;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jsp.warehousemanagement.entity.Client;
import com.jsp.warehousemanagement.exception.ClientNotFoundByEmailException;
import com.jsp.warehousemanagement.exception.IllegalOperationException;
import com.jsp.warehousemanagement.repository.ClientRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

	private ClientRepository clientRepo;



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getSession(false) != null)
			throw new IllegalOperationException("Can't register -> active user is present");

		if (!request.getRequestURI().equals("/api/v2/client/register")) {

			String username = request.getHeader("email");
			String headerApiKey = request.getHeader("apiKey");

			if (username != null && headerApiKey != null) {
				Client client = clientRepo.findByEmail(username)
						.orElseThrow(() -> new ClientNotFoundByEmailException("No client found by email"));

				if (!headerApiKey.equals(client.getApiKey()))
					throw new BadCredentialsException("Invalid Credentials");

			} else {
				throw new UsernameNotFoundException("No user name not found exception");
			}
		}

		filterChain.doFilter(request, response);

	}

}
