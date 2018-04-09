package com.rest.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

@NameBinding //Serve per creare annotazioni per filtri e interceptors
@Retention(RUNTIME) //Dice quando verr� letta l'annotazione
@Target({TYPE,METHOD}) 
public @interface Secured {

}
