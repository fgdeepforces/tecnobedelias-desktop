package com.proyecto.tecnobedelias_desktop.model;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TablaCurso {

	private SimpleStringProperty colId;
	private SimpleStringProperty colAsignatura;
	private SimpleStringProperty colFechaInicio;
	private SimpleStringProperty colFechaFin;
	private SimpleStringProperty colSemestre;
	private ObjectProperty<JFXButton> colEditar;
	private ObjectProperty<JFXButton> colEliminar;
	private ObjectProperty<JFXButton> colActa;
	private ObjectProperty<JFXButton> colCargarCalificaciones;

	public TablaCurso(String colId, String colAsignatura, String colFechaInicio, String colFechaFin, String colSemestre, JFXButton colEditar,
			JFXButton colEliminar, JFXButton colActa, JFXButton colCargarCalificaciones) {
		super();
		this.colId = new SimpleStringProperty(colId);
		this.colAsignatura = new SimpleStringProperty(colAsignatura);
		this.colFechaInicio = new SimpleStringProperty(colFechaInicio);
		this.colFechaFin = new SimpleStringProperty(colFechaFin);
		this.colSemestre = new SimpleStringProperty(colSemestre);
		this.colEditar = new SimpleObjectProperty<JFXButton>(colEditar);
		this.colEliminar = new SimpleObjectProperty<JFXButton>(colEliminar);
		this.colActa = new SimpleObjectProperty<JFXButton>(colActa);
		this.colCargarCalificaciones = new SimpleObjectProperty<JFXButton>(colCargarCalificaciones);
	}

	public TablaCurso(String colId, String colAsignatura, String colFechaInicio, String colFechaFin, String colSemestre) {
		this.colId = new SimpleStringProperty(colId);
		this.colAsignatura = new SimpleStringProperty(colAsignatura);
		this.colFechaInicio = new SimpleStringProperty(colFechaInicio);
		this.colFechaFin = new SimpleStringProperty(colFechaFin);
		this.colSemestre = new SimpleStringProperty(colSemestre);
		this.colEditar = new SimpleObjectProperty<JFXButton>(new JFXButton("Editar"));
		this.colEliminar = new SimpleObjectProperty<JFXButton>(new JFXButton("Eliminar"));
		this.colActa = new SimpleObjectProperty<JFXButton>(new JFXButton("Acta"));
		this.colCargarCalificaciones = new SimpleObjectProperty<JFXButton>(new JFXButton("Cargar Calificaciones"));

		this.colEditar.get().setStyle("-fx-background-color: yellow;");
		this.colEliminar.get().setStyle("-fx-background-color: red;");
		this.colActa.get().setStyle("-fx-background-color: blue;");
		this.colCargarCalificaciones.get().setStyle("-fx-background-color: orange;");

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

	public JFXButton getColEditar() {
		return this.colEditar.get();
	}

	public void setColEditar(JFXButton colEditar) {
		this.colEditar.set(colEditar);
	}

	public JFXButton getColEliminar() {
		return this.colEliminar.get();
	}

	public void setColEliminar(JFXButton colEliminar) {
		this.colEliminar.set(colEliminar);
	}

	public JFXButton getColActa() {
		return this.colActa.get();
	}

	public void setColActa(JFXButton colActa) {
		this.colActa.set(colActa);
	}

	public JFXButton getColCargarCalificaciones() {
		return this.colCargarCalificaciones.get();
	}

	public void setColCargarCalificaciones(JFXButton colCargarCalificaciones) {
		this.colCargarCalificaciones.set(colCargarCalificaciones);
	}

}
