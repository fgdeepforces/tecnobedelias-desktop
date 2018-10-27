package com.proyecto.tecnobedelias_desktop.model;

import javafx.beans.property.SimpleStringProperty;

public class TablaCurso {

	private SimpleStringProperty colId;
	private SimpleStringProperty colAsignatura;
	private SimpleStringProperty colFechaInicio;
	private SimpleStringProperty colFechaFin;
	private SimpleStringProperty colSemestre;
	private SimpleStringProperty colEditar;
	private SimpleStringProperty colEliminar;
	private SimpleStringProperty colActa;

	public TablaCurso(String colId, String colAsignatura, String colFechaInicio, String colFechaFin, String colSemestre, String colEditar,
			String colEliminar, String colActa) {
		super();
		this.colId = new SimpleStringProperty(colId);
		this.colAsignatura = new SimpleStringProperty(colAsignatura);
		this.colFechaInicio = new SimpleStringProperty(colFechaInicio);
		this.colFechaFin = new SimpleStringProperty(colFechaFin);
		this.colSemestre = new SimpleStringProperty(colSemestre);
		this.colEditar = new SimpleStringProperty(colEditar);
		this.colEliminar = new SimpleStringProperty(colEliminar);
		this.colActa = new SimpleStringProperty(colActa);
	}

	public TablaCurso(String colId, String colAsignatura, String colFechaInicio, String colFechaFin, String colSemestre) {
		this.colId = new SimpleStringProperty(colId);
		this.colAsignatura = new SimpleStringProperty(colAsignatura);
		this.colFechaInicio = new SimpleStringProperty(colFechaInicio);
		this.colFechaFin = new SimpleStringProperty(colFechaFin);
		this.colSemestre = new SimpleStringProperty(colSemestre);
	}

	public String getColId() {
		return this.colId.get();
	}

	public void setColId(String colId) {
		this.colId.set(colId);
	}

	public String getColAsignatura() {
		return this.colAsignatura.get();
	}

	public void setColAsignatura(String colAsignatura) {
		this.colAsignatura.set(colAsignatura);
	}

	public String getColFechaInicio() {
		return this.colFechaInicio.get();
	}

	public void setColFechaInicio(String colFechaInicio) {
		this.colFechaInicio.set(colFechaInicio);
	}

	public String getColFechaFin() {
		return this.colFechaFin.get();
	}

	public void setColFechaFin(String colFechaFin) {
		this.colFechaFin.set(colFechaFin);
	}

	public String getColSemestre() {
		return this.colSemestre.get();
	}

	public void setColSemestre(String colSemestre) {
		this.colSemestre.set(colSemestre);
	}

	public String getColEditar() {
		return this.colEditar.get();
	}

	public void setColEditar(String colEditar) {
		this.colEditar.set(colEditar);
	}

	public String getColEliminar() {
		return this.colEliminar.get();
	}

	public void setColEliminar(String colEliminar) {
		this.colEliminar.set(colEliminar);
	}

	public String getColActa() {
		return this.colActa.get();
	}

	public void setColActa(String colActa) {
		this.colActa.set(colActa);
	}



}
