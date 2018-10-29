package com.proyecto.tecnobedelias_desktop.views.prueba;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.proyecto.tecnobedelias_desktop.global.Constants;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import com.proyecto.tecnobedelias_desktop.model.Examen;
import com.proyecto.tecnobedelias_desktop.model.TablaCurso;
import com.proyecto.tecnobedelias_desktop.model.TablaExamen;
import com.proyecto.tecnobedelias_desktop.service.CursoService;
import com.proyecto.tecnobedelias_desktop.service.ExamenService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Prueba implements Initializable{

	@FXML Label lblOperacion;
	@FXML Label lblTituloOperacion;
	@FXML JFXButton btnVolver;
	@FXML JFXButton btnCursos;
	@FXML JFXButton btnExamenes;
	@FXML JFXButton btnLogout;
	@FXML JFXButton btnAgregarCurso;
	@FXML JFXButton btnAgregarExamen;
	@FXML BorderPane cursosPane;
	@FXML BorderPane examenesPane;
	@FXML BorderPane operacionesPane;
	@FXML AnchorPane listarCarrerasPane;
	@FXML AnchorPane listarAsignaturasPane;
	@FXML AnchorPane listarCursosPane;
	@FXML AnchorPane ingresarCursoPane;
	@FXML AnchorPane ingresarExamenPane;
	@FXML StackPane stackPane;
	@FXML StackPane stackOperationPane;

	@FXML TableView<TablaCurso> tablaCursos;
	@FXML TableColumn<TablaCurso, String> colIdCursos;
	@FXML TableColumn<TablaCurso, String> colAsignaturaCursos;
	@FXML TableColumn<TablaCurso, String> colFechaInicioCursos;
	@FXML TableColumn<TablaCurso, String> colFechaFinCursos;
	@FXML TableColumn<TablaCurso, String> colSemestreCursos;
	@FXML TableColumn<TablaCurso, String> colEditarCursos;
	@FXML TableColumn<TablaCurso, String> colEliminarCursos;
	@FXML TableColumn<TablaCurso, String> colActaCursos;
	@FXML TableColumn<TablaCurso, String> colCargarCalificacionesCursos;

	@FXML TableView<TablaExamen> tablaExamenes;
	@FXML TableColumn<TablaExamen, String> colIdExamenes;
	@FXML TableColumn<TablaExamen, String> colAsignaturaExamenes;
	@FXML TableColumn<TablaExamen, String> colFechaExamenes;
	@FXML TableColumn<TablaExamen, String> colHoraExamenes;
	@FXML TableColumn<TablaExamen, String> colEditarExamenes;
	@FXML TableColumn<TablaExamen, String> colEliminarExamenes;
	@FXML TableColumn<TablaExamen, String> colActaExamenes;
	@FXML TableColumn<TablaExamen, String> colCargarCalificacionesExamenes;

	final ObservableList<TablaCurso> dataCurso = FXCollections.observableArrayList();
	final ObservableList<TablaExamen> dataExamen = FXCollections.observableArrayList();


	@SuppressWarnings("deprecation")
	public void logoutButtonPushed(ActionEvent event) throws IOException {
		try {
			Token.getInstance().setToken(null);
			Token.getInstance().setMensaje(null);
			URL formUrl = new File(Constants.getLoginPackage() +"login.fxml").toURL();
			URL cssUrl = new File(Constants.getLoginPackage() + "login.css").toURL();
			AnchorPane root = FXMLLoader.load(formUrl);
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(cssUrl.toExternalForm());
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.centerOnScreen();
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void btnAgregarCursoActionListener(ActionEvent event) {
		operacionesPaneToFront();
		inicializarOperacion("INGRESAR CURSO");
		TablaCurso entry = new TablaCurso("curso", "curso", "curso", "curso", "curso");
		dataCurso.add(entry);
	}

	public void btnAgregarExamenActionListener(ActionEvent event) {
		operacionesPaneToFront();
		inicializarOperacion("INGRESAR EXAMEN");
		TablaExamen entry = new TablaExamen("examen", "examen", "examen", "examen");
		dataExamen.add(entry);
	}

	public void cursosButtonPushed(ActionEvent event) throws IOException {
		//TODO: Cambiar Scene del BorderPane.CENTER para carga de CursosScene
		cursosPaneToFront();
	}

	public void examenesButtonPushed(ActionEvent event) throws IOException {
		//TODO: Cambiar Scene del BorderPane.CENTER para carga de ExamenScene
		examenesPaneToFront();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		inicializarTablaCursos();
		inicializarTablaExamenes();
		cursosPaneToFront();
	}

	private void inicializarTablaCursos() {
		colIdCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colId"));
		colAsignaturaCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colAsignatura"));
		colFechaInicioCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colFechaInicio"));
		colFechaFinCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colFechaFin"));
		colSemestreCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colSemestre"));
		try {
			colEditarCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colEditar"));
			colEliminarCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colEliminar"));
			colActaCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colActa"));
			colCargarCalificacionesCursos.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colCargarCalificaciones"));
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		tablaCursos.setItems(dataCurso);

		CursoService service = new CursoService();
		List<Curso> lstCurso = service.listarCursosResponse();
		if(lstCurso != null) {
			lstCurso.forEach(curso->{
				String id = "" + curso.getId();
				String semestre = "" + curso.getSemestre();
				TablaCurso entry = new TablaCurso(id, curso.getNombreAsignatura(), curso.getFechaInicio(), curso.getFechaFin(), semestre);
				dataCurso.add(entry);
	        });
		}
		colIdCursos.setVisible(false);
	}

	private void inicializarTablaExamenes() {
		colIdExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen,String>("colIdExamen"));
		colAsignaturaExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen,String>("colAsignaturaExamen"));
		try {
			colFechaExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen,String>("colFechaExamen"));
			colHoraExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen,String>("colHoraExamen"));
			colEditarExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen,String>("colEditarExamen"));
			colEliminarExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen,String>("colEliminarExamen"));
			colActaExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen,String>("colActaExamen"));
			colCargarCalificacionesExamenes.setCellValueFactory(new PropertyValueFactory<TablaExamen,String>("colCargarCalificacionesExamen"));
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		tablaExamenes.setItems(dataExamen);

		ExamenService service = new ExamenService();
		List<Examen> lstExamen = service.listarExamenesResponse();
		if(lstExamen != null) {
			lstExamen.forEach(examen->{
				String id = "" + examen.getId();
				TablaExamen entry = new TablaExamen(id, examen.getNombreAsignatura(), examen.getFecha(), examen.getHora());
				dataExamen.add(entry);
	        });
		}
		colIdExamenes.setVisible(false);
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

	private void operacionesPaneToFront() {
		inhabilitarPanelesCentrales();
		operacionesPane.toFront();
		operacionesPane.setVisible(true);
	}

	private void inhabilitarPanelesCentrales() {
		cursosPane.setVisible(false);
		examenesPane.setVisible(false);
		operacionesPane.setVisible(false);
	}

	private void inicializarOperacion(String nombreOperacion) {
		lblTituloOperacion.setText(nombreOperacion);
		listarCarrerasPaneToFront();
		listarAsignaturasPaneToFront();
		listarCursosPaneToFront();
		if(nombreOperacion.equalsIgnoreCase("INGRESAR CURSO")) {
			ingresarCursoPaneToFront();
		}else {
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


}