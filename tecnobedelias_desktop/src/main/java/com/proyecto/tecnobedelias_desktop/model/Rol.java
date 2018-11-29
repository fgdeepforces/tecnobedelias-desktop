package com.proyecto.tecnobedelias_desktop.model;

public class Rol {

	private String descripcion;
	private Integer id;
	private Integer nombre;
	public Rol() {
		super();
	}
	public Rol(String descripcion, Integer id, Integer nombre) {
		super();
		this.descripcion = descripcion;
		this.id = id;
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNombre() {
		return nombre;
	}
	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Rol [descripcion=" + descripcion + ", id=" + id + ", nombre=" + nombre + "]";
	}

}
