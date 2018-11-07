package com.proyecto.tecnobedelias_desktop.views.login;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.proyecto.tecnobedelias_desktop.global.Constants;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.service.LoginService;
//import com.proyecto.tecnobedelias_desktop.views.main_form.MainFormView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login implements Initializable {

	@FXML
	JFXTextField txtUser;
	@FXML
	JFXPasswordField txtPass;
	@FXML
	JFXButton btnLogin;

	@SuppressWarnings("deprecation")
	public void loginButtonPushed(ActionEvent event) throws IOException {
		if (!txtUser.getText().isEmpty() && !txtPass.getText().isEmpty()) {
			LoginService service = new LoginService();
			String mensaje = service.loginResponse(txtUser.getText(), txtPass.getText());
			if (mensaje != null) {
				if (mensaje.equalsIgnoreCase("BIENVENIDO")) {
					//TODO: NO BORRAR INICIO
//					URL formUrl = new File(Constants.getMainPackage() + "Main.fxml").toURL();
//					URL cssUrl = new File(Constants.getMainPackage() + "Main.css").toURL();
//					AnchorPane root = FXMLLoader.load(formUrl);
//					Scene scene = new Scene(root);
//					scene.getStylesheets().add(cssUrl.toExternalForm());
//					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//					window.setScene(scene);
//					window.setMaximized(true);
//					window.show();
					//TODO: NO BORRAR FIN

					URL formUrl = new File(Constants.getPruebaPackage() + "Prueba.fxml").toURL();
					URL cssUrl = new File(Constants.getPruebaPackage() + "Prueba.css").toURL();
					AnchorPane root = FXMLLoader.load(formUrl);
					Scene scene = new Scene(root,400,400);
					scene.getStylesheets().add(cssUrl.toExternalForm());
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					window.setScene(scene);
					window.setMaximized(true);
					window.show();



					//TODO: Perdida de tiempo al pedo con el framework
//					URL cssUrl = new File(Constants.getMainFormPackage() + "MainForm.css").toURL();
//					MainFormView mainForm = new MainFormView();
//					Scene scene = new Scene(mainForm.getView());
//					scene.getStylesheets().add(cssUrl.toExternalForm());
//					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//					window.setScene(scene);
//					window.setMaximized(true);
//					window.show();
					//TODO: Perdida de tiempo al pedo con el framework
				} else {
					Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
					dialogo.setTitle("ACCESO");
					dialogo.setContentText(mensaje);
					dialogo.showAndWait();
				}
			} else {
				Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
				dialogo.setTitle("SERVIDOR");
				dialogo.setContentText("Error 500: Servidor temporalmente fuera de servicio, vuelva a intentarlo nuevamente");
				dialogo.showAndWait();
			}
			txtUser.setText("");
			txtPass.setText("");
			Token.getInstance().setMensaje(null);
			txtUser.requestFocus();
		} else {
			Alert dialogo = new Alert(Alert.AlertType.ERROR);
			dialogo.setTitle("ERROR");
			dialogo.setContentText("Usuario o clave incorrectas.");
			dialogo.showAndWait();
			txtUser.setText("");
			txtPass.setText("");
			txtUser.requestFocus();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
