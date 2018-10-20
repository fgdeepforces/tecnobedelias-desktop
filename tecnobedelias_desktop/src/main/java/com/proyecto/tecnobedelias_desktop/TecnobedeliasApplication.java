package com.proyecto.tecnobedelias_desktop;

import java.io.File;
import java.net.URL;
import com.proyecto.tecnobedelias_desktop.global.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TecnobedeliasApplication extends Application {

	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			URL formUrl = new File(Constants.getLoginPackage() +"login.fxml").toURL();
			URL cssUrl = new File(Constants.getLoginPackage() + "login.css").toURL();
			AnchorPane root = FXMLLoader.load(formUrl);
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(cssUrl.toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
