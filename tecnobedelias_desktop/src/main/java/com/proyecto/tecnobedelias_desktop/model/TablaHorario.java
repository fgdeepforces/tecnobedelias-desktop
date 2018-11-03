package com.proyecto.tecnobedelias_desktop.model;

import java.util.List;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TablaHorario {

	private SimpleStringProperty colIdHorario;
	private SimpleStringProperty colDiaHorario;
	private SimpleStringProperty colHoraInicioHorario;
	private SimpleStringProperty colHoraFinHorario;
	private ObjectProperty<JFXButton> colEditarHorario;
	private ObjectProperty<JFXButton> colEliminarHorario;

	static Label lblNombreEstudiante = null;
	static TextField txtNota = null;
	static List<Curso_Estudiante> lstNotasCargadas = null;
	static int i = 1;
	static String idCurso = null;

	public TablaHorario(String colIdHorario, String colDiaHorario,
			String colHoraInicioHorario, String colHoraFinHorario,
			JFXButton colEditarHorario, JFXButton colEliminarHorario) {
		super();
		this.colIdHorario = new SimpleStringProperty(colIdHorario);
		this.colDiaHorario = new SimpleStringProperty(colDiaHorario);
		this.colHoraInicioHorario = new SimpleStringProperty(colHoraInicioHorario);
		this.colHoraFinHorario = new SimpleStringProperty(colHoraFinHorario);
		this.colEditarHorario = new SimpleObjectProperty<JFXButton>(colEditarHorario);
		this.colEliminarHorario = new SimpleObjectProperty<JFXButton>(colEliminarHorario);
	}

	public String getColIdHorario() {
		return this.colIdHorario.get();
	}

	public void setColIdHorario(String colIdHorario) {
		this.colIdHorario.set(colIdHorario);
	}

	public String getColDiaHorario() {
		return this.colDiaHorario.get();
	}

	public void setColDiaHorario(String colDiaHorario) {
		this.colDiaHorario.set(colDiaHorario);
	}

	public String getColHoraInicioHorario() {
		return this.colHoraInicioHorario.get();
	}

	public void setColHoraInicioHorario(String colHoraInicioHorario) {
		this.colHoraInicioHorario.set(colHoraInicioHorario);
	}

	public String getColHoraFinHorario() {
		return this.colHoraFinHorario.get();
	}

	public void setColHoraFinHorario(String colHoraFinHorario) {
		this.colHoraFinHorario.set(colHoraFinHorario);
	}

	public JFXButton getColEditarHorario() {
		return this.colEditarHorario.get();
	}

	public void setColEditarHorario(JFXButton colEditarHorario) {
		this.colEditarHorario.set(colEditarHorario);
	}

	public JFXButton getColEliminarHorario() {
		return this.colEliminarHorario.get();
	}

	public void setColEliminarHorario(JFXButton colEliminarHorario) {
		this.colEliminarHorario.set(colEliminarHorario);
	}

}
