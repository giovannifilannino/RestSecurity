package com.rest.security;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.rest.security.exceptions.TokenNonInseritoException;
import com.rest.security.utility.HttpUtility;
import com.rest.security.utility.JWTUtils;

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	@Context
	private SecurityContext securityContext;

	public void filter(ContainerRequestContext requestContext) throws IOException {

		
			String token;
			try {
				token = HttpUtility.getTokenFromAuthorizationHeader(requestContext);
				JWTUtils.verifyToken(token);
				final User user = (User) AuthUtlity.getUser(token);
				
				requestContext.setSecurityContext(new SecurityContext() {

					public Principal getUserPrincipal() {
						return user;
					}

					public boolean isUserInRole(String role) {
						return false;
					}

					public boolean isSecure() {
						return false;
					}

					public String getAuthenticationScheme() {
						return null;
					}
					
				});
			} catch (TokenNonInseritoException e) {
				requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity(e.getMessage()).build());
			} catch (Exception e) {
				requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
			}
			
		
	}

}
