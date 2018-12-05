package com.proyecto.tecnobedelias_desktop.global;

public class Constants {

	private static final String LOGIN_PACKAGE = "src/main/java/com/proyecto/tecnobedelias_desktop/views/login/";
	private static final String MAIN_PACKAGE = "src/main/java/com/proyecto/tecnobedelias_desktop/views/main/";
	private static final String PRUEBA_PACKAGE = "src/main/java/com/proyecto/tecnobedelias_desktop/views/prueba/";
	private static final String MAIN_FORM_PACKAGE = "src/main/java/com/proyecto/tecnobedelias_desktop/views/main_form/";
	private static final String LOGIN_SERVICE_URL = "login";
	private static final String LISTAR_CURSOS_SERVICE_URL = "curso/listar";
	//private static final String URL_BASE = "http://localhost:8080/";
	private static final String URL_BASE = "http://ec2-3-16-143-114.us-east-2.compute.amazonaws.com:8080/tecnobedelias/";	

	public static String getLoginPackage() {
		return LOGIN_PACKAGE;
	}

	public static String getLoginServiceUrl() {
		return URL_BASE + LOGIN_SERVICE_URL;
	}

	public static String getMainPackage() {
		return MAIN_PACKAGE;
	}

	public static String getListarCursosServiceUrl() {
		return URL_BASE + LISTAR_CURSOS_SERVICE_URL;
	}

	public static String getUrlBase() {
		return URL_BASE;
	}

	public static String getMainFormPackage() {
		return MAIN_FORM_PACKAGE;
	}

	public static String getPruebaPackage() {
		return PRUEBA_PACKAGE;
	}

}
