package com.proyecto.tecnobedelias_desktop.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String apellido;
	private String app_token;
	private List<Carrera> carreras = new ArrayList<>();
	private String cedula;
	private String email;
	private List<Estudiante_Examen> estudianteExamen = new ArrayList<>();
	private String fechaNacimiento;
	private String foto;
	private Integer id;
	private String nombre;
	private String password;
	private String resetToken;
	private List<Rol> roles = new ArrayList<>();
	private String username;
	public Usuario() {
		super();
	}
	public Usuario(String apellido, String app_token, List<Carrera> carreras, String cedula, String email,
			List<Estudiante_Examen> estudianteExamen, String fechaNacimiento, String foto, Integer id, String nombre,
			String password, String resetToken, List<Rol> roles, String username) {
		super();
		this.apellido = apellido;
		this.app_token = app_token;
		this.carreras = carreras;
		this.cedula = cedula;
		this.email = email;
		this.estudianteExamen = estudianteExamen;
		this.fechaNacimiento = fechaNacimiento;
		this.foto = foto;
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.resetToken = resetToken;
		this.roles = roles;
		this.username = username;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getApp_token() {
		return app_token;
	}
	public void setApp_token(String app_token) {
		this.app_token = app_token;
	}
	public List<Carrera> getCarreras() {
		return carreras;
	}
	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Estudiante_Examen> getEstudianteExamen() {
		return estudianteExamen;
	}
	public void setEstudianteExamen(List<Estudiante_Examen> estudianteExamen) {
		this.estudianteExamen = estudianteExamen;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	public List<Rol> getRoles() {
		return roles;
	}
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Usuario [apellido=" + apellido + ", app_token=" + app_token + ", carreras=" + carreras + ", cedula="
				+ cedula + ", email=" + email + ", estudianteExamen=" + estudianteExamen + ", fechaNacimiento="
				+ fechaNacimiento + ", foto=" + foto + ", id=" + id + ", nombre=" + nombre + ", password=" + password
				+ ", resetToken=" + resetToken + ", roles=" + roles + ", username=" + username + "]";
	}



}
