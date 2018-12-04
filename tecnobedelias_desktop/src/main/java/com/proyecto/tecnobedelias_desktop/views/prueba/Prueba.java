package com.proyecto.tecnobedelias_desktop.views.prueba;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.proyecto.tecnobedelias_desktop.global.Constants;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.global.Variables;
import com.proyecto.tecnobedelias_desktop.model.Actividad;
import com.proyecto.tecnobedelias_desktop.model.AsignaturaCarrera;
import com.proyecto.tecnobedelias_desktop.model.Carrera;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import com.proyecto.tecnobedelias_desktop.model.Examen;
import com.proyecto.tecnobedelias_desktop.model.Horario;
import com.proyecto.tecnobedelias_desktop.model.ServerResponse;
import com.proyecto.tecnobedelias_desktop.model.TablaCurso;
import com.proyecto.tecnobedelias_desktop.model.TablaExamen;
import com.proyecto.tecnobedelias_desktop.model.TablaHorario;
import com.proyecto.tecnobedelias_desktop.model.Usuario;
import com.proyecto.tecnobedelias_desktop.service.CarreraService;
import com.proyecto.tecnobedelias_desktop.service.CursoService;
import com.proyecto.tecnobedelias_desktop.service.ExamenService;
import com.proyecto.tecnobedelias_desktop.service.UsuarioService;
import com.proyecto.tecnobedelias_desktop.utils.GeneratePDFFileIText;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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

public class Prueba implements Initializable {

	@FXML
	Label lblOperacion;
	@FXML
	Label lblTituloOperacion;
	@FXML
	JFXButton btnVolver;
	@FXML
	JFXButton btnCursos;
	@FXML
	JFXButton btnExamenes;
	@FXML
	JFXButton btnEscolaridades;
	@FXML
	JFXButton btnLogout;
	@FXML
	JFXButton btnAgregarCurso;
	@FXML
	JFXButton btnAgregarExamen;
	@FXML
	JFXButton btnPdf;
	@FXML
	BorderPane cursosPane;
	@FXML
	BorderPane examenesPane;
	@FXML
	BorderPane escolaridadesPane;
	@FXML
	BorderPane operacionesPane;
	@FXML
	AnchorPane listarCarrerasPane;
	@FXML
	AnchorPane listarAsignaturasPane;
	@FXML
	AnchorPane listarCursosPane;
	@FXML
	AnchorPane ingresarCursoPane;
	@FXML
	AnchorPane ingresarExamenPane;
	@FXML
	StackPane stackPane;
	@FXML
	StackPane stackOperationPane;

	@FXML
	TableView<TablaCurso> tablaCursos;
	@FXML
	TableColumn<TablaCurso, String> colIdCursos;
	@FXML
	TableColumn<TablaCurso, String> colAsignaturaCursos;
	@FXML
	TableColumn<TablaCurso, String> colAnioCursos;
	@FXML
	TableColumn<TablaCurso, String> colFechaInicioCursos;
	@FXML
	TableColumn<TablaCurso, String> colFechaFinCursos;
	@FXML
	TableColumn<TablaCurso, String> colSemestreCursos;
	@FXML
	TableColumn<TablaCurso, String> colHorariosCursos;
	@FXML
	TableColumn<TablaCurso, String> colEditarCursos;
	@FXML
	TableColumn<TablaCurso, String> colEliminarCursos;
	@FXML
	TableColumn<TablaCurso, String> colActaCursos;
	@FXML
	TableColumn<TablaCurso, String> colCargarCalificacionesCursos;
	@FXML
	TableColumn<TablaCurso, String> colEstudiantesCursos;

	@FXML
	TableView<TablaExamen> tablaExamenes;
	@FXML
	TableColumn<TablaExamen, String> colIdExamenes;
	@FXML
	TableColumn<TablaExamen, String> colAsignaturaExamenes;
	@FXML
	TableColumn<TablaExamen, String> colFechaExamenes;
	@FXML
	TableColumn<TablaExamen, String> colHoraExamenes;
	@FXML
	TableColumn<TablaExamen, String> colEditarExamenes;
	@FXML
	TableColumn<TablaExamen, String> colEliminarExamenes;
	@FXML
	TableColumn<TablaExamen, String> colActaExamenes;
	@FXML
	TableColumn<TablaExamen, String> colCargarCalificacionesExamenes;
	@FXML
	TableColumn<TablaExamen, String> colEstudiantesExamenes;

	final static ObservableList<TablaCurso> dataCurso = FXCollections.observableArrayList();
	final static ObservableList<TablaExamen> dataExamen = FXCollections.observableArrayList();
	private ObservableList<TablaHorario> dataHorario = FXCollections.observableArrayList();

	private TableView<TablaHorario> tablaHorarios = new TableView<>();

	private static List<Examen> lstExamen;
	private static List<Curso> lstCurso;

	private JFXButton colEditarHorario;
	private JFXButton colEliminarHorario;

	static TablaCurso entry = null;
	static int gridPanelHeight = 1;
	static String nombreAsignatura;
	static Carrera carrera;

	@SuppressWarnings("deprecation")
	public void logoutButtonPushed(ActionEvent event) throws IOException {
		try {
			Token.getInstance().setToken(null);
			Token.getInstance().setMensaje(null);
			URL formUrl = new File(Constants.getLoginPackage() + "login.fxml").toURL();
			URL cssUrl = new File(Constants.getLoginPackage() + "login.css").toURL();
			AnchorPane root = FXMLLoader.load(formUrl);
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(cssUrl.toExternalForm());
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.setMaximized(false);
			window.centerOnScreen();
			window.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void btnAgregarCursoActionListener(ActionEvent event) {
		// operacionesPaneToFront();
		nombreAsignatura = null;
		Dialog<Curso> dialog = new Dialog<>();
		JFXDatePicker datePickerFechaInicioFX = new JFXDatePicker();
		JFXDatePicker datePickerFechaFinFX = new JFXDatePicker();
		JFXButton btnIngresarHorario = new JFXButton("Ingresar Horario");
		inicializarOperacion("INGRESAR CURSO");
		CarreraService cs = new CarreraService();
		ObservableList<Carrera> dataCarreras = FXCollections.observableArrayList();
		ObservableList<AsignaturaCarrera> dataAsignaturas = FXCollections.observableArrayList();
		List<Carrera> carreras = cs.listarCarrerasResponse();

		Label lblCarreras = new Label("Carrera");
		Label lblAsignatura = new Label("Asignatura");
		ComboBox<Carrera> cboCarreras = new ComboBox<>();
		ComboBox<AsignaturaCarrera> cboAsignaturas = new ComboBox<>();
		Curso curso = new Curso();

		Label lblNombre = new Label("Nombre");
		Label lblAnio = new Label("Año");
		Label lblFechaFin = new Label("Fecha Fin");
		Label lblFechaInicio = new Label("Fecha Inicio");
		Label lblSemestre = new Label("Semestre");
		Label lblHorarios = new Label("Horarios");

		lblAsignatura.setVisible(false);
		lblNombre.setVisible(false);
		lblAnio.setVisible(false);
		lblFechaFin.setVisible(false);
		lblFechaInicio.setVisible(false);
		lblSemestre.setVisible(false);

		TextField txtNombre = new TextField();
		TextField txtAnio = new TextField();
		TextField txtSemestre = new TextField();

		txtNombre.setVisible(false);
		txtAnio.setVisible(false);
		txtSemestre.setVisible(false);

		datePickerFechaInicioFX.setPromptText("Elige una fecha");
		datePickerFechaFinFX.setPromptText("Elige una fecha");

		datePickerFechaInicioFX.setVisible(false);
		datePickerFechaFinFX.setVisible(false);

		cboAsignaturas.setVisible(false);

		VBox box = new VBox();
		HBox topBox = new HBox();
		GridPane grid = new GridPane();
		BorderPane border = new BorderPane();

		box.getChildren().addAll(grid, border);
		dialog.getDialogPane().setContent(box);

		dialog.setTitle("Ingresar Curso");
		dialog.setHeaderText("Ingresar Curso");
		dialog.setResizable(true);

		TableColumn<TablaHorario, String> colHorario_id = new TableColumn<>("Id");
		TableColumn<TablaHorario, String> colHorario_dia = new TableColumn<>("Dia");
		TableColumn<TablaHorario, String> colHorario_horaInicio = new TableColumn<>("Hora Inicio");
		TableColumn<TablaHorario, String> colHorario_horaFin = new TableColumn<>("Hora Fin");
		TableColumn<TablaHorario, String> colHorario_btnEditar = new TableColumn<>();
		TableColumn<TablaHorario, String> colHorario_btnEliminar = new TableColumn<>();
		getTablaHorarios().getColumns().clear();
		getTablaHorarios().getColumns().addAll(colHorario_id, colHorario_dia, colHorario_horaInicio, colHorario_horaFin,
				colHorario_btnEditar, colHorario_btnEliminar);

		topBox.getChildren().addAll(lblHorarios, btnIngresarHorario);

		border.setTop(topBox);
		border.setCenter(getTablaHorarios());

		lblHorarios.setAlignment(Pos.CENTER);
		btnIngresarHorario.setAlignment(Pos.CENTER_RIGHT);

		try {
			colHorario_id.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colIdHorario"));
			colHorario_dia.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colDiaHorario"));
			colHorario_horaInicio
					.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colHoraInicioHorario"));
			colHorario_horaFin.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colHoraFinHorario"));
			colHorario_btnEditar
					.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colEditarHorario"));
			colHorario_btnEliminar
					.setCellValueFactory(new PropertyValueFactory<TablaHorario, String>("colEliminarHorario"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		if (carreras == null) {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Informacion");
			alerta.setContentText(
					"No existen carreras en el sistema por favor contactese con el director para que cree alguna");
			alerta.showAndWait();
		} else {
			dataCarreras.addAll(carreras);
			cboCarreras.getItems().addAll(dataCarreras);
			carrera = null;
			cboCarreras.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					carrera = cboCarreras.getSelectionModel().getSelectedItem();
					System.out.println("Carrera seleccionada: " + carrera.toString());
					if (carrera != null) {
						List<AsignaturaCarrera> asignaturasDeCarrera = carrera.getAsignaturaCarrera();
						cboAsignaturas.setVisible(true);
						if (asignaturasDeCarrera == null) {
							Alert alerta = new Alert(Alert.AlertType.INFORMATION);
							alerta.setTitle("Informacion");
							alerta.setContentText(
									"No existen asignaturas para la carrera seleccionada en el sistema por favor contactese con el director para que cree alguna");
							alerta.showAndWait();
						} else {
							lblAsignatura.setVisible(true);
							dataAsignaturas.clear();
							dataAsignaturas.addAll(asignaturasDeCarrera);
							cboAsignaturas.getItems().clear();
							cboAsignaturas.getItems().addAll(dataAsignaturas);
							cboAsignaturas.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									nombreAsignatura = cboAsignaturas.getSelectionModel().getSelectedItem().getAsignatura().getNombre();
									lblAnio.setVisible(true);
									lblFechaFin.setVisible(true);
									lblFechaInicio.setVisible(true);
									lblSemestre.setVisible(true);
									txtAnio.setVisible(true);
									datePickerFechaInicioFX.setVisible(true);
									datePickerFechaFinFX.setVisible(true);
									txtSemestre.setVisible(true);
								}
							});
						}
					}else {
						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
						alerta.setTitle("Informacion");
						alerta.setContentText("no hay carrera");
						alerta.showAndWait();
					}
				}
			});
		}

		grid.add(lblCarreras, 1, 1);
		grid.add(cboCarreras, 2, 1);
		grid.add(lblAsignatura, 1, 2);
		grid.add(cboAsignaturas, 2, 2);
		grid.add(lblAnio, 1, 4);
		grid.add(txtAnio, 2, 4);
		grid.add(lblFechaInicio, 1, 5);
		grid.add(datePickerFechaInicioFX, 2, 5);
		grid.add(lblFechaFin, 1, 6);
		grid.add(datePickerFechaFinFX, 2, 6);
		grid.add(lblSemestre, 1, 7);
		grid.add(txtSemestre, 2, 7);

		btnIngresarHorario.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialogIngresarHorario(curso);
			}
		});

		actualizarDatosTablaHorario(curso);

		colHorario_id.setVisible(false);
		dialog.getDialogPane().setContent(box);
		ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, Curso>() {
			@Override
			public Curso call(ButtonType b) {
				if (b == buttonTypeOk) {
					curso.setAnio(Integer.parseInt(txtAnio.getText()));
					curso.setFechaFin(Date.from(Instant.from(datePickerFechaFinFX.getValue().atStartOfDay(ZoneId.systemDefault()))));
					curso.setFechaInicio(Date.from(Instant.from(datePickerFechaInicioFX.getValue().atStartOfDay(ZoneId.systemDefault()))));
					curso.setNombreAsignatura(nombreAsignatura);
					curso.setSemestre(Integer.parseInt(txtSemestre.getText()));
					CursoService cs = new CursoService();
					alertConfirmacionIngresarCurso(cs.ingresarCursoResponse(curso));
				}
				return null;
			}
		});
		dialog.showAndWait();
	}

	public void btnAgregarExamenActionListener(ActionEvent event) {
		// operacionesPaneToFront();
		nombreAsignatura = null;
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setHeight(300d);
		try {
			FlowPane main = new FlowPane();
	        main.setVgap(20);
	        main.setHgap(20);

	        JFXTimePicker tpHora = new JFXTimePicker();
	        inicializarOperacion("INGRESAR EXAMEN");
			CarreraService cs = new CarreraService();
			JFXDatePicker datePickerFechaFX = new JFXDatePicker();
			ObservableList<Carrera> dataCarreras = FXCollections.observableArrayList();
			ObservableList<AsignaturaCarrera> dataAsignaturas = FXCollections.observableArrayList();
			List<Carrera> carreras = cs.listarCarrerasResponse();
			Label lblCarreras = new Label("Carrera");
			Label lblAsignatura = new Label("Asignatura");
			ComboBox<Carrera> cboCarreras = new ComboBox<>();
			ComboBox<AsignaturaCarrera> cboAsignaturas = new ComboBox<>();
			Examen examen = new Examen();

			tpHora.setDefaultColor(Color.valueOf("#3f51b5"));
			tpHora.setOverLay(true);

			Label lblFecha = new Label("Fecha");
			Label lblHora = new Label("Hora");
			Label vacio1 = new Label("\t");
			Label vacio2 = new Label("\t");
			Label vacio3 = new Label("\t");
			Label vacio4 = new Label("\t");

			lblAsignatura.setVisible(false);
//			vacio1.setVisible(false);
//			vacio2.setVisible(false);
//			vacio3.setVisible(false);
//			vacio4.setVisible(false);
			lblFecha.setVisible(false);
			lblHora.setVisible(false);
			tpHora.setVisible(false);
			datePickerFechaFX.setVisible(false);

			cboAsignaturas.setVisible(false);

			GridPane grid = new GridPane();

			if (carreras == null) {
				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("Informacion");
				alerta.setContentText(
						"No existen carreras en el sistema por favor contactese con el director para que cree alguna");
				alerta.showAndWait();
			}else{
				dataCarreras.addAll(carreras);
				cboCarreras.getItems().addAll(dataCarreras);
				carrera = null;
				cboCarreras.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						carrera = cboCarreras.getSelectionModel().getSelectedItem();
						System.out.println("Carrera seleccionada: " + carrera.toString());
						if (carrera != null) {
							List<AsignaturaCarrera> asignaturasDeCarrera = carrera.getAsignaturaCarrera();
							cboAsignaturas.setVisible(true);
							if (asignaturasDeCarrera == null) {
								Alert alerta = new Alert(Alert.AlertType.INFORMATION);
								alerta.setTitle("Informacion");
								alerta.setContentText(
										"No existen asignaturas para la carrera seleccionada en el sistema por favor contactese con el director para que cree alguna");
								alerta.showAndWait();
							} else {
								//TODO: FIX BUG
								lblAsignatura.setVisible(true);
								dataAsignaturas.clear();
								dataAsignaturas.addAll(asignaturasDeCarrera);
								cboAsignaturas.getItems().clear();
								cboAsignaturas.getItems().addAll(dataAsignaturas);
								cboAsignaturas.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										nombreAsignatura = cboAsignaturas.getSelectionModel().getSelectedItem().getAsignatura().getNombre();
										lblFecha.setVisible(true);
										lblHora.setVisible(true);
										datePickerFechaFX.setVisible(true);
										tpHora.setVisible(true);
									}
								});
							}
						}else {
							Alert alerta = new Alert(Alert.AlertType.INFORMATION);
							alerta.setTitle("Informacion");
							alerta.setContentText("no hay carrera");
							alerta.showAndWait();
						}
					}
				});
			}

			grid.add(lblCarreras, 1, 1);
			grid.add(vacio1, 2, 1);
			grid.add(cboCarreras, 3, 1);
			grid.add(lblAsignatura, 1, 2);
			grid.add(vacio2, 2, 2);
			grid.add(cboAsignaturas, 3, 2);
			grid.add(lblFecha, 1, 3);
			grid.add(vacio3, 2, 3);
			grid.add(datePickerFechaFX, 3, 3);
			grid.add(lblHora, 1, 4);
			grid.add(vacio4, 2, 4);
			grid.add(tpHora, 3, 4);

			JFXButton btnIngresar = new JFXButton("Ingresar");
			btnIngresar.setStyle("-fx-background-color: green; -fx-pref-width: 100px; -fx-pref-height: 30px;");

			btnIngresar.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					LocalDate localDate = datePickerFechaFX.getValue();
					Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
					Date dateInicio = Date.from(instant);
					System.out.println(localDate + "\n" + instant + "\n" + dateInicio);

					examen.setFecha(Date.from(instant));

					examen.setHora(tpHora.getValue().toString());
					examen.setNombreAsignatura(nombreAsignatura);
					ExamenService cs = new ExamenService();
					alertConfirmacionIngresarExamen(cs.ingresarExamenResponse(examen));
				}
			});

			main.getChildren().add(grid);
			main.getChildren().add(btnIngresar);

	        StackPane pane = new StackPane();
	        pane.getChildren().add(main);
	        StackPane.setMargin(main, new Insets(100));
	        pane.setStyle("-fx-background-color:WHITE");

	        final Scene scene = new Scene(pane, 400, 700);

	        primaryStage.setTitle("Ingresar Examen");
	        primaryStage.setScene(scene);
	        primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}

		/***************************************************************/
//
//		JFXTimePicker tpHora = new JFXTimePicker();
//		Dialog<Examen> dialog = new Dialog<>();
//		inicializarOperacion("INGRESAR EXAMEN");
//		CarreraService cs = new CarreraService();
//		JFXDatePicker datePickerFechaFX = new JFXDatePicker();
//		ObservableList<Carrera> dataCarreras = FXCollections.observableArrayList();
//		ObservableList<AsignaturaCarrera> dataAsignaturas = FXCollections.observableArrayList();
//		List<Carrera> carreras = cs.listarCarrerasResponse();
//		Label lblCarreras = new Label("Carrera");
//		Label lblAsignatura = new Label("Asignatura");
//		ComboBox<Carrera> cboCarreras = new ComboBox<>();
//		ComboBox<AsignaturaCarrera> cboAsignaturas = new ComboBox<>();
//		Examen examen = new Examen();
//
//		tpHora.setDefaultColor(Color.valueOf("#3f51b5"));
//		tpHora.setOverLay(true);
//
//		Label lblFecha = new Label("Fecha");
//		Label lblHora = new Label("Hora");
//
//		lblAsignatura.setVisible(false);
//		lblFecha.setVisible(false);
//		lblHora.setVisible(false);
//		tpHora.setVisible(false);
//		datePickerFechaFX.setVisible(false);
//
//		cboAsignaturas.setVisible(false);
//
//		GridPane grid = new GridPane();
//
//		dialog.setTitle("Ingresar Examen");
//		dialog.setHeaderText("Ingresar Examen");
//		dialog.setResizable(true);
//
//		if (carreras == null) {
//			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
//			alerta.setTitle("Informacion");
//			alerta.setContentText(
//					"No existen carreras en el sistema por favor contactese con el director para que cree alguna");
//			alerta.showAndWait();
//		}else{
//			dataCarreras.addAll(carreras);
//			cboCarreras.getItems().addAll(dataCarreras);
//			carrera = null;
//			cboCarreras.setOnAction(new EventHandler<ActionEvent>() {
//
//				@Override
//				public void handle(ActionEvent event) {
//					carrera = cboCarreras.getSelectionModel().getSelectedItem();
//					System.out.println("Carrera seleccionada: " + carrera.toString());
//					if (carrera != null) {
//						List<AsignaturaCarrera> asignaturasDeCarrera = carrera.getAsignaturaCarrera();
//						cboAsignaturas.setVisible(true);
//						if (asignaturasDeCarrera == null) {
//							Alert alerta = new Alert(Alert.AlertType.INFORMATION);
//							alerta.setTitle("Informacion");
//							alerta.setContentText(
//									"No existen asignaturas para la carrera seleccionada en el sistema por favor contactese con el director para que cree alguna");
//							alerta.showAndWait();
//						} else {
//							//TODO: FIX BUG
//							lblAsignatura.setVisible(true);
//							dataAsignaturas.clear();
//							dataAsignaturas.addAll(asignaturasDeCarrera);
//							cboAsignaturas.getItems().clear();
//							cboAsignaturas.getItems().addAll(dataAsignaturas);
//							cboAsignaturas.setOnAction(new EventHandler<ActionEvent>() {
//
//								@Override
//								public void handle(ActionEvent event) {
//									nombreAsignatura = cboAsignaturas.getSelectionModel().getSelectedItem().getAsignatura().getNombre();
//									lblFecha.setVisible(true);
//									lblHora.setVisible(true);
//									datePickerFechaFX.setVisible(true);
//									tpHora.setVisible(true);
//								}
//							});
//						}
//					}else {
//						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
//						alerta.setTitle("Informacion");
//						alerta.setContentText("no hay carrera");
//						alerta.showAndWait();
//					}
//				}
//			});
//		}
//
//		grid.add(lblCarreras, 1, 1);
//		grid.add(cboCarreras, 2, 1);
//		grid.add(lblAsignatura, 1, 2);
//		grid.add(cboAsignaturas, 2, 2);
//		grid.add(lblFecha, 1, 4);
//		grid.add(datePickerFechaFX, 2, 4);
//		grid.add(lblHora, 1, 5);
//		grid.add(tpHora, 2, 5);
//
//		dialog.getDialogPane().setContent(grid);
//		ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
//		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
//		dialog.setResultConverter(new Callback<ButtonType, Examen>() {
//			@Override
//			public Examen call(ButtonType b) {
//				if (b == buttonTypeOk) {
//
//					LocalDate localDate = datePickerFechaFX.getValue();
//					Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
//					Date dateInicio = Date.from(instant);
//					System.out.println(localDate + "\n" + instant + "\n" + dateInicio);
//
//					examen.setFecha(Date.from(instant));
//
//					examen.setHora(tpHora.getValue().toString());
//					examen.setNombreAsignatura(nombreAsignatura);
//					ExamenService cs = new ExamenService();
//					alertConfirmacionIngresarExamen(cs.ingresarExamenResponse(examen));
//				}
//				return null;
//			}
//		});
//		dialog.showAndWait();
	}

	private void alertConfirmacionIngresarExamen(ServerResponse respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Ingresar Examen");
		dialogo.setContentText(respuesta.getMensaje());
		actualizarDatosTablaExamen();
		dialogo.showAndWait();
	}

	private void alertConfirmacionIngresarCurso(ServerResponse respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Ingresar Curso");
		dialogo.setContentText(respuesta.getMensaje());
		actualizarDatosTablaCurso();
		dialogo.showAndWait();
	}

	private void dialogIngresarHorario(Curso curso) {

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
		    		alertConfirmacionIngresarHorario(true, curso);
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
//			@Override
//			public Horario call(ButtonType b) {
//				if (b == buttonTypeOk) {
//					Variables.getLstHorarios()
//							.add(new Horario(txtDia.getText(), txtHoraFin.getText(), txtHoraInicio.getText()));
//					alertConfirmacionIngresarHorario(true, curso);
//				}
//				return null;
//			}
//		});
//		dialog.showAndWait();
	}

	private void alertConfirmacionIngresarHorario(boolean respuesta, Curso curso) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Ingreso de Horario");

		if (respuesta) {
			dialogo.setContentText("Se ha ingresado el horario satisfactoriamente.");
		} else {
			dialogo.setContentText("No se pudo ingresar el horario.");
		}
		actualizarDatosTablaHorario(curso);
		dialogo.showAndWait();
	}

	public void actualizarDatosTablaHorario(Curso curso) {
		getDataHorario().clear();
		if (curso != null) {
			Variables.setLstHorarios(curso.getHorarios());
			if (Variables.getLstHorarios() != null) {
				Variables.getLstHorarios().forEach(horario -> {
					String id = "" + horario.getId();
					colEditarHorario = new JFXButton("Editar");
					colEliminarHorario = new JFXButton("Eliminar");

					this.colEditarHorario
							.setStyle("-fx-background-color: yellow; -fx-pref-width: 80px; -fx-pref-height: 30px;");
					this.colEliminarHorario
							.setStyle("-fx-background-color: red; -fx-pref-width: 80px; -fx-pref-height: 30px;");

					this.colEditarHorario.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							dialogModificarHorario(curso);
						}
					});

					this.colEliminarHorario.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							alertEliminarHorario(curso);
						}
					});
					TablaHorario entry = new TablaHorario(id, horario.getDia(), horario.getHoraInicio(),
							horario.getHoraFin(), colEditarHorario, colEliminarHorario);
					getDataHorario().add(entry);
				});
			}
		}

		getTablaHorarios().setItems(getDataHorario());
	}

	private void alertEliminarHorario(Curso curso) {
		Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
		dialogo.setTitle("Eliminar Horario");
		if (getTablaHorarios().getSelectionModel().getSelectedItem() == null) {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Informacion");
			alerta.setContentText("Debes de seleccionar una fila antes de proceder a eliminar el horario");
			alerta.showAndWait();
		} else {
			int index = getTablaHorarios().getSelectionModel().getSelectedIndex();
			dialogo.setContentText("Esta seguro de eliminar el horario "
					+ getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario() + "?");
			dialogo.showAndWait();
			if (dialogo.getResult().getText().equalsIgnoreCase("ACEPTAR")) {
				Variables.getLstHorarios().remove(index);
				alertConfirmacionEliminarHorario(true, curso);
			}
		}
	}

	private void alertConfirmacionEliminarHorario(boolean respuesta, Curso curso) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Eliminar Horario");

		if (respuesta) {
			dialogo.setContentText("Se ha eliminado el horario "
					+ getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario()
					+ " satisfactoriamente");
		} else {
			dialogo.setContentText("No se puede eliminar el horario");
		}
		actualizarDatosTablaHorario(curso);
		dialogo.showAndWait();
	}

	@SuppressWarnings("deprecation")
	private void dialogModificarHorario(Curso curso) {

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
		    		alertConfirmacionModificarHorario(true, curso);
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

//		Dialog<Horario> dialog = new Dialog<>();
//		Label lblDia = new Label("Dia");
//		Label lblHoraInicio = new Label("Hora de Inicio");
//		Label lblHoraFin = new Label("Hora de Finalizacion");
//		GridPane grid = new GridPane();
//
//		if (getTablaHorarios().getSelectionModel().getSelectedItem() == null) {
//			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
//			alerta.setTitle("Informacion");
//			alerta.setContentText("Debes de seleccionar una fila antes de proceder a editar el horario");
//			alerta.showAndWait();
//		} else {
//			int index = getTablaHorarios().getSelectionModel().getSelectedIndex();
//			TextField txtDia = new TextField(
//					getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario());
//			TextField txtHoraInicio = new TextField(
//					getTablaHorarios().getSelectionModel().getSelectedItem().getColHoraInicioHorario());
//			TextField txtHoraFin = new TextField(
//					getTablaHorarios().getSelectionModel().getSelectedItem().getColHoraFinHorario());
//
//			dialog.setTitle("Modificacion del horario del dia "
//					+ getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario());
//			dialog.setHeaderText("Modificacion del horario del dia "
//					+ getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario());
//			dialog.setResizable(true);
//
//			grid.add(lblDia, 1, 1);
//			grid.add(txtDia, 2, 1);
//			grid.add(lblHoraInicio, 1, 2);
//			grid.add(txtHoraInicio, 2, 2);
//			grid.add(lblHoraFin, 1, 3);
//			grid.add(txtHoraFin, 2, 3);
//
//			dialog.getDialogPane().setContent(grid);
//			ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
//			dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
//			dialog.setResultConverter(new Callback<ButtonType, Horario>() {
//				@Override
//				public Horario call(ButtonType b) {
//					if (b == buttonTypeOk) {
//						Variables.getLstHorarios().get(index).setDia(txtDia.getText());
//						Variables.getLstHorarios().get(index).setHoraInicio(txtHoraInicio.getText());
//						Variables.getLstHorarios().get(index).setHoraFin(txtHoraFin.getText());
//						alertConfirmacionModificarHorario(true, curso);
//					}
//					return null;
//				}
//			});
//			dialog.showAndWait();
//		}
	}

	private void alertConfirmacionModificarHorario(boolean respuesta, Curso curso) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Modificacion del Horario");

		if (respuesta) {
			dialogo.setContentText("Se ha modificado el horario satisfactoriamente.");
		} else {
			dialogo.setContentText("No se pudo modificar el horario.");
		}
		actualizarDatosTablaHorario(curso);
		dialogo.showAndWait();
	}

	public void cursosButtonPushed(ActionEvent event) throws IOException {
		cursosPaneToFront();
	}

	public void examenesButtonPushed(ActionEvent event) throws IOException {
		examenesPaneToFront();
	}

	public void escolaridadesButtonPushed(ActionEvent event) throws IOException {
		escolaridadesPaneToFront();
		Dialog<Usuario> dialog = new Dialog<>();
		//inicializarOperacion("ESCOLARIDADES");

		Label lblCedula = new Label("Cedula");
		TextField txtCedula = new TextField();

		GridPane grid = new GridPane();

		dialog.setTitle("Escolaridad");
		dialog.setHeaderText("Escolaridad");
		dialog.setResizable(true);

		grid.add(lblCedula, 1, 1);
		grid.add(txtCedula, 2, 1);

		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, Usuario>() {
			@Override
			public Usuario call(ButtonType b) {
				if (b == buttonTypeOk) {
					UsuarioService us = new UsuarioService();
					Usuario user = us.obtenerUsuarioPorCedulaResponse(txtCedula.getText());
					if(user != null) {
						if((user.getRoles().get(0).getNombre().equalsIgnoreCase("ESTUDIANTE"))) {
							dialogCrearEscolaridad(user);
						}else {
							Alert alerta = new Alert(Alert.AlertType.INFORMATION);
							alerta.setTitle("Informacion");
							alerta.setContentText("La cedula ingresada no corresponde con un estudiante inscripto en esta institución");
							alerta.showAndWait();
						}
					}else {
						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
						alerta.setTitle("Informacion");
						alerta.setContentText("La cedula ingresada no es correcta");
						alerta.showAndWait();
					}
				}
				return null;
			}
		});
		dialog.showAndWait();
	}

	public void dialogCrearEscolaridad(Usuario user){
		Dialog<Usuario> dialog = new Dialog<>();
		//inicializarOperacion("ESCOLARIDADES");

//		JFXButton btnEscolaridad = new JFXButton("Generar Escolaridad");
//		Label vacio = new Label("\t");

		ObservableList<Carrera> dataCarreras = FXCollections.observableArrayList();
		List<Carrera> carreras = user.getCarreras();

		Label lblCarreras = new Label("Carrera");
		ComboBox<Carrera> cboCarreras = new ComboBox<>();

		if (carreras == null) {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Informacion");
			alerta.setContentText(
					"El estudiante no esta inscripto en ninguna carrera");
			alerta.showAndWait();
		} else {
			dataCarreras.addAll(carreras);
			cboCarreras.getItems().addAll(dataCarreras);
			carrera = null;
			cboCarreras.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					carrera = cboCarreras.getSelectionModel().getSelectedItem();
					if (carrera != null) {
						System.out.println("Carrera seleccionada: " + carrera.toString());
//						btnEscolaridad.setVisible(true);
					}else {
						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
						alerta.setTitle("Informacion");
						alerta.setContentText("Debes de seleccionar una carrera para continuar");
						alerta.showAndWait();
					}
				}
			});
		}

		GridPane grid = new GridPane();

		dialog.setTitle("Escolaridad");
		dialog.setHeaderText("Escolaridad");
		dialog.setResizable(true);

		grid.add(lblCarreras, 1, 1);
		grid.add(cboCarreras, 2, 1);
//		grid.add(vacio, 2, 1);
//		grid.add(btnEscolaridad, 2, 2);

//		btnEscolaridad.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
//				generatePDFFileIText.crearEscolaridad(carrera, user);
//			}
//		});

		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Generar Escolaridad", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, Usuario>() {
			@Override
			public Usuario call(ButtonType b) {
				if (b == buttonTypeOk) {

					if (carrera == null) {
						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
						alerta.setTitle("Informacion");
						alerta.setContentText("Debes de seleccionar una carrera para continuar, vuelve a intentarlo");
						alerta.showAndWait();
					}else {
						System.out.println("Carrera seleccionada: " + carrera.toString());
						GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
						generatePDFFileIText.crearEscolaridad(carrera, user);
						try {
							Desktop.getDesktop().open(new File("src/resources/pdf/ReporteEscolaridad-DOC" + user.getCedula() + "-CARRERA" + carrera + ".pdf"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

//					UsuarioService us = new UsuarioService();
//					Usuario user = us.obtenerUsuarioPorCedulaResponse(txtCedula.getText());
//					if(user != null) {
//						dialogCrearEscolaridad(user);
//						try {
//							Desktop.getDesktop().open(new File("src/resources/pdf/ReporteEscolaridad-DOC" + txtCedula.getText() + ".pdf"));
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
				}
				return null;
			}
		});
		dialog.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicializarTablaCursos();
		inicializarTablaExamenes();
		cursosPaneToFront();
	}

	private void inicializarTablaCursos() {
		try {
			colIdCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colId"));
			colAsignaturaCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colAsignatura"));
			colAnioCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colAnio"));
			colFechaInicioCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colFechaInicio"));
			colFechaFinCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colFechaFin"));
			colSemestreCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colSemestre"));
			colHorariosCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colHorarios"));
			colEditarCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colEditar"));
			colEliminarCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colEliminar"));
			colActaCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colActa"));
			colCargarCalificacionesCursos
					.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colCargarCalificaciones"));
			colEstudiantesCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso, String>("colEstudiantes"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		actualizarDatosTablaCurso();
		tablaCursos.setItems(dataCurso);

		colIdCursos.setVisible(false);
	}

	public static void actualizarDatosTablaCurso() {
		dataCurso.clear();

		CursoService service = new CursoService();
		lstCurso = service.listarCursosResponse();
		if (lstCurso != null) {
			lstCurso.forEach(curso -> {
				String id = "" + curso.getId();
				String semestre = "" + curso.getSemestre();
				entry = new TablaCurso(id, curso.getNombreAsignatura(), curso.getAnio().toString(),
						curso.getFechaInicio(), curso.getFechaFin(), semestre);
				dataCurso.add(entry);
			});
		}
	}

	private void inicializarTablaExamenes() {
		colIdExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen, String>("colIdExamen"));
		colAsignaturaExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen, String>("colAsignaturaExamen"));
		try {
			colFechaExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen, String>("colFechaExamen"));
			colHoraExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen, String>("colHoraExamen"));
			colEditarExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen, String>("colEditarExamen"));
			colEliminarExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen, String>("colEliminarExamen"));
			colActaExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen, String>("colActaExamen"));
			colCargarCalificacionesExamenes.setCellValueFactory(
					new PropertyValueFactory<TablaExamen, String>("colCargarCalificacionesExamen"));
			colEstudiantesExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen, String>("colEstudiantesExamen"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		tablaExamenes.setItems(dataExamen);
		actualizarDatosTablaExamen();
		colIdExamenes.setVisible(false);
	}

	public static void actualizarDatosTablaExamen() {
		dataExamen.clear();
		ExamenService service = new ExamenService();
		lstExamen = service.listarExamenesResponse();
		if (lstExamen != null) {
			lstExamen.forEach(examen -> {
				String id = "" + examen.getId();
				TablaExamen entry = new TablaExamen(id, examen.getNombreAsignatura(), examen.getFecha(),
						examen.getHora());
				dataExamen.add(entry);
			});
		}
	}

	private void cursosPaneToFront() {
		inhabilitarPanelesCentrales();
		actualizarDatosTablaCurso();
		cursosPane.toFront();
		cursosPane.setVisible(true);
	}

	private void examenesPaneToFront() {
		inhabilitarPanelesCentrales();
		actualizarDatosTablaExamen();
		examenesPane.toFront();
		examenesPane.setVisible(true);
	}

	private void escolaridadesPaneToFront() {
		inhabilitarPanelesCentrales();
		escolaridadesPane.toFront();
		escolaridadesPane.setVisible(true);
	}

//	private void operacionesPaneToFront() {
//		inhabilitarPanelesCentrales();
//		operacionesPane.toFront();
//		operacionesPane.setVisible(true);
//	}

	private void inhabilitarPanelesCentrales() {
		cursosPane.setVisible(false);
		examenesPane.setVisible(false);
		escolaridadesPane.setVisible(false);
		operacionesPane.setVisible(false);
	}

	private void inicializarOperacion(String nombreOperacion) {
		lblTituloOperacion.setText(nombreOperacion);
		listarCarrerasPaneToFront();
		listarAsignaturasPaneToFront();
		listarCursosPaneToFront();
		if (nombreOperacion.equalsIgnoreCase("INGRESAR CURSO")) {
			ingresarCursoPaneToFront();
		} else {
			ingresarExamenPaneToFront();
		}
	}

	private void inhabilitarSubPanelesRutinas() {
		listarCarrerasPane.setVisible(false);
		listarAsignaturasPane.setVisible(false);
		listarCursosPane.setVisible(false);
		ingresarCursoPane.setVisible(false);
		ingresarExamenPane.setVisible(false);
	}

	private void listarCarrerasPaneToFront() {
		inhabilitarSubPanelesRutinas();
		listarCarrerasPane.toFront();
		listarCarrerasPane.setVisible(true);
	}

	private void listarAsignaturasPaneToFront() {
		inhabilitarSubPanelesRutinas();
		listarAsignaturasPane.toFront();
		listarAsignaturasPane.setVisible(true);
	}

	private void listarCursosPaneToFront() {
		inhabilitarSubPanelesRutinas();
		listarCursosPane.toFront();
		listarCursosPane.setVisible(true);
	}

	private void ingresarCursoPaneToFront() {
		inhabilitarSubPanelesRutinas();
		ingresarCursoPane.toFront();
		ingresarCursoPane.setVisible(true);
	}

	private void ingresarExamenPaneToFront() {
		inhabilitarSubPanelesRutinas();
		ingresarExamenPane.toFront();
		ingresarExamenPane.setVisible(true);
	}

	public static List<Examen> getLstExamen() {
		return lstExamen;
	}

	public static List<Curso> getLstCurso() {
		return lstCurso;
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

}
