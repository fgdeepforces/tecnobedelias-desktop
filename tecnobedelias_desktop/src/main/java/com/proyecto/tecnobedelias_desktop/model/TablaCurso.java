package com.proyecto.tecnobedelias_desktop.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.jfoenix.controls.JFXButton;
import com.proyecto.tecnobedelias_desktop.service.CursoService;
import com.proyecto.tecnobedelias_desktop.views.prueba.Prueba;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

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

	static Label lblNombreEstudiante = null;
	static TextField txtNota = null;
	static List<Curso_Estudiante> lstNotasCargadas = null;
	static int i = 1;
	static String idCurso = null;

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

		this.colCargarCalificaciones.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialogCargarCalificacionesCurso();
			}
		});

	}

	private void dialogCargarCalificacionesCurso() {
		Dialog<List<Curso_Estudiante>> dialog = new Dialog<>();
		List<TextField> campos = new ArrayList<>();
		lstNotasCargadas = new ArrayList<>();
		GridPane grid = new GridPane();
		i = 1;
		idCurso = "";
		dialog.setTitle("Calificaciones del curso " + this.getColAsignatura());
		dialog.setHeaderText("Calificaciones del curso " + this.getColAsignatura());
		dialog.setResizable(true);
		try {
			List<Curso> lstCursos = Prueba.getLstCurso();
			if(lstCursos != null) {
				if(!lstCursos.isEmpty()) {
					Curso curso = lstCursos.stream().filter(c -> c.getId() == Integer.parseInt(this.getColId())).findFirst().get();
					if(curso != null) {
						idCurso += curso.getId();
						List<Curso_Estudiante> estudiantes = curso.getCursoEstudiante();
						if(!estudiantes.isEmpty()) {
							estudiantes.forEach(estudiante -> {
								lblNombreEstudiante = new Label(estudiante.getNombre() + " " + estudiante.getApellido());
								txtNota = new TextField();
								grid.add(lblNombreEstudiante, 1, i);
								grid.add(txtNota, 2, i);
								campos.add(txtNota);
								lstNotasCargadas.add(new Curso_Estudiante(estudiante.getId(),estudiante.getEstado(),estudiante.getNota(),estudiante.getNombre(),estudiante.getApellido(),estudiante.getId_curso(),estudiante.getId_estudiante()));
								i++;
							});
						}
					}
				}
			}
		}catch(NullPointerException | NumberFormatException e) {
			e.printStackTrace();
		}
		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, List<Curso_Estudiante>>() {
		    @Override
		    public List<Curso_Estudiante> call(ButtonType b) {
		        List<Curso_Estudiante> respuesta = null;
		    	if (b == buttonTypeOk) {
		    		Iterator<Curso_Estudiante> iter = lstNotasCargadas.iterator();
		    		campos.forEach(notas -> {
		    			iter.next().setNota(Integer.parseInt(notas.getText()));
		    		});
		    		respuesta = lstNotasCargadas;
		    		CursoService cs = new CursoService();
		    		alertConfirmacionCargarCalificacionesCurso(cs.cargarCalificacionesCursoResponse(idCurso,respuesta));
		        }
		        return respuesta;
		    }
		});
		dialog.showAndWait();
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

	private void alertConfirmacionCargarCalificacionesCurso(boolean respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Calificaciones del Curso");

		if(respuesta) {
			dialogo.setContentText("Se han cargado las calificaciones del curso " + this.getColAsignatura() + " satisfactoriamente.");
		}else {
			dialogo.setContentText("No se pudo cargar las calificaciones del curso.");
		}
		Prueba.actualizarDatosTablaCurso();
		dialogo.showAndWait();
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
