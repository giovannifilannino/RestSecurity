package com.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import com.rest.model.Messaggio;
import com.rest.model.Utente;
import com.rest.security.Secured;
import com.rest.security.User;
import com.rest.security.utility.JWTUtils;

@Path("user")
public class UtentiResource {
	
	@Context
	private UriInfo uriInfo;

	@Context
	private HttpServletRequest servletRequest;

	@Context
	private SecurityContext securityContext;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Utente user) {
		if (null != user && user.getUsername().equals("TEST") && user.getPassword().equals("TEST")) {
			User user2 = new User();
			user2.setName(user.getUsername());
			user2.setRole("ADMIN");
			user2.setUsername(user.getUsername());
			String token = JWTUtils.generateToken(uriInfo, user2);
			
			return Response.status(Status.OK).header("AUTHORIZATION", token).build();
		}

		return Response.status(Status.UNAUTHORIZED).build();
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	public Response accediAInfoSegrete() {
		Messaggio msg = new Messaggio();
		msg.setMessaggio("MESSAGGIO SUPER IPER IMPORTANTE!!!");
		return Response.status(Status.OK).entity(msg).build();
	}

}
