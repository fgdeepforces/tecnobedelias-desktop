package com.proyecto.tecnobedelias_desktop.model;

public class Actividad {
	private String asignatura;
	private Integer creditos;
	private String apellido;
	private String nombre;
	private String estado;
	private String fecha;
	private Integer id;
	private Integer nota;
	private Integer notaMaxima;
	private String tipo;


	public Actividad(String asignatura, Integer creditos, String apellido, String nombre, String estado, String fecha,
			Integer id, Integer nota, Integer notaMaxima, String tipo) {
		this.asignatura = asignatura;
		this.creditos = creditos;
		this.apellido = apellido;
		this.nombre = nombre;
		this.estado = estado;
		this.fecha = fecha;
		this.id = id;
		this.nota = nota;
		this.notaMaxima = notaMaxima;
		this.tipo = tipo;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public Integer getCreditos() {
		return creditos;
	}
	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public Integer getNotaMaxima() {
		return notaMaxima;
	}
	public void setNotaMaxima(Integer notaMaxima) {
		this.notaMaxima = notaMaxima;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Actividad [asignatura=" + asignatura + ", creditos=" + creditos + ", apellido=" + apellido + ", nombre="
				+ nombre + ", estado=" + estado + ", fecha=" + fecha + ", id=" + id + ", nota=" + nota + ", notaMaxima="
				+ notaMaxima + ", tipo=" + tipo + "]";
	}


}
