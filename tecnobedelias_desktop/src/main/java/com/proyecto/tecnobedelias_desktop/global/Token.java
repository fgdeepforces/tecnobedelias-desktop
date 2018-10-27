package com.proyecto.tecnobedelias_desktop.global;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Token{

	private static Token INSTANCE = null;
	private String token = null;
	private String mensaje = null;
	private static Retrofit retro;

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

	public Retrofit getRetro() {
		retro = new Retrofit.Builder().baseUrl(Constants.getUrlBase()).addConverterFactory(GsonConverterFactory.create()).build();
		return retro;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
