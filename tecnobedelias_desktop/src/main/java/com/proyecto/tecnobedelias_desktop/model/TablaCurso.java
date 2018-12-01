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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.proyecto.tecnobedelias_desktop.global.Variables;
import com.proyecto.tecnobedelias_desktop.service.CursoService;
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class TablaCurso {

	private SimpleStringProperty colId;
	private SimpleStringProperty colAsignatura;
	private SimpleStringProperty colAnio;
	private ObjectProperty<Date> colFechaInicio;
	private ObjectProperty<Date> colFechaFin;
	private SimpleStringProperty colSemestre;
	private ObjectProperty<JFXButton> colHorarios;
	private ObjectProperty<JFXButton> colEditar;
	private ObjectProperty<JFXButton> colEliminar;
	private ObjectProperty<JFXButton> colEstudiantes;
	private ObjectProperty<JFXButton> colActa;
	private ObjectProperty<JFXButton> colCargarCalificaciones;

	private JFXButton colEditarHorario;
	private JFXButton colEliminarHorario;

	private ObservableList<TablaHorario> dataHorario = FXCollections.observableArrayList();
	private ObservableList<TablaEstudiantes> dataEstudiantes = FXCollections.observableArrayList();
	private TableView<TablaHorario> tablaHorarios = new TableView<>();
	private TableView<TablaEstudiantes> tablaEstudiantes = new TableView<>();

	static Label lblNombreEstudiante = null;
	static TextField txtNota = null;
	static List<Curso_Estudiante> lstNotasCargadas = null;
	static int i = 1;
	static String idCurso = null;

	@SuppressWarnings("deprecation")
	private void dialogModificarHorario() {
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setHeight(300d);
		try {
			FlowPane main = new FlowPane();
	        main.setVgap(20);
	        main.setHgap(20);

	        JFXTimePicker tpHoraInicio = new JFXTimePicker();
	        JFXTimePicker tpHoraFin = new JFXTimePicker();
			Label lblDia = new Label("Dia");
			Label vacio1 = new Label("\t");
			Label vacio2 = new Label("\t");
			Label vacio3 = new Label("\t");
			Label lblHoraInicio = new Label("Hora Inicio");
			Label lblHoraFin = new Label("Hora Fin");

			ObservableList<String> dataDias = FXCollections.observableArrayList();
			dataDias.add("Lunes");
			dataDias.add("Martes");
			dataDias.add("Miercoles");
			dataDias.add("Jueves");
			dataDias.add("Viernes");

			JFXComboBox<String> cboDia = new JFXComboBox<>(dataDias);

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

			try {
				Date timeInicio = sdf.parse(getTablaHorarios().getSelectionModel().getSelectedItem().getColHoraInicioHorario());
				tpHoraInicio.setValue(LocalTime.of(timeInicio.getHours(), timeInicio.getMinutes()));
				Date timeFin = sdf.parse(getTablaHorarios().getSelectionModel().getSelectedItem().getColHoraInicioHorario());
				tpHoraInicio.setValue(LocalTime.of(timeFin.getHours(), timeFin.getMinutes()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			tpHoraInicio.setDefaultColor(Color.valueOf("#3f51b5"));
			tpHoraInicio.setOverLay(true);
			tpHoraFin.setDefaultColor(Color.valueOf("#3f51b5"));
			tpHoraFin.setOverLay(true);

			GridPane grid = new GridPane();

			grid.add(lblDia, 1, 1);
			grid.add(vacio1, 2, 1);
			grid.add(cboDia, 3, 1);
			grid.add(lblHoraInicio, 1, 2);
			grid.add(vacio2, 2, 2);
			grid.add(tpHoraInicio, 3, 2);
			grid.add(lblHoraFin, 1, 3);
			grid.add(vacio3, 2, 3);
			grid.add(tpHoraFin, 3, 3);

			JFXButton btnModificar = new JFXButton("Modificar");
			btnModificar.setStyle("-fx-background-color: green; -fx-pref-width: 100px; -fx-pref-height: 30px;");

			btnModificar.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					int index = getTablaHorarios().getSelectionModel().getSelectedIndex();
					Variables.getLstHorarios().get(index).setDia(cboDia.getSelectionModel().getSelectedItem());
		    		Variables.getLstHorarios().get(index).setHoraInicio(tpHoraInicio.getValue().toString());
		    		Variables.getLstHorarios().get(index).setHoraFin(tpHoraFin.getValue().toString());
		    		alertConfirmacionModificarHorario(true);
				}
			});

			main.getChildren().add(grid);
			main.getChildren().add(btnModificar);

	        StackPane pane = new StackPane();
	        pane.getChildren().add(main);
	        StackPane.setMargin(main, new Insets(100));
	        pane.setStyle("-fx-background-color:WHITE");

	        final Scene scene = new Scene(pane, 400, 700);

	        primaryStage.setTitle("Modificacion del horario del dia " + getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario());
	        primaryStage.setScene(scene);
	        primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void alertConfirmacionModificarHorario(boolean respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Modificacion del Horario");

		if(respuesta) {
			dialogo.setContentText("Se ha modificado el horario satisfactoriamente.");
		}else {
			dialogo.setContentText("No se pudo modificar el horario.");
		}
		actualizarDatosTablaHorario();
		dialogo.showAndWait();
	}

	private void alertEliminarHorario() {
		Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
		dialogo.setTitle("Eliminar Horario");
		if(getTablaHorarios().getSelectionModel().getSelectedItem() == null) {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Informacion");
			alerta.setContentText("Debes de seleccionar una fila antes de proceder a eliminar el horario");
			alerta.showAndWait();
		}else {
			int index = getTablaHorarios().getSelectionModel().getSelectedIndex();
			dialogo.setContentText("Esta seguro de eliminar el horario " + getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario() + "?");
			dialogo.showAndWait();
			if(dialogo.getResult().getText().equalsIgnoreCase("ACEPTAR")){
				Variables.getLstHorarios().remove(index);
				alertConfirmacionEliminarHorario(true);
			}
		}
	}

	private void alertConfirmacionEliminarHorario(boolean respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Eliminar Horario");

		if(respuesta) {
			dialogo.setContentText("Se ha eliminado el horario " + getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario() + " satisfactoriamente");
		}else {
			dialogo.setContentText("No se puede eliminar el horario");
		}
		actualizarDatosTablaHorario();
		dialogo.showAndWait();
	}

	public TablaCurso() {
	}

	public TablaCurso(SimpleStringProperty colId, SimpleStringProperty colAsignatura, SimpleStringProperty colAnio,
			ObjectProperty<Date> colFechaInicio, ObjectProperty<Date> colFechaFin, SimpleStringProperty colSemestre,
			ObjectProperty<JFXButton> colHorarios, ObjectProperty<JFXButton> colEditar,
			ObjectProperty<JFXButton> colEliminar, ObjectProperty<JFXButton> colActa,
			ObjectProperty<JFXButton> colCargarCalificaciones, ObjectProperty<JFXButton> colEstudiantes) {
		super();
		this.colId = colId;
		this.colAsignatura = colAsignatura;
		this.colAnio = colAnio;
		this.colFechaInicio = colFechaInicio;
		this.colFechaFin = colFechaFin;
		this.colSemestre = colSemestre;
		this.colHorarios = colHorarios;
		this.colEditar = colEditar;
		this.colEliminar = colEliminar;
		this.colEstudiantes = colEstudiantes;
		this.colActa = colActa;
		this.colCargarCalificaciones = colCargarCalificaciones;
	}

	public TablaCurso(String colId, String colAsignatura, String anio, Date colFechaInicio, Date colFechaFin, String colSemestre) {
		this.colId = new SimpleStringProperty(colId);
		this.colAsignatura = new SimpleStringProperty(colAsignatura);
		this.colAnio = new SimpleStringProperty(anio);
		this.colFechaInicio = new SimpleObjectProperty<Date>(colFechaInicio);
		this.colFechaFin = new SimpleObjectProperty<Date>(colFechaFin);
		this.colSemestre = new SimpleStringProperty(colSemestre);
		this.colHorarios = new SimpleObjectProperty<JFXButton>(new JFXButton("Horarios"));
		this.colEditar = new SimpleObjectProperty<JFXButton>(new JFXButton("Editar"));
		this.colEliminar = new SimpleObjectProperty<JFXButton>(new JFXButton("Eliminar"));
		this.colActa = new SimpleObjectProperty<JFXButton>(new JFXButton("Acta"));
		this.colCargarCalificaciones = new SimpleObjectProperty<JFXButton>(new JFXButton("Calificaciones"));
		this.colEstudiantes = new SimpleObjectProperty<JFXButton>(new JFXButton("Estudiantes"));

		this.colHorarios.get().setStyle("-fx-background-color: #00e676; -jfx-button-type: RAISED; -fx-font: bold italic 10.5pt \"Arial\"; -fx-pref-width: 130px; -fx-pref-height: 40px;");
		this.colEditar.get().setStyle("-fx-background-color: #ffea00; -jfx-button-type: RAISED; -fx-font: bold italic 10.5pt \"Arial\"; -fx-pref-width: 130px; -fx-pref-height: 40px;");
		this.colEliminar.get().setStyle("-fx-background-color: #f44336; -jfx-button-type: RAISED; -fx-font: bold italic 10.5pt \"Arial\"; -fx-text-fill: white; -fx-pref-width: 130px; -fx-pref-height: 40px;");
		this.colActa.get().setStyle("-fx-background-color: #2196f3; -jfx-button-type: RAISED; -fx-text-fill: white; -fx-font: bold italic 10.5pt \"Arial\"; -fx-pref-width: 130px; -fx-pref-height: 40px;");
		this.colCargarCalificaciones.get().setStyle("-fx-background-color: #ff9800; -jfx-button-type: RAISED; -fx-font: bold italic 10.5pt \"Arial\"; -fx-pref-width: 130px; -fx-pref-height: 40px;");
		this.colEstudiantes.get().setStyle("-fx-background-color: #9c27b0; -jfx-button-type: RAISED; -fx-font: bold italic 10.5pt \"Arial\"; -fx-text-fill: white; -fx-pref-width: 130px; -fx-pref-height: 40px;");

		this.colHorarios.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				alertHorariosCurso();
			}
		});

		this.colEditar.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialogModificarCurso();
			}
		});

		this.colEliminar.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				alertEliminarCurso();
			}
		});

		this.colActa.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
				List<Curso> lstCursos = Prueba.getLstCurso();
	    		if(lstCursos != null) {
	    			if(!lstCursos.isEmpty()) {
	    				Curso curso = lstCursos.stream().filter(c -> c.getId() == Integer.parseInt(getColId())).findFirst().get();
	    				if(curso != null) {
	    					List<Curso_Estudiante> lstEstudiante = curso.getCursoEstudiante();
	    					if(lstEstudiante != null) {
	    						if(!lstEstudiante.isEmpty()) {
	    							generatePDFFileIText.crearActaFinalDeCurso(curso.getNombreAsignatura(), curso.getSemestre(), curso.getAnio(), lstEstudiante);
	    							try {
	    								Desktop.getDesktop().open(new File("src/resources/pdf/actaCurso" + colAsignatura + ".pdf"));
	    							} catch (IOException e) {
	    								e.printStackTrace();
	    							}
	    						}else {
	    							Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
	    							dialogo.setTitle("Acta del Examen");
	    							dialogo.setContentText("Debe de cargar las calificaciones antes para generar el acta.");
	    							Prueba.actualizarDatosTablaCurso();
	    							dialogo.showAndWait();
	    						}
	    					}
	    				}
	    			}
	    		}
			}
		});

		this.colCargarCalificaciones.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialogCargarCalificacionesCurso();
			}
		});

		this.colEstudiantes.get().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				alertEstudiantesCurso();
			}
		});

		Date now = new Date();
		if(getColFechaFin().after(now)) {
			getColCargarCalificaciones().setDisable(true);
		}else {
			getColCargarCalificaciones().setDisable(false);
		}

	}

	@SuppressWarnings("unchecked")
	private void dialogModificarCurso() {
		Dialog<Curso> dialog = new Dialog<>();

		JFXButton btnIngresarHorario = new JFXButton("Ingresar Horario");

		Label lblNombreAsignatura = new Label("Nombre de la Asignatura");
		Label lblAnio = new Label("Anio");
		Label lblFechaInicio = new Label("Fecha de Inicio");
		Label lblFechaFin = new Label("Fecha de Finalizacion");
		Label lblSemestre = new Label("Semestre");
		Label lblHorarios = new Label("Horarios");
		Label vacio1 = new Label("\t");
		Label vacio2 = new Label("\t");
		Label vacio3 = new Label("\t");
		Label vacio4 = new Label("\t");
		Label vacio5 = new Label("\t");

		Label txtNombreAsignatura = new Label(this.getColAsignatura());
		Label txtAnio = new Label(this.getColAnio());

		Date dateInicio = this.getColFechaInicio();
		Instant instantInicio = dateInicio.toInstant();
		LocalDate localDateInicio = instantInicio.atZone(ZoneId.systemDefault()).toLocalDate();

		JFXDatePicker datePickerFechaInicioFX = new JFXDatePicker(localDateInicio);

		Date dateFin = this.getColFechaFin();
		Instant instantFin = dateFin.toInstant();
		LocalDate localDateFin = instantFin.atZone(ZoneId.systemDefault()).toLocalDate();

		JFXDatePicker datePickerFechaFinFX = new JFXDatePicker(localDateFin);

		TextField txtSemestre = new TextField(this.getColSemestre());

		VBox box = new VBox();
		HBox topBox = new HBox();
		GridPane grid = new GridPane();
		BorderPane border = new BorderPane();

		btnIngresarHorario.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialogIngresarHorario();
			}
		});

		box.getChildren().addAll(grid,border);
		dialog.getDialogPane().setContent(box);

		dialog.setTitle("Modificacion del curso " + this.getColAsignatura());
		dialog.setHeaderText("Modificacion del curso " + this.getColAsignatura());
		dialog.setResizable(true);

		grid.add(lblNombreAsignatura, 1, 1);
		grid.add(vacio1, 2, 1);
		grid.add(txtNombreAsignatura, 3, 1);
		grid.add(lblAnio, 1, 2);
		grid.add(vacio2, 2, 2);
		grid.add(txtAnio, 3, 2);
		grid.add(lblFechaInicio, 1, 3);
		grid.add(vacio3, 2, 3);
		grid.add(datePickerFechaInicioFX, 3, 3);
		grid.add(lblFechaFin, 1, 4);
		grid.add(vacio4, 2, 4);
		grid.add(datePickerFechaFinFX, 3, 4);
		grid.add(lblSemestre, 1, 5);
		grid.add(vacio5, 2, 5);
		grid.add(txtSemestre, 3, 5);

		TableColumn<TablaHorario, String> colHorario_id = new TableColumn<>("Id");
		TableColumn<TablaHorario, String> colHorario_dia = new TableColumn<>("Dia");
		TableColumn<TablaHorario, String> colHorario_horaInicio = new TableColumn<>("Hora Inicio");
		TableColumn<TablaHorario, String> colHorario_horaFin = new TableColumn<>("Hora Fin");
		TableColumn<TablaHorario, String> colHorario_btnEditar = new TableColumn<>();
		TableColumn<TablaHorario, String> colHorario_btnEliminar = new TableColumn<>();
		getTablaHorarios().getColumns().clear();
		getTablaHorarios().getColumns().addAll(colHorario_id, colHorario_dia, colHorario_horaInicio,colHorario_horaFin, colHorario_btnEditar, colHorario_btnEliminar);

		topBox.getChildren().addAll(lblHorarios,btnIngresarHorario);

		border.setTop(topBox);
		border.setCenter(getTablaHorarios());

		lblHorarios.setAlignment(Pos.CENTER);
		btnIngresarHorario.setAlignment(Pos.CENTER_RIGHT);

		try {
			colHorario_id.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colIdHorario"));
			colHorario_dia.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colDiaHorario"));
			colHorario_horaInicio.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colHoraInicioHorario"));
			colHorario_horaFin.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colHoraFinHorario"));
			colHorario_btnEditar.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colEditarHorario"));
			colHorario_btnEliminar.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colEliminarHorario"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		actualizarDatosTablaHorario();
		colHorario_id.setVisible(false);
		dialog.getDialogPane().setContent(box);
		ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, Curso>() {
		    @Override
		    public Curso call(ButtonType b) {
		    	if (b == buttonTypeOk) {
		    		List<Curso> lstCursos = Prueba.getLstCurso();
		    		if(lstCursos != null) {
		    			if(!lstCursos.isEmpty()) {
		    				Curso curso = lstCursos.stream().filter(c -> c.getId() == Integer.parseInt(getColId())).findFirst().get();
		    				if(curso != null) {
		    					LocalDate localDateInicio = datePickerFechaInicioFX.getValue();
		    					Instant instantInicio = Instant.from(localDateInicio.atStartOfDay(ZoneId.systemDefault()));
		    					Date dateInicio = Date.from(instantInicio);
		    					System.out.println(localDateInicio + "\n" + instantInicio + "\n" + dateInicio);

		    					curso.setFechaInicio(Date.from(instantInicio));

		    					LocalDate localDateFin = datePickerFechaFinFX.getValue();
		    					Instant instantFin = Instant.from(localDateFin.atStartOfDay(ZoneId.systemDefault()));
		    					Date dateFin = Date.from(instantFin);
		    					System.out.println(localDateFin + "\n" + instantFin + "\n" + dateFin);

		    					curso.setFechaFin(Date.from(instantFin));
		    					curso.setSemestre(Integer.parseInt(txtSemestre.getText()));
		    					CursoService cs = new CursoService();
		    		    		alertConfirmacionModificarCurso(cs.modificarCursoResponse(curso));
		    				}
		    			}
		    		}
		        }
		        return null;
		    }
		});
		dialog.showAndWait();
	}

	private void dialogIngresarHorario() {
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setHeight(300d);
		try {
			FlowPane main = new FlowPane();
	        main.setVgap(20);
	        main.setHgap(20);

	        JFXTimePicker tpHoraInicio = new JFXTimePicker();
	        JFXTimePicker tpHoraFin = new JFXTimePicker();
			Label lblDia = new Label("Dia");
			Label vacio1 = new Label("\t");
			Label vacio2 = new Label("\t");
			Label vacio3 = new Label("\t");
			Label lblHoraInicio = new Label("Hora Inicio");
			Label lblHoraFin = new Label("Hora Fin");

			ObservableList<String> dataDias = FXCollections.observableArrayList();
			dataDias.add("Lunes");
			dataDias.add("Martes");
			dataDias.add("Miercoles");
			dataDias.add("Jueves");
			dataDias.add("Viernes");

			JFXComboBox<String> cboDia = new JFXComboBox<>(dataDias);

			tpHoraInicio.setDefaultColor(Color.valueOf("#3f51b5"));
			tpHoraInicio.setOverLay(true);
			tpHoraFin.setDefaultColor(Color.valueOf("#3f51b5"));
			tpHoraFin.setOverLay(true);

			GridPane grid = new GridPane();

			grid.add(lblDia, 1, 1);
			grid.add(vacio1, 2, 1);
			grid.add(cboDia, 3, 1);
			grid.add(lblHoraInicio, 1, 2);
			grid.add(vacio2, 2, 2);
			grid.add(tpHoraInicio, 3, 2);
			grid.add(lblHoraFin, 1, 3);
			grid.add(vacio3, 2, 3);
			grid.add(tpHoraFin, 3, 3);

			JFXButton btnModificar = new JFXButton("Ingresar");
			btnModificar.setStyle("-fx-background-color: green; -fx-pref-width: 100px; -fx-pref-height: 30px;");

			btnModificar.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
		    		Variables.getLstHorarios().add(new Horario(cboDia.getSelectionModel().getSelectedItem(),tpHoraFin.getValue().toString(),tpHoraInicio.getValue().toString()));
		    		alertConfirmacionIngresarHorario(true);
				}
			});

			main.getChildren().add(grid);
			main.getChildren().add(btnModificar);

	        StackPane pane = new StackPane();
	        pane.getChildren().add(main);
	        StackPane.setMargin(main, new Insets(100));
	        pane.setStyle("-fx-background-color:WHITE");

	        final Scene scene = new Scene(pane, 400, 700);

	        primaryStage.setTitle("Ingreso de nuevo horario");
	        primaryStage.setScene(scene);
	        primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
//
//		//TODO ASD
//		Dialog<Horario> dialog = new Dialog<>();
//
//		GridPane grid = new GridPane();
//
//		Label lblDia = new Label("Dia");
//		Label lblHoraInicio = new Label("Hora inicio");
//		Label lblHoraFin = new Label("Hora fin");
//
//		TextField txtDia = new TextField();
//		TextField txtHoraInicio = new TextField();
//		TextField txtHoraFin = new TextField();
//
//		dialog.setTitle("Horarios");
//		dialog.setHeaderText("Ingreso de nuevo horario");
//		dialog.setResizable(true);
//
//		grid.add(lblDia, 1, 1);
//		grid.add(txtDia, 2, 1);
//		grid.add(lblHoraInicio, 1, 2);
//		grid.add(txtHoraInicio, 2, 2);
//		grid.add(lblHoraFin, 1, 3);
//		grid.add(txtHoraFin, 2, 3);
//
//		dialog.getDialogPane().setContent(grid);
//		ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
//		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
//		dialog.setResultConverter(new Callback<ButtonType, Horario>() {
//		    @Override
//		    public Horario call(ButtonType b) {
//		    	if (b == buttonTypeOk) {
//		    		Variables.getLstHorarios().add(new Horario(txtDia.getText(),txtHoraFin.getText(),txtHoraInicio.getText()));
//		    		alertConfirmacionIngresarHorario(true);
//		        }
//		        return null;
//		    }
//		});
//		dialog.showAndWait();
	}

	private void alertConfirmacionIngresarHorario(boolean respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Ingreso de Horario");

		if(respuesta) {
			dialogo.setContentText("Se ha ingresado el horario satisfactoriamente.");
		}else {
			dialogo.setContentText("No se pudo ingresar el horario.");
		}
		actualizarDatosTablaHorario();
		dialogo.showAndWait();
	}

	public void actualizarDatosTablaHorario() {
		getDataHorario().clear();
		List<Curso> lstCursos = Prueba.getLstCurso();
		if(lstCursos != null) {
			if(!lstCursos.isEmpty()) {
				Curso curso = lstCursos.stream().filter(c -> c.getId() == Integer.parseInt(getColId())).findFirst().get();
				if(curso != null) {
					Variables.setLstHorarios(curso.getHorarios());
					if(Variables.getLstHorarios() != null) {
						Variables.getLstHorarios().forEach(horario -> {
							String id = "" + horario.getId();
							colEditarHorario = new JFXButton("Editar");
							colEliminarHorario = new JFXButton("Eliminar");

							this.colEditarHorario.setStyle("-fx-background-color: yellow; -fx-pref-width: 80px; -fx-pref-height: 30px;");
							this.colEliminarHorario.setStyle("-fx-background-color: red; -fx-pref-width: 80px; -fx-pref-height: 30px;");

							this.colEditarHorario.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									dialogModificarHorario();
								}
							});

							this.colEliminarHorario.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									alertEliminarHorario();
								}
							});
							TablaHorario entry = new TablaHorario(id, horario.getDia(), horario.getHoraInicio(), horario.getHoraFin(), colEditarHorario, colEliminarHorario);
							getDataHorario().add(entry);
						});
					}
				}
			}
		}
		getTablaHorarios().setItems(getDataHorario());
	}

	public void actualizarDatosTablaEstudiante() {
		getDataEstudiantes().clear();
		List<Curso> lstCursos = Prueba.getLstCurso();
		if(lstCursos != null) {
			if(!lstCursos.isEmpty()) {
				Curso curso = lstCursos.stream().filter(c -> c.getId() == Integer.parseInt(getColId())).findFirst().get();
				if(curso != null) {
					Variables.setLstEstudiantes(curso.getCursoEstudiante());
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


	private void alertConfirmacionModificarCurso(ServerResponse respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Modificacion del Curso");
		try {
			dialogo.setContentText(respuesta.getMensaje());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		Prueba.actualizarDatosTablaCurso();
		dialogo.showAndWait();
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
								lstNotasCargadas.add(new Curso_Estudiante(estudiante.getId(),estudiante.getEstado(),estudiante.getNota(),estudiante.getNombre(),estudiante.getApellido(),estudiante.getCedula(),estudiante.getId_curso(),estudiante.getId_estudiante()));
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

	@SuppressWarnings("unchecked")
	private void alertHorariosCurso() {
		Dialog<List<Horario>> dialogo = new Dialog<>();
		dialogo.setTitle("Horarios Curso");
		dialogo.setHeaderText("Horarios Curso");
		dialogo.setResizable(true);

		TableColumn<TablaHorario, String> colHorario_id = new TableColumn<>("Id");
		TableColumn<TablaHorario, String> colHorario_dia = new TableColumn<>("Dia");
		TableColumn<TablaHorario, String> colHorario_horaInicio = new TableColumn<>("Hora Inicio");
		TableColumn<TablaHorario, String> colHorario_horaFin = new TableColumn<>("Hora Fin");
		getTablaHorarios().getColumns().clear();
		getTablaHorarios().getColumns().addAll(colHorario_id, colHorario_dia, colHorario_horaInicio,colHorario_horaFin);

		try {
			colHorario_id.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colIdHorario"));
			colHorario_dia.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colDiaHorario"));
			colHorario_horaInicio.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colHoraInicioHorario"));
			colHorario_horaFin.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colHoraFinHorario"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		actualizarDatosTablaHorario();
		colHorario_id.setVisible(false);
		dialogo.getDialogPane().setContent(getTablaHorarios());
		ButtonType buttonTypeOk = new ButtonType("Volver", ButtonData.BACK_PREVIOUS);
		dialogo.getDialogPane().getButtonTypes().add(buttonTypeOk);
		dialogo.showAndWait();
	}

	@SuppressWarnings("unchecked")
	private void alertEstudiantesCurso() {

		Dialog<List<Estudiante>> dialogo = new Dialog<>();
		dialogo.setTitle("Estudiantes del Curso");
		dialogo.setHeaderText("Estudiantes del Curso");
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

	private void alertConfirmacionCargarCalificacionesCurso(ServerResponse respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Calificaciones del Curso");
		dialogo.setContentText(respuesta.getMensaje());
		Prueba.actualizarDatosTablaCurso();
		dialogo.showAndWait();
	}

	private void alertConfirmacionEliminarCurso(ServerResponse respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Eliminar Curso");
		dialogo.setContentText(respuesta.getMensaje());
		Prueba.actualizarDatosTablaCurso();
		dialogo.showAndWait();
	}

	public String getColId() {
		return colId.get();
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

	public String getColAnio() {
		return this.colAnio.get();
	}

	public void setColAnio(String colAnio) {
		this.colAnio.set(colAnio);
	}

	public Date getColFechaInicio() {
		return this.colFechaInicio.get();
	}

	public void setColFechaInicio(Date colFechaInicio) {
		this.colFechaInicio.set(colFechaInicio);
	}

	public Date getColFechaFin() {
		return this.colFechaFin.get();
	}

	public void setColFechaFin(Date colFechaFin) {
		this.colFechaFin.set(colFechaFin);
	}

	public String getColSemestre() {
		return this.colSemestre.get();
	}

	public void setColSemestre(String colSemestre) {
		this.colSemestre.set(colSemestre);
	}

	public JFXButton getColHorarios() {
		return this.colHorarios.get();
	}

	public void setColHorarios(JFXButton colHorarios) {
		this.colHorarios.set(colHorarios);
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

	public JFXButton getColEstudiantes() {
		return this.colEstudiantes.get();
	}

	public void setColEstudiantes(JFXButton colEstudiantes) {
		this.colEstudiantes.set(colEstudiantes);
	}

	public ObservableList<TablaHorario> getDataHorario() {
		return dataHorario;
	}

	public void setDataHorario(ObservableList<TablaHorario> dataHorario) {
		this.dataHorario = dataHorario;
	}

	public TableView<TablaHorario> getTablaHorarios() {
		return tablaHorarios;
	}

	public void setTablaHorarios(TableView<TablaHorario> tablaHorarios) {
		this.tablaHorarios = tablaHorarios;
	}

	public TableView<TablaEstudiantes> getTablaEstudiantes() {
		return tablaEstudiantes;
	}

	public void setTablaEstudiantes(TableView<TablaEstudiantes> tablaEstudiantes) {
		this.tablaEstudiantes = tablaEstudiantes;
	}

	public ObservableList<TablaEstudiantes> getDataEstudiantes() {
		return dataEstudiantes;
	}

	public void setDataEstudiantes(ObservableList<TablaEstudiantes> dataEstudiantes) {
		this.dataEstudiantes = dataEstudiantes;
	}

}
