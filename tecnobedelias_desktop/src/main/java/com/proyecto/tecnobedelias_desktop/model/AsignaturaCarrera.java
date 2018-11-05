package com.proyecto.tecnobedelias_desktop.model;

public class AsignaturaCarrera {

    @Override
	public String toString() {
		return asignatura.getNombre();
	}

	private Integer id;
    private Integer creditos;
    private boolean electiva;
    private Integer notaMaxima;
    private Integer notaMinimaExamen;
    private Integer notaMinimaExonera;
    private Integer notaSalvaExamen;
    private Integer idAsignatura;
    private Integer idCarrera;
    private Asignatura asignatura;

    public AsignaturaCarrera() {
	}

	public AsignaturaCarrera(Integer id, Integer creditos, boolean electiva, Integer notaMaxima,
			Integer notaMinimaExamen, Integer notaMinimaExonera, Integer notaSalvaExamen, Integer idAsignatura,
			Integer idCarrera, Asignatura asignatura) {
		super();
		this.id = id;
		this.creditos = creditos;
		this.electiva = electiva;
		this.notaMaxima = notaMaxima;
		this.notaMinimaExamen = notaMinimaExamen;
		this.notaMinimaExonera = notaMinimaExonera;
		this.notaSalvaExamen = notaSalvaExamen;
		this.idAsignatura = idAsignatura;
		this.idCarrera = idCarrera;
		this.asignatura = asignatura;
	}

	public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotaMaxima() {
        return notaMaxima;
    }

    public void setNotaMaxima(Integer notaMaxima) {
        this.notaMaxima = notaMaxima;
    }

    public Integer getNotaMinimaExamen() {
        return notaMinimaExamen;
    }

    public void setNotaMinimaExamen(Integer notaMinimaExamen) {
        this.notaMinimaExamen = notaMinimaExamen;
    }

    public Integer getNotaMinimaExonera() {
        return notaMinimaExonera;
    }

    public void setNotaMinimaExonera(Integer notaMinimaExonera) {
        this.notaMinimaExonera = notaMinimaExonera;
    }

    public Integer getNotaSalvaExamen() {
        return notaSalvaExamen;
    }

    public void setNotaSalvaExamen(Integer notaSalvaExamen) {
        this.notaSalvaExamen = notaSalvaExamen;
    }

	public boolean isElectiva() {
		return electiva;
	}

	public void setElectiva(boolean electiva) {
		this.electiva = electiva;
	}

	public Integer getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(Integer idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public Integer getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Integer idCarrera) {
		this.idCarrera = idCarrera;
	}

}
