//package com.proyecto.tecnobedelias_desktop.business;
//
//import java.util.List;
//
//public class ExamenesController {
//
//	private static ExamenesController INSTANCE = null;
//	private ExamenesController() {
//
//	}
//
//	public static ExamenesController getInstancia() {
//		if(INSTANCE == null) {
//			INSTANCE = new ExamenesController();
//		}
//		return INSTANCE;
//	}
//
//	public void ingresarExamen() {
//		//TODO: Not implemented yet
//		verExamenes();
//		List<Carrera> carreras = mostrarCarreras();
//		Carrera carrera = carreras.find(carreraSeleccionada.id);
//		List<Asignatura> asignaturas = mostrarAsignaturas(carrera);
//		Asignatura asignatura = asignaturas.find(asignaturaSeleccionada.id);
//		verAgregarExamen(asignatura,carrera);
//		ingresarExamen(fecha,asignatura,carrera);
//	}
//
//	public void modificarExamen() {
//		//TODO: Not implemented yet
//		verExamenes();
//		List<Examen> examenes = mostrarExamenes();
//		Examen examen = examenes.find(examenSeleccionado.id);
//		verModificarExamen(examen);
//		modificarExamen(examen,datos);
//		confirmar();
//	}
//
//	public void eliminarExamen() {
//		//TODO: Not implemented yet
//		verExamen();
//		List<Carrera> carreras = mostrarCarreras();
//		Carrera carrera = carreras.find(carreraSeleccionada.id);
//		List<Asignatura> asignaturas = mostrarAsignaturas(carrera);
//		Asignatura asignatura = asignaturas.find(asignaturaSeleccionada.id);
//		List<Examen> examenes = mostrarExamen(asignatura,carrera);
//		Examen examen = examenes.find(examenSeleccionado.id);
//		eliminarExamen(examen);
//		confirmar();
//	}
//
//	public void cargarCalificacionesDeExamen() {
//		//TODO: Not implemented yet
//		verExamenes();
//		List<Carrera> carreras = mostrarCarreras();
//		Carrera carrera = carreras.find(carreraSeleccionada.id);
//		List<Asignatura> asignaturas = mostrarAsignaturas(carrera);
//		Asignatura asignatura = asignaturas.find(asignaturaSeleccionada.id);
//		verExamenes(asignatura,carrera);
//		List<Examen> examenes = mostrarExamenes(asignatura,carrera);
//		Examen examen = examenes.find(examenSeleccionado.id);
//		verCargarNotas(examen);
//		ingresarNotas(notas);
//	}
//
//	public void imprimirActaDeExamen() {
//		//TODO: Not implemented yet
//		verExamenes();
//		List<Carrera> carreras = mostrarCarreras();
//		Carrera carrera = carreras.find(carreraSeleccionada.id);
//		List<Asignatura> asignaturas = mostrarAsignaturas(carrera);
//		Asignatura asignatura = asignaturas.find(asignaturaSeleccionada.id);
//		verExamenes(asignatura,carrera);
//		List<Examen> examenes = mostrarExamenes(asignatura,carrera);
//		Examen curso = examenes.find(examenSeleccionado.id);
//		Acta acta = mostrarActa(examen,asignatura,carrera);
//		if(seleccion == imprimir) {
//			imprimirActa();
//		}else {
//			imprimirPDF();
//		}
//	}
//}