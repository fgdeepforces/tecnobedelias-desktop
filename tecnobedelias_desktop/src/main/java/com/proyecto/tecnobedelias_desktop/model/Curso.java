package com.proyecto.tecnobedelias_desktop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Curso {

    private Integer id;
    private boolean activa;
    private Integer anio;
    private Date fechaFin;
    private Date fechaInicio;
    private Integer semestre;
    private Integer idAsignatura;
    private String nombreAsignatura;
    private List<Curso_Estudiante> cursoEstudiante = new ArrayList<>();
    private List<Horario> horarios = new ArrayList<>();

    public Curso() {
	}

	public Curso(Integer id, boolean activa, Integer anio, Date fechaFin, Date fechaInicio, Integer semestre,
			Integer idAsignatura, String nombreAsignatura, List<Curso_Estudiante> cursoEstudiante,
			List<Horario> horarios) {
		super();
		this.id = id;
		this.activa = activa;
		this.anio = anio;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.semestre = semestre;
		this.idAsignatura = idAsignatura;
		this.nombreAsignatura = nombreAsignatura;
		this.cursoEstudiante = cursoEstudiante;
		this.horarios = horarios;
	}

	public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public List<Curso_Estudiante> getCursoEstudiante() {
        return cursoEstudiante;
    }

    public void setCursoEstudiante(List<Curso_Estudiante> cursoEstudiante) {
        this.cursoEstudiante = cursoEstudiante;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Integer getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(Integer idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", activa=" + activa + ", anio=" + anio + ", fechaFin=" + fechaFin + ", fechaInicio="
				+ fechaInicio + ", semestre=" + semestre + ", idAsignatura=" + idAsignatura + ", nombreAsignatura="
				+ nombreAsignatura + ", cursoEstudiante=" + cursoEstudiante + ", horarios=" + horarios + "]";
	}



}
