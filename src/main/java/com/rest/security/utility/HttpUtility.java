package com.rest.security.utility;

import javax.ws.rs.container.ContainerRequestContext;

import com.rest.security.exceptions.TokenNonInseritoException;

public class HttpUtility {
	
	private static final String BEARER = "Bearer";
	private static final String AUTHORIZATION = "Authorization";
	private static final String SPACE = " ";
	
	public static String buildAuthorizationBearer(String token) {
        return BEARER.concat(" ").concat(token);
    }

	//Con questo metodo ci prendiamo dall'header l'autorizzazione e ci recuperiamo il token, in caso token mancante lanciare un eccezione (qui non c'è per farla semplice)
    public static String getTokenFromAuthorizationHeader(ContainerRequestContext requestContext) throws TokenNonInseritoException {
        String authorizationHeader = requestContext.getHeaderString(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            return authorizationHeader.split(SPACE)[1];
        }
		throw new TokenNonInseritoException("TOKEN NON INSERITO");
    }

}
