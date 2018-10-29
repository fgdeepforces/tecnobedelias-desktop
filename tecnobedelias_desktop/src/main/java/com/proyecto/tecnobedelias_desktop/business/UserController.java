//package com.proyecto.tecnobedelias_desktop.business;
//
//public class UserController {
//
//	private static UserController INSTANCE = null;
//	private UserController() {
//
//	}
//
//	public static UserController getInstancia() {
//		if(INSTANCE == null) {
//			INSTANCE = new UserController();
//		}
//		return INSTANCE;
//	}
//
//	public void iniciarSesion() {
//		String usuario = getUsuario();
//		String clave = getClave();
//		iniciarSesion(usuario,clave);
//	}
//
//	public void cerrarSesion() {
//		salir();
//		login();
//	}
//
//	public void cambiarClave() {
//		//TODO: Not implemented yet
//		verPerfil();
//		verCambiarClave();
//		String claveNueva1 = getClaveNueva();
//		String claveNueva2 = getConfirmacionClaveNueva();
//		String claveActual = getClaveActual();
//		cambiarClave(claveNueva1,claveNueva2,claveActual);
//	}
//
//	public void imprimirEscolaridad() {
//		//TODO: Not implemented yet
//		verEscolaridades();
//		List<Estudiante> estudiantes = mostrarEstudiantes();
//		Estudiante estudiante = estudiantes.find(estudianteSeleccionado.id);
//		verEstudiante(estudiante);
//		mostrarPerfil(estudiante);
//		verCarreras(estudiante);
//		List<Carrera> carrerasMatriculadas = mostrarMatriculadas(estudiante);
//		Carrera carrera = carrerasMatriculadas.find(carreraSeleccionada.id);
//		verEscolaridad(estudiante,carrera);
//		mostrarEscolaridad(estudiante,carrera);
//		if(seleccion == imprimir) {
//			imprimirEscolaridad();
//		}else {
//			imprimirPDF();
//		}
//	}
//}
