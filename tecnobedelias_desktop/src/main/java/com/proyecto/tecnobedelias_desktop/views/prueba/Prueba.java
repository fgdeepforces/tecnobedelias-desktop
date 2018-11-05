package com.proyecto.tecnobedelias_desktop.views.prueba;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.jfoenix.controls.JFXButton;
import com.proyecto.tecnobedelias_desktop.global.Constants;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.global.Variables;
import com.proyecto.tecnobedelias_desktop.model.AsignaturaCarrera;
import com.proyecto.tecnobedelias_desktop.model.Carrera;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import com.proyecto.tecnobedelias_desktop.model.Examen;
import com.proyecto.tecnobedelias_desktop.model.Horario;
import com.proyecto.tecnobedelias_desktop.model.TablaCurso;
import com.proyecto.tecnobedelias_desktop.model.TablaExamen;
import com.proyecto.tecnobedelias_desktop.model.TablaHorario;
import com.proyecto.tecnobedelias_desktop.service.CarreraService;
import com.proyecto.tecnobedelias_desktop.service.CursoService;
import com.proyecto.tecnobedelias_desktop.service.ExamenService;
import com.proyecto.tecnobedelias_desktop.utils.GeneratePDFFileIText;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(cssUrl.toExternalForm());
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
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
		JFXButton btnIngresarHorario = new JFXButton("Ingresar Horario");
		inicializarOperacion("INGRESAR CURSO");
		CarreraService cs = new CarreraService();
		ObservableList<Carrera> dataCarreras = FXCollections.observableArrayList();
		ObservableList<AsignaturaCarrera> dataAsignaturas = FXCollections.observableArrayList();
		List<Carrera> carreras = cs.listarCarrerasResponse();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		TextField txtFechaInicio = new TextField();
		TextField txtFechaFin = new TextField();
		TextField txtSemestre = new TextField();

		txtNombre.setVisible(false);
		txtAnio.setVisible(false);
		txtFechaInicio.setVisible(false);
		txtFechaFin.setVisible(false);
		txtSemestre.setVisible(false);

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
					// TODO Auto-generated method stub
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
							dataAsignaturas.addAll(asignaturasDeCarrera);
							cboAsignaturas.getItems().addAll(dataAsignaturas);
							cboAsignaturas.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									nombreAsignatura = cboAsignaturas.getSelectionModel().getSelectedItem().getAsignatura().getNombre();
									lblAnio.setVisible(true);
									lblFechaFin.setVisible(true);
									lblFechaInicio.setVisible(true);
									lblSemestre.setVisible(true);
									txtAnio.setVisible(true);
									txtFechaInicio.setVisible(true);
									txtFechaFin.setVisible(true);
									txtSemestre.setVisible(true);
								}
							});
						}
					}else {
						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
						alerta.setTitle("Informacion");
						alerta.setContentText(
								"no hay carrera");
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
		grid.add(txtFechaInicio, 2, 5);
		grid.add(lblFechaFin, 1, 6);
		grid.add(txtFechaFin, 2, 6);
		grid.add(lblSemestre, 1, 7);
		grid.add(txtSemestre, 2, 7);

//		TablaCurso entry = new TablaCurso("curso", "curso", "curso", "curso", "curso", "curso");
//		dataCurso.add(entry);

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
					curso.setFechaFin(txtFechaFin.getText());
					curso.setFechaInicio(txtFechaInicio.getText());
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
		Dialog<Examen> dialog = new Dialog<>();
		inicializarOperacion("INGRESAR EXAMEN");
		CarreraService cs = new CarreraService();
		ObservableList<Carrera> dataCarreras = FXCollections.observableArrayList();
		ObservableList<AsignaturaCarrera> dataAsignaturas = FXCollections.observableArrayList();
		List<Carrera> carreras = cs.listarCarrerasResponse();
		Label lblCarreras = new Label("Carrera");
		Label lblAsignatura = new Label("Asignatura");
		ComboBox<Carrera> cboCarreras = new ComboBox<>();
		ComboBox<AsignaturaCarrera> cboAsignaturas = new ComboBox<>();
		Examen examen = new Examen();

		Label lblFecha = new Label("Fecha");
		Label lblHora = new Label("Hora");

		lblAsignatura.setVisible(false);
		lblFecha.setVisible(false);
		lblHora.setVisible(false);

		TextField txtFecha = new TextField();
		TextField txtHora = new TextField();

		txtFecha.setVisible(false);
		txtHora.setVisible(false);

		cboAsignaturas.setVisible(false);

		GridPane grid = new GridPane();

		dialog.setTitle("Ingresar Examen");
		dialog.setHeaderText("Ingresar Examen");
		dialog.setResizable(true);

		if (carreras == null) {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Informacion");
			alerta.setContentText(
					"No existen carreras en el sistema por favor contactese con el director para que cree alguna");
			alerta.showAndWait();
		}/* else {
			dataCarreras.addAll(carreras);
			cboCarreras.getItems().addAll(dataCarreras);
			carrera = null;
			Carrera carrera = cboCarreras.getSelectionModel().getSelectedItem();
			List<AsignaturaCarrera> asignaturasDeCarrera = carrera.getAsignaturaCarrera();
			if (asignaturasDeCarrera == null) {
				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("Informacion");
				alerta.setContentText(
						"No existen asignaturas para la carrera seleccionada en el sistema por favor contactese con el director para que cree alguna");
				alerta.showAndWait();
			} else {
				lblAsignatura.setVisible(true);
				cboAsignaturas.setVisible(true);
				dataAsignaturas.addAll(asignaturasDeCarrera);
				cboAsignaturas.getItems().addAll(dataAsignaturas);
				nombreAsignatura = cboAsignaturas.getSelectionModel().getSelectedItem().getAsignatura().getNombre();
				lblFecha.setVisible(true);
				lblHora.setVisible(true);
				txtFecha.setVisible(true);
				txtHora.setVisible(true);
			}
		}
		
		*//*************************************************************************************//*
		*/
		 else {
			dataCarreras.addAll(carreras);
			cboCarreras.getItems().addAll(dataCarreras);
			carrera = null;
			cboCarreras.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
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
							dataAsignaturas.addAll(asignaturasDeCarrera);
							cboAsignaturas.getItems().addAll(dataAsignaturas);
							cboAsignaturas.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									// TODO Auto-generated method stub
									nombreAsignatura = cboAsignaturas.getSelectionModel().getSelectedItem().getAsignatura().getNombre();
									lblFecha.setVisible(true);
									lblHora.setVisible(true);
									txtFecha.setVisible(true);
									txtHora.setVisible(true);
								}
							});
						}
					}else {
						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
						alerta.setTitle("Informacion");
						alerta.setContentText(
								"no hay carrera");
						alerta.showAndWait();
					}
				}
			});
		}
		
		/*************************************************************************************/

		grid.add(lblCarreras, 1, 1);
		grid.add(cboCarreras, 2, 1);
		grid.add(lblAsignatura, 1, 2);
		grid.add(cboAsignaturas, 2, 2);
		grid.add(lblFecha, 1, 4);
		grid.add(txtFecha, 2, 4);
		grid.add(lblHora, 1, 5);
		grid.add(txtHora, 2, 5);

		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, Examen>() {
			@Override
			public Examen call(ButtonType b) {
				if (b == buttonTypeOk) {
					examen.setFecha(txtFecha.getText());
					examen.setHora(txtHora.getText());
					examen.setNombreAsignatura(nombreAsignatura);
					ExamenService cs = new ExamenService();
					alertConfirmacionIngresarExamen(cs.ingresarExamenResponse(examen));
				}
				return null;
			}
		});
		dialog.showAndWait();
	}

	private void alertConfirmacionIngresarExamen(boolean respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Ingresar Examen");

		if (respuesta) {
			dialogo.setContentText("Se ha ingresado el examen satisfactoriamente.");
		} else {
			dialogo.setContentText("No se pudo ingresar el examen.");
		}
		actualizarDatosTablaExamen();
		dialogo.showAndWait();
	}

	private void alertConfirmacionIngresarCurso(boolean respuesta) {
		Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
		dialogo.setTitle("Ingresar Curso");

		if (respuesta) {
			dialogo.setContentText("Se ha ingresado el curso satisfactoriamente.");
		} else {
			dialogo.setContentText("No se pudo ingresar el curso.");
		}
		actualizarDatosTablaCurso();
		dialogo.showAndWait();
	}

	private void dialogIngresarHorario(Curso curso) {
		Dialog<Horario> dialog = new Dialog<>();

		GridPane grid = new GridPane();

		Label lblDia = new Label("Dia");
		Label lblHoraInicio = new Label("Hora inicio");
		Label lblHoraFin = new Label("Hora fin");

		TextField txtDia = new TextField();
		TextField txtHoraInicio = new TextField();
		TextField txtHoraFin = new TextField();

		dialog.setTitle("Horarios");
		dialog.setHeaderText("Ingreso de nuevo horario");
		dialog.setResizable(true);

		grid.add(lblDia, 1, 1);
		grid.add(txtDia, 2, 1);
		grid.add(lblHoraInicio, 1, 2);
		grid.add(txtHoraInicio, 2, 2);
		grid.add(lblHoraFin, 1, 3);
		grid.add(txtHoraFin, 2, 3);

		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, Horario>() {
			@Override
			public Horario call(ButtonType b) {
				if (b == buttonTypeOk) {
					Variables.getLstHorarios()
							.add(new Horario(txtDia.getText(), txtHoraFin.getText(), txtHoraInicio.getText()));
					alertConfirmacionIngresarHorario(true, curso);
				}
				return null;
			}
		});
		dialog.showAndWait();
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
					colEditarHorario = new JFXButton("Ediar");
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

	private void dialogModificarHorario(Curso curso) {
		Dialog<Horario> dialog = new Dialog<>();
		Label lblDia = new Label("Dia");
		Label lblHoraInicio = new Label("Hora de Inicio");
		Label lblHoraFin = new Label("Hora de Finalizacion");
		GridPane grid = new GridPane();

		if (getTablaHorarios().getSelectionModel().getSelectedItem() == null) {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Informacion");
			alerta.setContentText("Debes de seleccionar una fila antes de proceder a editar el horario");
			alerta.showAndWait();
		} else {
			int index = getTablaHorarios().getSelectionModel().getSelectedIndex();
			TextField txtDia = new TextField(
					getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario());
			TextField txtHoraInicio = new TextField(
					getTablaHorarios().getSelectionModel().getSelectedItem().getColHoraInicioHorario());
			TextField txtHoraFin = new TextField(
					getTablaHorarios().getSelectionModel().getSelectedItem().getColHoraFinHorario());

			dialog.setTitle("Modificacion del horario del dia "
					+ getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario());
			dialog.setHeaderText("Modificacion del horario del dia "
					+ getTablaHorarios().getSelectionModel().getSelectedItem().getColDiaHorario());
			dialog.setResizable(true);

			grid.add(lblDia, 1, 1);
			grid.add(txtDia, 2, 1);
			grid.add(lblHoraInicio, 1, 2);
			grid.add(txtHoraInicio, 2, 2);
			grid.add(lblHoraFin, 1, 3);
			grid.add(txtHoraFin, 2, 3);

			dialog.getDialogPane().setContent(grid);
			ButtonType buttonTypeOk = new ButtonType("Confirmar", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
			dialog.setResultConverter(new Callback<ButtonType, Horario>() {
				@Override
				public Horario call(ButtonType b) {
					if (b == buttonTypeOk) {
						Variables.getLstHorarios().get(index).setDia(txtDia.getText());
						Variables.getLstHorarios().get(index).setHoraInicio(txtHoraInicio.getText());
						Variables.getLstHorarios().get(index).setHoraFin(txtHoraFin.getText());
						alertConfirmacionModificarHorario(true, curso);
					}
					return null;
				}
			});
			dialog.showAndWait();
		}
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

	// TODO
	/**
	 * @throws IOException **************************************************************************************************/
	public void btnPdfActionListener(ActionEvent event) throws DocumentException, IOException {
//		System.out.println("entre al pdf events");
//		GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
//        generatePDFFileIText.crearActaFinalDeCurso(curso);
//        Desktop.getDesktop().open(new File("src/resources/pdf/Escolaridad.pdf"));
	}

//	public void btnAgregarExamenActionListener(ActionEvent event) {
//		operacionesPaneToFront();
//		inicializarOperacion("INGRESAR EXAMEN");
//		TablaExamen entry = new TablaExamen("examen", "examen", "examen", "examen");
//		dataExamen.add(entry);
//	}

	public void cursosButtonPushed(ActionEvent event) throws IOException {
		cursosPaneToFront();
	}

	public void examenesButtonPushed(ActionEvent event) throws IOException {
		examenesPaneToFront();
	}

	public void escolaridadesButtonPushed(ActionEvent event) throws IOException {
		escolaridadesPaneToFront();
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
		cursosPane.toFront();
		cursosPane.setVisible(true);
	}

	private void examenesPaneToFront() {
		inhabilitarPanelesCentrales();
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
