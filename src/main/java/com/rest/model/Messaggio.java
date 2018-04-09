package com.rest.model;

import java.io.Serializable;

public class Messaggio implements Serializable {

	private static final long serialVersionUID = 8358428204478918855L;
	private String messaggio;

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

}
