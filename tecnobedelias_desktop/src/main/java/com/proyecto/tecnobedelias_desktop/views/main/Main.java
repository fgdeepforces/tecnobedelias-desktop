package com.proyecto.tecnobedelias_desktop.views.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.proyecto.tecnobedelias_desktop.global.Constants;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import com.proyecto.tecnobedelias_desktop.model.TablaCurso;
import com.proyecto.tecnobedelias_desktop.service.CursoService;

public class Main implements Initializable{

	@FXML TableView<TablaCurso> dataTable;
	@FXML TableColumn<TablaCurso, String> colId;
	@FXML TableColumn<TablaCurso, String> colAsignatura;
	@FXML TableColumn<TablaCurso, String> colFechaInicio;
	@FXML TableColumn<TablaCurso, String> colFechaFin;
	@FXML TableColumn<TablaCurso, String> colSemestre;
	@FXML TableColumn<TablaCurso, String> colEditar;
	@FXML TableColumn<TablaCurso, String> colEliminar;
	@FXML TableColumn<TablaCurso, String> colActa;
	@FXML JFXButton btnCursos;
	@FXML JFXButton btnExamenes;
	@FXML JFXButton btnLogout;
	@FXML JFXButton btnAgregar;
	@FXML Label lblTitulo;

	final ObservableList<TablaCurso> data = FXCollections.observableArrayList();

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

	public void cursosButtonPushed(ActionEvent event) throws IOException {
		lblTitulo.setText("Cursos");
		//TODO: Cambiar Scene del BorderPane.CENTER para carga de CursosScene
	}

	public void examenesButtonPushed(ActionEvent event) throws IOException {
		lblTitulo.setText("Examenes");
		//TODO: Cambiar Scene del BorderPane.CENTER para carga de ExamenScene
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colId.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colId"));
		colAsignatura.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colAsignatura"));
		colFechaInicio.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colFechaInicio"));
		colFechaFin.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colFechaFin"));
		colSemestre.setCellValueFactory(new PropertyValueFactory<TablaCurso,String>("colSemestre"));

		dataTable.setItems(data);

		CursoService service = new CursoService();
		List<Curso> lstCurso = service.listarCursosResponse();
		if(lstCurso != null) {
			lstCurso.forEach(curso->{
				String id = "" + curso.getId();
				String semestre = "" + curso.getSemestre();
				TablaCurso entry = new TablaCurso(id, curso.getNombreAsignatura(), curso.getFechaInicio(), curso.getFechaFin(), semestre);
				data.add(entry);
	        });
		}

	}

	public void onAddItem(ActionEvent event) {
		//TODO: Agregar modal frame para Alta de Cursos / Examenes segun corresponda, una vez definidos
		//los scenes ExamenScene y CursosScene esta funcion se borra (SOLO FINES DE TEST)
		TablaCurso entry = new TablaCurso("prueba", "prueba", "prueba", "prueba", "prueba");
		data.add(entry);
	}

}