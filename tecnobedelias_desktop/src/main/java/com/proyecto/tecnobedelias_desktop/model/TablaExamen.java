package com.proyecto.tecnobedelias_desktop.model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.jfoenix.controls.JFXButton;
import com.proyecto.tecnobedelias_desktop.service.ExamenService;
import com.proyecto.tecnobedelias_desktop.utils.GeneratePDFFileIText;
import com.proyecto.tecnobedelias_desktop.views.prueba.Prueba;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class TablaExamen {

	private SimpleStringProperty colIdExamen;
	private SimpleStringProperty colAsignaturaExamen;
	private SimpleStringProperty colFechaExamen;
	private SimpleStringProperty colHoraExamen;
	private ObjectProperty<JFXButton> colEditarExamen;
	private ObjectProperty<JFXButton> colEliminarExamen;
	private ObjectProperty<JFXButton> colActaExamen;
	private ObjectProperty<JFXButton> colCargarCalificacioensExamen;

	static Label lblNombreEstudiante = null;
	static TextField txtNota = null;
	static List<Estudiante_Examen> lstNotasCargadas = null;
	static int i = 1;
	static String idExamen = null;

	@SuppressWarnings("unused")
	private void dialogModificarExamen() {
		Dialog<Examen> dialog = new Dialog<>();

		Label lblNombreAsignatura = new Label("Nombre de la Asignatura");
		Label lblFecha = new Label("Fecha");
		Label lblHora = new Label("Hora");

		TextField txtNombreAsignatura = new TextField(this.getColAsignaturaExamen());
		TextField txtFecha = new TextField(this.getColFechaExamen());
		TextField txtHora = new TextField(this.getColHoraExamen());

		GridPane grid = new GridPane();

		dialog.setTitle("Modificacion del examen " + this.getColAsignaturaExamen());
		dialog.setHeaderText("Modificacion del examen " + this.getColAsignaturaExamen());
		dialog.setResizable(true);

		grid.add(lblNombreAsignatura, 1, 1);
		grid.add(txtNombreAsignatura, 2, 1);
		grid.add(lblFecha, 1, 2);
		grid.add(txtFecha, 2, 2);
		grid.add(lblHora, 1, 3);
		grid.add(txtHora, 2, 3);

		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, Examen>() {
		    @Override
		    public Examen call(ButtonType b) {
		    	if (b == buttonTypeOk) {
		    		List<Examen> lstExamenes = Prueba.getLstExamen();
		    		if(lstExamenes != null) {
		    			if(!lstExamenes.isEmpty()) {
		    				Examen examen = lstExamenes.stream().filter(c -> c.getId() == Integer.parseInt(getColIdExamen())).findFirst().get();
		    				if(examen != null) {
		    					examen.setHora(txtHora.getText());
		    					examen.setFecha(txtFecha.getText());
		    					examen.setNombreAsignatura(txtNombreAsignatura.getText());
		    					ExamenService cs = new ExamenService();
		    		    		alertConfirmacionModificarExamen(cs.modificarExamenResponse(examen));
		    				}
		    			}
		    		}
		        }
		        return null;
		    }
		});
		dialog.showAndWait();
	}

	private void alertConfirmacionModificarExamen(boolean respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Modificacion del Examen");

		if(respuesta) {
			dialogo.setContentText("Se ha modificado examen " + this.getColAsignaturaExamen() + " satisfactoriamente.");
		}else {
			dialogo.setContentText("No se pudo modificar el examen.");
		}
		Prueba.actualizarDatosTablaCurso();
		dialogo.showAndWait();
	}

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

		this.colEditarExamen.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialogModificarExamen();
			}
		});

		this.colEliminarExamen.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				alertEliminarExamen();
			}
		});

		this.colActaExamen.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				alertEliminarExamen();
			}
		});

		this.colActaExamen.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
				List<Examen> lstExamenes = Prueba.getLstExamen();
	    		if(lstExamenes != null) {
	    			if(!lstExamenes.isEmpty()) {
	    				Examen examen = lstExamenes.stream().filter(c -> c.getId() == Integer.parseInt(getColIdExamen())).findFirst().get();
	    				if(examen != null) {
	    					List<Estudiante_Examen> lstEstudiante = examen.getEstudianteExamen();
	    					if(lstEstudiante != null && !lstEstudiante.isEmpty()) {

	    						generatePDFFileIText.crearActaFinalDeExamen(examen.getNombreAsignatura(), lstEstudiante);
	    					}else {
	    						Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
	    						dialogo.setTitle("Acta del Examen");
	    						dialogo.setContentText("El examen de " + examen.getNombreAsignatura() + " no cuenta con estudiantes inscriptos.");
	    						dialogo.showAndWait();
	    					}
	    				}
	    			}
	    		}
		        try {
					Desktop.getDesktop().open(new File("src/resources/pdf/actaExamen" + colAsignaturaExamen + ".pdf"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		this.colCargarCalificacioensExamen.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialogCargarCalificacionesExamen();
			}
		});

	}

	private void dialogCargarCalificacionesExamen() {
		Dialog<List<Estudiante_Examen>> dialog = new Dialog<>();
		List<TextField> campos = new ArrayList<>();
		lstNotasCargadas = new ArrayList<>();
		GridPane grid = new GridPane();
		i = 1;
		idExamen = "";
		dialog.setTitle("Calificaciones del examen " + this.getColAsignaturaExamen());
		dialog.setHeaderText("Calificaciones del examen " + this.getColAsignaturaExamen());
		dialog.setResizable(true);
		try {
			List<Examen> lstExamenes = Prueba.getLstExamen();
			if(lstExamenes != null) {
				if(!lstExamenes.isEmpty()) {
					Examen examen = lstExamenes.stream().filter(c -> c.getId() == Integer.parseInt(this.getColIdExamen())).findFirst().get();
					if(examen != null) {
						idExamen += examen.getId();
						List<Estudiante_Examen> estudiantes = examen.getEstudianteExamen();
						if(!estudiantes.isEmpty()) {
							estudiantes.forEach(estudiante -> {
								lblNombreEstudiante = new Label(estudiante.getNombre() + " " + estudiante.getApellido());
								txtNota = new TextField();
								grid.add(lblNombreEstudiante, 1, i);
								grid.add(txtNota, 2, i);
								campos.add(txtNota);
								lstNotasCargadas.add(new Estudiante_Examen(estudiante.getId(),estudiante.getEstado(),estudiante.getNota(),estudiante.getNombre(),estudiante.getApellido(),estudiante.getCedula(),estudiante.getId_usuario(),estudiante.getId_examen()));
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
		dialog.setResultConverter(new Callback<ButtonType, List<Estudiante_Examen>>() {
		    @Override
		    public List<Estudiante_Examen> call(ButtonType b) {
		        List<Estudiante_Examen> respuesta = null;
		    	if (b == buttonTypeOk) {
		    		Iterator<Estudiante_Examen> iter = lstNotasCargadas.iterator();
		    		campos.forEach(notas -> {
		    			iter.next().setNota(Integer.parseInt(notas.getText()));
		    		});
		    		respuesta = lstNotasCargadas;
		    		ExamenService cs = new ExamenService();
		    		alertConfirmacionCargarCalificacionesExamen(cs.cargarCalificacionesExamenResponse(idExamen,respuesta));
		        }
		        return respuesta;
		    }
		});
		dialog.showAndWait();
	}

	private void alertConfirmacionCargarCalificacionesExamen(boolean respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Calificaciones del Examen");

		if(respuesta) {
			dialogo.setContentText("Se han cargado las calificaciones del examen " + this.getColAsignaturaExamen() + " satisfactoriamente.");
		}else {
			dialogo.setContentText("No se pudo cargar las calificaciones del examen.");
		}
		Prueba.actualizarDatosTablaCurso();
		dialogo.showAndWait();
	}

	private void alertEliminarExamen() {
		Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
		dialogo.setTitle("Eliminar Examen");
		dialogo.setContentText("Esta seguro de eliminar el examen " + this.getColAsignaturaExamen() + "?");
		dialogo.showAndWait();
		if(dialogo.getResult().getText().equalsIgnoreCase("ACEPTAR")){
			ExamenService cs = new ExamenService();
			alertConfirmacionEliminarExamen(cs.borrarExamenResponse(getColIdExamen()));
		}
	}

	private void alertConfirmacionEliminarExamen(boolean respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Eliminar Curso");

		if(respuesta) {
			dialogo.setContentText("Se ha eliminado el examen " + this.getColAsignaturaExamen() + " satisfactoriamente");
		}else {
			dialogo.setContentText("No se puede eliminar el examen porque tiene estudiantes inscriptos en el. \nPor favor si desea eliminar el examen intente darle de baja a todos los alumnos de este examen");
		}
		Prueba.actualizarDatosTablaExamen();
		dialogo.showAndWait();
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
