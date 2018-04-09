package com.rest.security;

import java.security.Principal;

public interface UserPrincipal extends Principal{

	String getUsername();
	String getRole();
}
