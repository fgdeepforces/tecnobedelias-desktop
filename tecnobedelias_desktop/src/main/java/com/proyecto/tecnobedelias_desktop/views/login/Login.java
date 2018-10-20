package com.proyecto.tecnobedelias_desktop.views.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.service.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;

public class Login implements Initializable {

	@FXML JFXTextField txtUser;
	@FXML JFXPasswordField txtPass;
	@FXML JFXButton btnLogin;

	public void loginButtonPushed(ActionEvent event) throws IOException{
		if(!txtUser.getText().isEmpty() && !txtPass.getText().isEmpty()) {
//			AnchorPane mainFormParent = (AnchorPane)FXMLLoader.load(getClass().getResource("../views/MasterForm.fxml"));
//			Scene mainFormScene = new Scene(mainFormParent);
//			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//			window.setScene(mainFormScene);
//			window.setMaximized(true);
//			window.show();
			Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
			dialogo.setTitle("ACCESO PERMITIDO");
			LoginService service = new LoginService();
			String mensaje = service.loginResponse(txtUser.getText(), txtPass.getText());
			if(mensaje.equalsIgnoreCase("BIENVENIDO")) {
				mensaje += "\nSu token es: " + Token.getInstance().getToken();
			}
			dialogo.setContentText(mensaje);
			dialogo.showAndWait();
			txtUser.setText("");
			txtPass.setText("");
			txtUser.requestFocus();
		}else {
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
