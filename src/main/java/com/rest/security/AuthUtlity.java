package com.rest.security;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rest.security.utility.JWTUtils;

public class AuthUtlity {

	public static final String ROOT_QINETIC_CONTEXT = "q_context";

	public static final String ROOT_USER = "user";

	private static final String FIELD_USER_USERNAME = "username";
	private static final String FIELD_USER_ROLE = "role";
	private static final String FIELD_USER_NAME = "name";

	private static void validatePayload(String token) throws Exception {

		// Is user exists?
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<String, Object> userPayloadMap = (LinkedHashMap) JWTUtils.getTokenPayload(token).get(ROOT_USER);
		if (userPayloadMap == null || userPayloadMap.isEmpty()) {
			throw new Exception("Invalid PayLoad");
		}

		// Is app exists?
		String appPayloadMap = (String) userPayloadMap.get(FIELD_USER_USERNAME);
		if (appPayloadMap == null || appPayloadMap.isEmpty()) {
			throw new Exception("INVALID USERNAME");
		}

		// Is locale exists?
		String rolePayloadMap = (String) userPayloadMap.get(FIELD_USER_ROLE);
		if (rolePayloadMap == null || rolePayloadMap.isEmpty()) {
			throw new Exception("INVALID ROLE");

		}

		// Is locale exists?
		String fieldPayloadMap = (String) userPayloadMap.get(FIELD_USER_NAME);
		if (fieldPayloadMap == null || fieldPayloadMap.isEmpty()) {
			throw new Exception("INVALID NAME");

		}

	}

	@SuppressWarnings("rawtypes")
	private static LinkedHashMap getUserPayload(String token) {
		return (LinkedHashMap) JWTUtils.getTokenPayload(token).get(ROOT_USER);
	}

	public static User getUser(String token) throws Exception {
		validatePayload(token);
		@SuppressWarnings("unchecked")
		Map<String, Object> userPayloadMap = getUserPayload(token);
		String nome = (String) userPayloadMap.get(FIELD_USER_NAME);
		String role = (String) userPayloadMap.get(FIELD_USER_ROLE);
		String username = (String) userPayloadMap.get(FIELD_USER_USERNAME);
		// QUI SI RECUPERANO I DATI DAL DB (ESEMPIO STUPIDO)
		User u = new User();
		u.setName(nome);
		u.setRole(role);
		u.setUsername(username);

		return u;
	}

}
