package com.proyecto.tecnobedelias_desktop.model;

import javafx.beans.property.SimpleStringProperty;

public class TablaEstudiantes {

	private SimpleStringProperty colCedulaEstudiante;
	private SimpleStringProperty colNombreEstudiante;

	public TablaEstudiantes(String colCedulaEstudiante, String colNombreEstudiante) {
		super();
		this.colCedulaEstudiante = new SimpleStringProperty(colCedulaEstudiante);
		this.colNombreEstudiante = new SimpleStringProperty(colNombreEstudiante);
	}

	public String getColCedulaEstudiante() {
		return this.colCedulaEstudiante.get();
	}

	public void setColIdCedulaEstudiante(String colCedulaEstudiante) {
		this.colCedulaEstudiante.set(colCedulaEstudiante);
	}

	public String getColNombreEstudiante() {
		return this.colNombreEstudiante.get();
	}

	public void setColNombreEstudiante(String colNombreEstudiante) {
		this.colNombreEstudiante.set(colNombreEstudiante);
	}
}
