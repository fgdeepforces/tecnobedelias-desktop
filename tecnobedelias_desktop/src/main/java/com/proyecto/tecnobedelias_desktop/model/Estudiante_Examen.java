package com.proyecto.tecnobedelias_desktop.model;

public class Estudiante_Examen {

    private Integer id;
    private String estado;
    private Integer nota;
    private String nombre;
    private String apellido;
    private String ci;
    private Integer id_usuario;
    private Integer id_examen;

    public Estudiante_Examen() {
	}

	public Estudiante_Examen(Integer id, String estado, Integer nota, String nombre, String apellido,
			Integer id_usuario, Integer id_examen) {
		super();
		this.id = id;
		this.estado = estado;
		this.nota = nota;
		this.nombre = nombre;
		this.apellido = apellido;
		this.id_usuario = id_usuario;
		this.id_examen = id_examen;
	}

	public Estudiante_Examen(Integer id, String estado, Integer nota, String nombre, String apellido, String ci,
			Integer id_usuario, Integer id_examen) {
		super();
		this.id = id;
		this.estado = estado;
		this.nota = nota;
		this.nombre = nombre;
		this.apellido = apellido;
		this.setCi(ci);
		this.id_usuario = id_usuario;
		this.id_examen = id_examen;
	}

	public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Integer getId_examen() {
		return id_examen;
	}

	public void setId_examen(Integer id_examen) {
		this.id_examen = id_examen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Estudiante_Examen [id=" + id + ", estado=" + estado + ", nota=" + nota + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", id_usuario=" + id_usuario + ", id_examen=" + id_examen + "]";
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

}
