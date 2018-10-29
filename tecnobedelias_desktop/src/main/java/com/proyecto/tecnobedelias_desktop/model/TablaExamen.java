package com.proyecto.tecnobedelias_desktop.model;

import com.jfoenix.controls.JFXButton;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TablaExamen {

	private SimpleStringProperty colIdExamen;
	private SimpleStringProperty colAsignaturaExamen;
	private SimpleStringProperty colFechaExamen;
	private SimpleStringProperty colHoraExamen;
	private ObjectProperty<JFXButton> colEditarExamen;
	private ObjectProperty<JFXButton> colEliminarExamen;
	private ObjectProperty<JFXButton> colActaExamen;
	private ObjectProperty<JFXButton> colCargarCalificacioensExamen;

	public TablaExamen(String colIdExamen, String colAsignaturaExamen, String colFechaExamen, String colHoraExamen, JFXButton colEditarExamen,
			JFXButton colEliminarExamen, JFXButton colActaExamen, JFXButton colCargarCalificacioensExamen) {
		super();
		this.colIdExamen = new SimpleStringProperty(colIdExamen);
		this.colAsignaturaExamen = new SimpleStringProperty(colAsignaturaExamen);
		this.colFechaExamen = new SimpleStringProperty(colFechaExamen);
		this.colHoraExamen = new SimpleStringProperty(colHoraExamen);
		this.colEditarExamen = new SimpleObjectProperty<JFXButton>(colEditarExamen);
		this.colEliminarExamen = new SimpleObjectProperty<JFXButton>(colEliminarExamen);
		this.colActaExamen = new SimpleObjectProperty<JFXButton>(colActaExamen);
		this.colCargarCalificacioensExamen = new SimpleObjectProperty<JFXButton>(colCargarCalificacioensExamen);
	}

	public TablaExamen(String colIdExamen, String colAsignaturaExamen, String colFechaExamen, String colHoraExamen) {
		this.colIdExamen = new SimpleStringProperty(colIdExamen);
		this.colAsignaturaExamen = new SimpleStringProperty(colAsignaturaExamen);
		this.colFechaExamen = new SimpleStringProperty(colFechaExamen);
		this.colHoraExamen = new SimpleStringProperty(colHoraExamen);

		this.colEditarExamen = new SimpleObjectProperty<JFXButton>(new JFXButton("Editar"));
		this.colEliminarExamen = new SimpleObjectProperty<JFXButton>(new JFXButton("Eliminar"));
		this.colActaExamen = new SimpleObjectProperty<JFXButton>(new JFXButton("Acta"));
		this.colCargarCalificacioensExamen = new SimpleObjectProperty<JFXButton>(new JFXButton("Cargar Calificaciones"));

		this.colEditarExamen.get().setStyle("-fx-background-color: yellow; -fx-pref-width: 130px; -fx-pref-height: 50px;");
		this.colEliminarExamen.get().setStyle("-fx-background-color: red; -fx-pref-width: 130px; -fx-pref-height: 50px;");
		this.colActaExamen.get().setStyle("-fx-background-color: blue; -fx-pref-width: 130px; -fx-pref-height: 50px;");
		this.colCargarCalificacioensExamen.get().setStyle("-fx-background-color: orange; -fx-pref-width: 130px; -fx-pref-height: 50px;");

	}

	public String getColIdExamen() {
		return this.colIdExamen.get();
	}

	public void setColIdExamen(String colIdExamen) {
		this.colIdExamen.set(colIdExamen);
	}

	public String getColAsignaturaExamen() {
		return this.colAsignaturaExamen.get();
	}

	public void setColAsignaturaExamen(String colAsignaturaExamen) {
		this.colAsignaturaExamen.set(colAsignaturaExamen);
	}

	public String getColFechaExamen() {
		return this.colFechaExamen.get();
	}

	public void setColFechaExamen(String colFechaExamen) {
		this.colFechaExamen.set(colFechaExamen);
	}

	public String getColHoraExamen() {
		return this.colHoraExamen.get();
	}

	public void setColHoraExamen(String colHoraExamen) {
		this.colHoraExamen.set(colHoraExamen);
	}

	public JFXButton getColEditarExamen() {
		return this.colEditarExamen.get();
	}

	public void setColEditarExamen(JFXButton colEditarExamen) {
		this.colEditarExamen.set(colEditarExamen);
	}

	public JFXButton getColEliminarExamen() {
		return this.colEliminarExamen.get();
	}

	public void setColEliminarExamen(JFXButton colEliminarExamen) {
		this.colEliminarExamen.set(colEliminarExamen);
	}

	public JFXButton getColActaExamen() {
		return this.colActaExamen.get();
	}

	public void setColActaExamen(JFXButton colActaExamen) {
		this.colActaExamen.set(colActaExamen);
	}

	public JFXButton getColCargarCalificacionesExamen() {
		return this.colCargarCalificacioensExamen.get();
	}

	public void setColCargarCalificacionesExamen(JFXButton colCargarCalificacioensExamen) {
		this.colCargarCalificacioensExamen.set(colCargarCalificacioensExamen);
	}

}
