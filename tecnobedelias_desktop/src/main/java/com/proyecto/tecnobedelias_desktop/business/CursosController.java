//package com.proyecto.tecnobedelias_desktop.business;
//
//import java.util.List;
//
//public class CursosController{
//
//	private static CursosController INSTANCE = null;
//	private CursosController() {
//
//	}
//
//	public static CursosController getInstancia() {
//		if(INSTANCE == null) {
//			INSTANCE = new CursosController();
//		}
//		return INSTANCE;
//	}
//
//	public void ingresarCurso() {
//		//TODO: Not implemented yet
//		verCursos();
//		List<Carrera> carreras = mostrarCarreras();
//		Carrera carrera = carreras.find(carreraSeleccionada.id);
//		List<Asignatura> asignaturas = mostrarAsignaturas(carrera);
//		Asignatura asignatura = asignaturas.find(asignaturaSeleccionada.id);
//		verAgregarCurso(asignatura,carrera);
//		ingresarCurso(anio,semestre,inicio,fin,asignatura,carrera);
//	}
//
//	public void modificarCurso() {
//		//TODO: Not implemented yet
//		verCursos();
//		List<Curso> cursos = mostrarCursos();
//		Curso curso = cursos.find(cursoSeleccionado.id);
//		verModificarCurso(curso);
//		modificarCurso(curso,datos);
//		confirmar();
//	}
//
//	public void eliminarCurso() {
//		//TODO: Not implemented yet
//		verCursos();
//		List<Carrera> carreras = mostrarCarreras();
//		Carrera carrera = carreras.find(carreraSeleccionada.id);
//		List<Asignatura> asignaturas = mostrarAsignaturas(carrera);
//		Asignatura asignatura = asignaturas.find(asignaturaSeleccionada.id);
//		List<Curso> cursos = mostrarCursos(asignatura,carrera);
//		Curso curso = cursos.find(cursoSeleccionado.id);
//		eliminarCurso(curso);
//		confirmar();
//
//	}
//
//	public void imprimirActaDeFinDeCurso() {
//		//TODO: Not implemented yet
//		verCursos();
//		List<Carrera> carreras = mostrarCarreras();
//		Carrera carrera = carreras.find(carreraSeleccionada.id);
//		List<Asignatura> asignaturas = mostrarAsignaturas(carrera);
//		Asignatura asignatura = asignaturas.find(asignaturaSeleccionada.id);
//		verCursos(asignatura,carrera);
//		List<Curso> cursos = mostrarCursos(asignatura,carrera);
//		Curso curso = cursos.find(cursoSeleccionado.id);
//		Acta acta = mostrarActa(curso,asignatura,carrera);
//		if(seleccion == imprimir) {
//			imprimirActa();
//		}else {
//			imprimirPDF();
//		}
//	}
//
//	public void cargarCalificacionesDeCurso() {
//		//TODO: Not implemented yet
//		verCursos();
//		List<Carrera> carreras = mostrarCarreras();
//		Carrera carrera = carreras.find(carreraSeleccionada.id);
//		List<Asignatura> asignaturas = mostrarAsignaturas(carrera);
//		Asignatura asignatura = asignaturas.find(asignaturaSeleccionada.id);
//		verCursos(asignatura,carrera);
//		List<Curso> cursos = mostrarCursos(asignatura,carrera);
//		Curso curso = cursos.find(cursoSeleccionado.id);
//		verCargarNotasCurso(curso);
//		ingresarNotas(notas);
//	}
//
//}

//TODO SECUENCIA

//verCursos();
//if(paso = modificarCurso) {
//	List<Curso> cursos = mostrarCursos();
//	Curso curso = cursos.find(cursoSeleccionado.id);
//	verModificarCurso(curso);
//	modificarCurso(curso,datos);
//	confirmar();
//}
//List<Carrera> carreras = mostrarCarreras();
//Carrera carrera = carreras.find(carreraSeleccionada.id);
//List<Asignatura> asignaturas = mostrarAsignaturas(carrera);
//Asignatura asignatura = asignaturas.find(asignaturaSeleccionada.id);
//if(paso = ingresarCurso) {
//	verAgregarCurso(asignatura,carrera);
//	ingresarCurso(anio,semestre,inicio,fin,asignatura,carrera);
//}
//if(paso = cargarCalificacionesDeCurso || paso = imprimirActaDeFinDeCurso) {
//	verCursos(asignatura,carrera);
//}
//List<Curso> cursos = mostrarCursos(asignatura,carrera);
//Curso curso = cursos.find(cursoSeleccionado.id);
//if(paso = cargarCalificacionesDeCurso) {
//	verCargarNotasCurso(curso);
//	ingresarNotas(notas);
//}
//if(paso = eliminarCurso) {
//	eliminarCurso(curso);
//	confirmar();
//}
//if(paso = imprimirActaDeFinDeCurso) {
//	Acta acta = mostrarActa(curso,asignatura,carrera);
//	if(seleccion == imprimir) {
//		imprimirActa();
//	}else {
//		imprimirPDF();
//	}
//}