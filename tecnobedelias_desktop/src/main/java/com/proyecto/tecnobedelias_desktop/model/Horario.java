package com.proyecto.tecnobedelias_desktop.model;

public class Horario {

    private Integer id;
    private boolean activa;
    private String dia;
    private String horaFin;
    private String horaInicio;

    public Horario(Integer id, boolean activa, String dia, String horaFin, String horaInicio) {
		super();
		this.id = id;
		this.activa = activa;
		this.dia = dia;
		this.horaFin = horaFin;
		this.horaInicio = horaInicio;
	}



	public Horario(String dia, String horaFin, String horaInicio) {
		super();
		this.id = 0;
		this.dia = dia;
		this.horaFin = horaFin;
		this.horaInicio = horaInicio;
	}



	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "Horario [id=" + id + ", activa=" + activa + ", dia=" + dia + ", horaFin=" + horaFin + ", horaInicio="
				+ horaInicio + "]";
	}

}
