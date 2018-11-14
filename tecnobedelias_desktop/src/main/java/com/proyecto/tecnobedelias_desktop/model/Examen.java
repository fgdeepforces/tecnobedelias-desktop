package com.proyecto.tecnobedelias_desktop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Examen {

    private Integer id;
    private boolean activa;
    private Date fecha;
    private String hora;
    private Integer idAsignatura;
    private String nombreAsignatura;
    private List<Estudiante_Examen> estudianteExamen = new ArrayList<>();

    public Examen() {
	}

	public Examen(Integer id, boolean activa, Date fecha, String hora, Integer idAsignatura, String nombreAsignatura,
			List<Estudiante_Examen> estudianteExamen) {
		super();
		this.id = id;
		this.activa = activa;
		this.fecha = fecha;
		this.hora = hora;
		this.idAsignatura = idAsignatura;
		this.nombreAsignatura = nombreAsignatura;
		this.estudianteExamen = estudianteExamen;
	}

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public List<Estudiante_Examen> getEstudianteExamen() {
		return estudianteExamen;
	}

	public void setEstudianteExamen(List<Estudiante_Examen> estudianteExamen) {
		this.estudianteExamen = estudianteExamen;
	}

	@Override
	public String toString() {
		return "Examen [id=" + id + ", activa=" + activa + ", fecha=" + fecha + ", hora=" + hora + ", idAsignatura="
				+ idAsignatura + ", nombreAsignatura=" + nombreAsignatura + "]";
	}

}
