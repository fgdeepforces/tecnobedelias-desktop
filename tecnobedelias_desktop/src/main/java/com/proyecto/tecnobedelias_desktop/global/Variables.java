package com.proyecto.tecnobedelias_desktop.global;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.proyecto.tecnobedelias_desktop.model.Horario;

public class Variables {

	private static List<Horario> lstHorarios = new ArrayList<>();
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
}
