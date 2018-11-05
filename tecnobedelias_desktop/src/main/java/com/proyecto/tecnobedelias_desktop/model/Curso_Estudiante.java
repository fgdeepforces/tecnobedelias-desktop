package com.proyecto.tecnobedelias_desktop.model;

public class Curso_Estudiante {

    private Integer id;
    private String estado;
    private Integer nota;
    private String nombre;
    private String apellido;
    private String ci;
    private Integer id_curso;
    private Integer id_estudiante;

    public Curso_Estudiante() {
	}

	public Curso_Estudiante(Integer id, String estado, Integer nota, String nombre, String apellido, Integer id_curso,
			Integer id_estudiante) {
		super();
		this.id = id;
		this.estado = estado;
		this.nota = nota;
		this.nombre = nombre;
		this.apellido = apellido;
		this.id_curso = id_curso;
		this.id_estudiante = id_estudiante;
	}

	public Curso_Estudiante(Integer id, String estado, Integer nota, String nombre, String apellido, String ci,
			Integer id_curso, Integer id_estudiante) {
		super();
		this.id = id;
		this.estado = estado;
		this.nota = nota;
		this.nombre = nombre;
		this.apellido = apellido;
		this.setCi(ci);
		this.id_curso = id_curso;
		this.id_estudiante = id_estudiante;
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

	public Integer getId_curso() {
		return id_curso;
	}

	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}

	public Integer getId_estudiante() {
		return id_estudiante;
	}

	public void setId_estudiante(Integer id_estudiante) {
		this.id_estudiante = id_estudiante;
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
		return "Curso_Estudiante [id=" + id + ", estado=" + estado + ", nota=" + nota + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", id_curso=" + id_curso + ", id_estudiante=" + id_estudiante + "]";
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

}
