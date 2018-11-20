package com.proyecto.tecnobedelias_desktop.global;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.tecnobedelias_desktop.model.Curso_Estudiante;
import com.proyecto.tecnobedelias_desktop.model.Estudiante_Examen;
import com.proyecto.tecnobedelias_desktop.model.Horario;

public class Variables {

	private static List<Horario> lstHorarios = new ArrayList<>();
	private static List<Curso_Estudiante> lstEstudiantes = new ArrayList<>();
	private static List<Estudiante_Examen> lstEstudiantesExamen = new ArrayList<>();
	private static Variables INSTANCE = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private Variables() {

	}

	public static Variables getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Variables();
		}
		return INSTANCE;
	}

	public static List<Horario> getLstHorarios() {
		return lstHorarios;
	}

	public static void setLstHorarios(List<Horario> lstHorario) {
		Variables.lstHorarios = lstHorario;
	}

	public static SimpleDateFormat getSdf() {
		return sdf;
	}

	public static void setSdf(SimpleDateFormat sdf) {
		Variables.sdf = sdf;
	}

	public static List<Curso_Estudiante> getLstEstudiantes() {
		return lstEstudiantes;
	}

	public static void setLstEstudiantes(List<Curso_Estudiante> lstEstudiantes) {
		Variables.lstEstudiantes = lstEstudiantes;
	}

	public static List<Estudiante_Examen> getLstEstudiantesExamen() {
		return lstEstudiantesExamen;
	}

	public static void setLstEstudiantesExamen(List<Estudiante_Examen> lstEstudiantesExamen) {
		Variables.lstEstudiantesExamen = lstEstudiantesExamen;
	}

}
