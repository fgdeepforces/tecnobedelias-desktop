package com.proyecto.tecnobedelias_desktop.model;

import com.google.gson.annotations.SerializedName;

public class RespuestaApiLogin {

	@SerializedName("autorizado")
	private Boolean autorizado;

	@SerializedName("username")
	private String username;

	public Boolean getAutorizado() {
		return autorizado;
	}
	public void setAutorizado(Boolean autorizado) {
		this.autorizado = autorizado;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
