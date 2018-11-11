package com.proyecto.tecnobedelias_desktop.model;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {

	@SerializedName("estado")
	private Boolean estado;

	@SerializedName("mensaje")
	private String mensaje;

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "ServerResponse [estado=" + estado + ", mensaje=" + mensaje + "]";
	}

}
