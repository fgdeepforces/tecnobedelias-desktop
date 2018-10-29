package com.proyecto.tecnobedelias_desktop.model;

import java.util.ArrayList;
import java.util.List;

public class Carrera {

    private Integer id;
    private boolean activa;
    private Integer creditosMinimos;
    private String descripcion;
    private String nombre;
    private List<AsignaturaCarrera> asignaturaCarrera = new ArrayList<>();

    public Carrera() {
	}

	public Carrera(Integer id, boolean activa, Integer creditosMinimos, String descripcion, String nombre,
			List<AsignaturaCarrera> asignaturaCarrera) {
		super();
		this.id = id;
		this.activa = activa;
		this.creditosMinimos = creditosMinimos;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.asignaturaCarrera = asignaturaCarrera;
	}

	public List<AsignaturaCarrera> getAsignaturaCarrera() {
        return asignaturaCarrera;
    }

    public void setAsignaturaCarrera(List<AsignaturaCarrera> asignaturaCarrera) {
        this.asignaturaCarrera = asignaturaCarrera;
    }

    public Integer getCreditosMinimos() {
        return creditosMinimos;
    }

    public void setCreditosMinimos(Integer creditosMinimos) {
        this.creditosMinimos = creditosMinimos;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

}
