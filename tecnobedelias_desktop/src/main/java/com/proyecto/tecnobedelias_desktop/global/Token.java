package com.proyecto.tecnobedelias_desktop.global;

public final class Token{

	private static Token INSTANCE = null;
	private String token = null;

	public static Token getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Token();
		}
		return INSTANCE;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


}
