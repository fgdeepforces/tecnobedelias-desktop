package com.proyecto.tecnobedelias_desktop.model;

import java.util.ArrayList;
import java.util.List;

public class Asignatura {

    private Integer id;
    private boolean activa;
    private String codigo;
    private String descripcion;
    private String nombre;
    private Boolean taller;
    private List<Curso> cursos = new ArrayList<>();
    private List<Examen> examenes = new ArrayList<>();

    public Asignatura() {
	}

	public Asignatura(Integer id, boolean activa, String codigo, String descripcion, String nombre, Boolean taller,
			List<Curso> cursos, List<Examen> examenes) {
		super();
		this.id = id;
		this.activa = activa;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.taller = taller;
		this.cursos = cursos;
		this.examenes = examenes;
	}

	public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<Examen> examenes) {
        this.examenes = examenes;
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

    public Boolean getTaller() {
        return taller;
    }

    public void setTaller(Boolean taller) {
        this.taller = taller;
    }

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

}
