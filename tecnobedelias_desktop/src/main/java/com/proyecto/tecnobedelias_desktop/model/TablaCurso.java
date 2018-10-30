package com.proyecto.tecnobedelias_desktop.model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.proyecto.tecnobedelias_desktop.service.CursoService;
import com.proyecto.tecnobedelias_desktop.views.prueba.Prueba;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

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

		this.colEditar.get().setStyle("-fx-background-color: yellow; -fx-pref-width: 130px; -fx-pref-height: 50px;");
		this.colEliminar.get().setStyle("-fx-background-color: red; -fx-pref-width: 130px; -fx-pref-height: 50px;");
		this.colActa.get().setStyle("-fx-background-color: blue; -fx-pref-width: 130px; -fx-pref-height: 50px;");
		this.colCargarCalificaciones.get().setStyle("-fx-background-color: orange; -fx-pref-width: 130px; -fx-pref-height: 50px;");

		this.colEliminar.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				alertEliminarCurso();
			}
		});

	}

	private void alertEliminarCurso() {
		Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
		dialogo.setTitle("Eliminar Curso");
		dialogo.setContentText("Esta seguro de eliminar el curso " + this.getColAsignatura() + "?");
		dialogo.showAndWait();
		if(dialogo.getResult().getText().equalsIgnoreCase("ACEPTAR")){
			CursoService cs = new CursoService();
			alertConfirmacionEliminarCurso(cs.borrarCursoResponse(getColId()));
		}
	}

	private void alertConfirmacionEliminarCurso(boolean respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Eliminar Curso");

		if(respuesta) {
			dialogo.setContentText("Se ha eliminado el curso " + this.getColAsignatura() + " satisfactoriamente");
		}else {
			dialogo.setContentText("No se puede eliminar el curso porque tiene estudiantes inscriptos en el. \nPor favor si desea eliminar el curso intente darle de baja a todos los alumnos de este curso");
		}
		Prueba.actualizarDatosTablaCurso();
		dialogo.showAndWait();
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
