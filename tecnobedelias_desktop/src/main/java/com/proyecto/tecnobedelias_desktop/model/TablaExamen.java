package com.proyecto.tecnobedelias_desktop.model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.proyecto.tecnobedelias_desktop.global.Variables;
import com.proyecto.tecnobedelias_desktop.service.ExamenService;
import com.proyecto.tecnobedelias_desktop.utils.GeneratePDFFileIText;
import com.proyecto.tecnobedelias_desktop.views.prueba.Prueba;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class TablaExamen {

	private SimpleStringProperty colIdExamen;
	private SimpleStringProperty colAsignaturaExamen;
	private ObjectProperty<Date> colFechaExamen;
	private SimpleStringProperty colHoraExamen;
	private ObjectProperty<JFXButton> colEditarExamen;
	private ObjectProperty<JFXButton> colEliminarExamen;
	private ObjectProperty<JFXButton> colEstudiantesExamen;
	private ObjectProperty<JFXButton> colActaExamen;
	private ObjectProperty<JFXButton> colCargarCalificacioensExamen;

	private ObservableList<TablaEstudiantes> dataEstudiantes = FXCollections.observableArrayList();
	private TableView<TablaEstudiantes> tablaEstudiantes = new TableView<>();

	static Label lblNombreEstudiante = null;
	static TextField txtNota = null;
	static List<Estudiante_Examen> lstNotasCargadas = null;
	static int i = 1;
	static String idExamen = null;

	@SuppressWarnings("deprecation")
	private void dialogModificarExamen() {
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setHeight(300d);
		try {
			FlowPane main = new FlowPane();
	        main.setVgap(20);
	        main.setHgap(20);

	        JFXTimePicker blueTimePicker = new JFXTimePicker();
			Label lblNombreAsignatura = new Label("Nombre de la Asignatura");
			Label vacio1 = new Label("\t");
			Label vacio2 = new Label("\t");
			Label vacio3 = new Label("\t");
			Label lblFecha = new Label("Fecha");
			Label lblHora = new Label("Hora");
			Label txtNombreAsignatura = new Label(this.getColAsignaturaExamen());

			Date date = this.getColFechaExamen();
			Instant instant = date.toInstant();
			LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

			JFXDatePicker datePickerFechaFX = new JFXDatePicker(localDate);

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

			try {
				Date time = sdf.parse(this.getColHoraExamen());
				blueTimePicker.setValue(LocalTime.of(time.getHours(), time.getMinutes()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			blueTimePicker.setDefaultColor(Color.valueOf("#3f51b5"));
			blueTimePicker.setOverLay(true);

			GridPane grid = new GridPane();

			grid.add(lblNombreAsignatura, 1, 1);
			grid.add(vacio1, 2, 1);
			grid.add(txtNombreAsignatura, 3, 1);
			grid.add(lblFecha, 1, 2);
			grid.add(vacio2, 2, 2);
			grid.add(datePickerFechaFX, 3, 2);
			grid.add(lblHora, 1, 3);
			grid.add(vacio3, 2, 3);
			grid.add(blueTimePicker, 3, 3);

			JFXButton btnModificar = new JFXButton("Modificar");
			btnModificar.setStyle("-fx-background-color: green; -fx-pref-width: 100px; -fx-pref-height: 30px;");

			btnModificar.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					List<Examen> lstExamenes = Prueba.getLstExamen();
		    		if(lstExamenes != null) {
		    			if(!lstExamenes.isEmpty()) {
		    				Examen examen = lstExamenes.stream().filter(c -> c.getId() == Integer.parseInt(getColIdExamen())).findFirst().get();
		    				if(examen != null) {
		    					System.out.println("blueTimePicker: " + blueTimePicker.getValue().toString());
		    					examen.setHora(blueTimePicker.getValue().toString());

		    					LocalDate localDateFin = datePickerFechaFX.getValue();
		    					Instant instantFin = Instant.from(localDateFin.atStartOfDay(ZoneId.systemDefault()));
		    					Date dateFin = Date.from(instantFin);
		    					System.out.println(localDateFin + "\n" + instantFin + "\n" + dateFin);

		    					examen.setFecha(Date.from(instantFin));
		    					ExamenService cs = new ExamenService();
		    		    		alertConfirmacionModificarExamen(cs.modificarExamenResponse(examen));
		    				}
		    			}
		    		}
				}
			});

			main.getChildren().add(grid);
			main.getChildren().add(btnModificar);

	        StackPane pane = new StackPane();
	        pane.getChildren().add(main);
	        StackPane.setMargin(main, new Insets(100));
	        pane.setStyle("-fx-background-color:WHITE");

	        final Scene scene = new Scene(pane, 400, 700);

	        primaryStage.setTitle("Modificacion del examen " + this.getColAsignaturaExamen());
	        primaryStage.setScene(scene);
	        primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	private void alertConfirmacionModificarExamen(ServerResponse respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Modificacion del Examen");
		dialogo.setContentText(respuesta.getMensaje());
		Prueba.actualizarDatosTablaCurso();
		dialogo.showAndWait();
	}

	public TablaExamen(String colIdExamen, String colAsignaturaExamen, Date colFechaExamen, String colHoraExamen, JFXButton colEditarExamen,
			JFXButton colEliminarExamen, JFXButton colActaExamen, JFXButton colCargarCalificacioensExamen, JFXButton colEstudiantesExamen) {
		super();
		this.colIdExamen = new SimpleStringProperty(colIdExamen);
		this.colAsignaturaExamen = new SimpleStringProperty(colAsignaturaExamen);
		this.colFechaExamen = new SimpleObjectProperty<Date>(colFechaExamen);
		this.colHoraExamen = new SimpleStringProperty(colHoraExamen);
		this.colEditarExamen = new SimpleObjectProperty<JFXButton>(colEditarExamen);
		this.colEliminarExamen = new SimpleObjectProperty<JFXButton>(colEliminarExamen);
		this.colActaExamen = new SimpleObjectProperty<JFXButton>(colActaExamen);
		this.colCargarCalificacioensExamen = new SimpleObjectProperty<JFXButton>(colCargarCalificacioensExamen);
		this.colEstudiantesExamen = new SimpleObjectProperty<JFXButton>(colEstudiantesExamen);
	}

	public TablaExamen(String colIdExamen, String colAsignaturaExamen, Date colFechaExamen, String colHoraExamen) {
		this.colIdExamen = new SimpleStringProperty(colIdExamen);
		this.colAsignaturaExamen = new SimpleStringProperty(colAsignaturaExamen);
		this.colFechaExamen = new SimpleObjectProperty<Date>(colFechaExamen);
		this.colHoraExamen = new SimpleStringProperty(colHoraExamen);

		this.colEditarExamen = new SimpleObjectProperty<JFXButton>(new JFXButton("Editar"));
		this.colEliminarExamen = new SimpleObjectProperty<JFXButton>(new JFXButton("Eliminar"));
		this.colActaExamen = new SimpleObjectProperty<JFXButton>(new JFXButton("Acta"));
		this.colCargarCalificacioensExamen = new SimpleObjectProperty<JFXButton>(new JFXButton("Calificaciones"));
		this.colEstudiantesExamen = new SimpleObjectProperty<JFXButton>(new JFXButton("Estudiantes"));

		this.colEditarExamen.get().setStyle("-fx-background-color: #ffea00; -jfx-button-type: RAISED; -fx-font: bold italic 10.5pt \"Arial\"; -fx-pref-width: 130px; -fx-pref-height: 40px;");
		this.colEliminarExamen.get().setStyle("-fx-background-color: #f44336; -jfx-button-type: RAISED; -fx-font: bold italic 10.5pt \"Arial\"; -fx-text-fill: white; -fx-pref-width: 130px; -fx-pref-height: 40px;");
		this.colActaExamen.get().setStyle("-fx-background-color: #2196f3; -jfx-button-type: RAISED; -fx-text-fill: white; -fx-font: bold italic 10.5pt \"Arial\"; -fx-pref-width: 130px; -fx-pref-height: 40px;");
		this.colCargarCalificacioensExamen.get().setStyle("-fx-background-color: #ff9800; -jfx-button-type: RAISED; -fx-font: bold italic 10.5pt \"Arial\"; -fx-pref-width: 130px; -fx-pref-height: 40px;");
		this.colEstudiantesExamen.get().setStyle("-fx-background-color: #9c27b0; -jfx-button-type: RAISED; -fx-font: bold italic 10.5pt \"Arial\"; -fx-text-fill: white; -fx-pref-width: 130px; -fx-pref-height: 40px;");

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
				GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
				List<Examen> lstExamenes = Prueba.getLstExamen();
	    		if(lstExamenes != null) {
	    			if(!lstExamenes.isEmpty()) {
	    				Examen examen = lstExamenes.stream().filter(c -> c.getId() == Integer.parseInt(getColIdExamen())).findFirst().get();
	    				if(examen != null) {
	    					List<Estudiante_Examen> lstEstudiante = examen.getEstudianteExamen();
	    					if(lstEstudiante != null) {
	    						generatePDFFileIText.crearActaFinalDeExamen(examen.getNombreAsignatura(), lstEstudiante);
	    					}
	    				}
	    			}
	    		}
		        try {
					Desktop.getDesktop().open(new File("src/resources/pdf/actaCurso" + colAsignaturaExamen + ".pdf"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

//		this.colActaExamen.get().setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
//				List<Examen> lstExamenes = Prueba.getLstExamen();
//	    		if(lstExamenes != null) {
//	    			if(!lstExamenes.isEmpty()) {
//	    				Examen examen = lstExamenes.stream().filter(c -> c.getId() == Integer.parseInt(getColIdExamen())).findFirst().get();
//	    				if(examen != null) {
//	    					List<Estudiante_Examen> lstEstudiante = examen.getEstudianteExamen();
//	    					if(lstEstudiante != null && !lstEstudiante.isEmpty()) {
//
//	    						generatePDFFileIText.crearActaFinalDeExamen(examen.getNombreAsignatura(), lstEstudiante);
//	    					}else {
//	    						Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
//	    						dialogo.setTitle("Acta del Examen");
//	    						dialogo.setContentText("El examen de " + examen.getNombreAsignatura() + " no cuenta con estudiantes inscriptos.");
//	    						dialogo.showAndWait();
//	    					}
//	    				}
//	    			}
//	    		}
//		        try {
//					Desktop.getDesktop().open(new File("src/resources/pdf/actaExamen" + colAsignaturaExamen + ".pdf"));
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});

		this.colCargarCalificacioensExamen.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialogCargarCalificacionesExamen();
			}
		});

		this.colEstudiantesExamen.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				alertEstudiantesExamen();
			}
		});

		Date now = new Date();
		if(getColFechaExamen().after(now)) {
			getColCargarCalificacionesExamen().setDisable(true);
		}else {
			getColCargarCalificacionesExamen().setDisable(false);
		}

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

	@SuppressWarnings("unchecked")
	private void alertEstudiantesExamen() {

		Dialog<List<Estudiante>> dialogo = new Dialog<>();
		dialogo.setTitle("Estudiantes del Examen");
		dialogo.setHeaderText("Estudiantes del Examen");
		dialogo.setResizable(true);

		TableColumn<TablaEstudiantes, String> colEstudiante_ci = new TableColumn<>("Cedula");
		TableColumn<TablaEstudiantes, String> colEstudiante_nombre = new TableColumn<>("Nombre");
		getTablaEstudiantes().getColumns().clear();
		getTablaEstudiantes().getColumns().addAll(colEstudiante_ci, colEstudiante_nombre);

		try {
			colEstudiante_ci.setCellValueFactory(new PropertyValueFactory<TablaEstudiantes, String>("colCedulaEstudiante"));
			colEstudiante_nombre.setCellValueFactory(new PropertyValueFactory<TablaEstudiantes, String>("colNombreEstudiante"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		actualizarDatosTablaEstudiante();
		dialogo.getDialogPane().setContent(getTablaEstudiantes());
		ButtonType buttonTypeOk = new ButtonType("Volver", ButtonData.BACK_PREVIOUS);
		dialogo.getDialogPane().getButtonTypes().add(buttonTypeOk);
		dialogo.showAndWait();
	}

	public void actualizarDatosTablaEstudiante() {
		getDataEstudiantes().clear();
		List<Examen> lstExamenes = Prueba.getLstExamen();
		if(lstExamenes != null) {
			if(!lstExamenes.isEmpty()) {
				Examen examen = lstExamenes.stream().filter(e -> e.getId() == Integer.parseInt(getColIdExamen())).findFirst().get();
				if(examen != null) {
					Variables.setLstEstudiantesExamen(examen.getEstudianteExamen());
					if(Variables.getLstEstudiantes() != null) {
						Variables.getLstEstudiantes().forEach(estudiante -> {
							TablaEstudiantes entry = new TablaEstudiantes(estudiante.getCedula(), estudiante.getApellido().toUpperCase() + ", " + estudiante.getNombre().toUpperCase());
							getDataEstudiantes().add(entry);
						});
					}
				}
			}
		}
		getTablaEstudiantes().setItems(getDataEstudiantes());
	}

	private void alertConfirmacionCargarCalificacionesExamen(ServerResponse respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Calificaciones del Examen");
		dialogo.setContentText(respuesta.getMensaje());
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

	private void alertConfirmacionEliminarExamen(ServerResponse respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Eliminar Curso");
		dialogo.setContentText(respuesta.getMensaje());
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

	public Date getColFechaExamen() {
		return this.colFechaExamen.get();
	}

	public void setColFechaExamen(Date colFechaExamen) {
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

	public JFXButton getColEstudiantesExamen() {
		return this.colEstudiantesExamen.get();
	}

	public void setColEstudiantesExamen(JFXButton colEstudiantesExamen) {
		this.colEstudiantesExamen.set(colEstudiantesExamen);
	}

	public ObservableList<TablaEstudiantes> getDataEstudiantes() {
		return dataEstudiantes;
	}

	public void setDataEstudiantes(ObservableList<TablaEstudiantes> dataEstudiantes) {
		this.dataEstudiantes = dataEstudiantes;
	}

	public TableView<TablaEstudiantes> getTablaEstudiantes() {
		return tablaEstudiantes;
	}

	public void setTablaEstudiantes(TableView<TablaEstudiantes> tablaEstudiantes) {
		this.tablaEstudiantes = tablaEstudiantes;
	}

}
