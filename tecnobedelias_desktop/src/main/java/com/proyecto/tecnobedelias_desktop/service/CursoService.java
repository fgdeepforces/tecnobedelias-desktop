package com.proyecto.tecnobedelias_desktop.service;

import java.io.IOException;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.global.Variables;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import com.proyecto.tecnobedelias_desktop.model.Curso_Estudiante;
import com.proyecto.tecnobedelias_desktop.model.ServerResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CursoService {

	private static final String SERVER = "SERVER: ";

	List<Curso> data = null;
	ServerResponse responseServer = null;

	public List<Curso> listarCursosResponse() {
		data = null;
		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<List<Curso>> respuesta = interfaz.obtenerListaCursos("Bearer " + Token.getInstance().getToken());
		try {
			Response<List<Curso>> response = respuesta.execute();
			data = response.body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public ServerResponse borrarCursoResponse(String idCurso) {
		responseServer = null;
		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<ServerResponse> respuesta = interfaz.borrarCurso("Bearer " + Token.getInstance().getToken(), idCurso);
		try {
			Response<ServerResponse> response = respuesta.execute();
			responseServer = response.body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseServer;
	}

	public ServerResponse cargarCalificacionesCursoResponse(String idCurso, List<Curso_Estudiante> lstNotasCurso) {
		responseServer = null;
		JsonArray ja = new JsonArray();

		lstNotasCurso.forEach(estudiante -> {
			JsonObject jo = new JsonObject();
			jo.addProperty("id", estudiante.getId());
			jo.addProperty("nota", estudiante.getNota());
			ja.add(jo);
		});

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);

		Call<ServerResponse> respuesta = interfaz.cargarCalificacionesCurso("Bearer " + Token.getInstance().getToken(), ja ,idCurso);
		try {
			Response<ServerResponse> response = respuesta.execute();
			System.out.println(SERVER + response);
			while(!response.isSuccessful()) {
				Thread.sleep(1000);
			}
			System.out.println(SERVER + response);
			responseServer = response.body();	
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return responseServer;
	}

	public ServerResponse modificarCursoResponse(Curso curso) {
		responseServer = null;
		JsonObject jo = new JsonObject();
		JsonArray ja_horarios = new JsonArray();
		jo.addProperty("anio", curso.getAnio());
		jo.addProperty("fechaFin", Variables.getSdf().format(curso.getFechaFin()));
		jo.addProperty("fechaInicio", Variables.getSdf().format(curso.getFechaInicio()));
		curso.getHorarios().forEach(horario -> {
			JsonObject jo_horario = new JsonObject();
			jo_horario.addProperty("dia", horario.getDia());
			jo_horario.addProperty("horaFin", horario.getHoraFin());
			jo_horario.addProperty("horaInicio", horario.getHoraInicio());
			jo_horario.addProperty("id", horario.getId());
			ja_horarios.add(jo_horario);
		});
		jo.add("horarios", ja_horarios);
		jo.addProperty("semestre", curso.getSemestre());

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);

		Call<ServerResponse> respuesta = interfaz.modificarCurso("Bearer " + Token.getInstance().getToken(), jo ,curso.getId().toString());
		try {
			Response<ServerResponse> response = respuesta.execute();
			System.out.println(SERVER + response);
			while(!response.isSuccessful()) {
				Thread.sleep(1000);
			}
			System.out.println(SERVER + response);
			responseServer = response.body();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(SERVER + responseServer);
		return responseServer;
	}

	public ServerResponse ingresarCursoResponse(Curso curso) {
		responseServer = null;
		JsonObject jo = new JsonObject();
		JsonArray ja_horarios = new JsonArray();

		jo.addProperty("anio", curso.getAnio());
		jo.addProperty("fechaFin", Variables.getSdf().format(curso.getFechaFin()));
		jo.addProperty("fechaInicio", Variables.getSdf().format(curso.getFechaInicio()));
		curso.getHorarios().forEach(horario -> {
			JsonObject jo_horario = new JsonObject();
			jo_horario.addProperty("dia", horario.getDia());
			jo_horario.addProperty("horaFin", horario.getHoraFin());
			jo_horario.addProperty("horaInicio", horario.getHoraInicio());
			ja_horarios.add(jo_horario);
		});
		jo.add("horarios", ja_horarios);
		jo.addProperty("semestre", curso.getSemestre());

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);

		Call<ServerResponse> respuesta = interfaz.ingresarCurso("Bearer " + Token.getInstance().getToken(), jo ,curso.getNombreAsignatura());
		try {
			Response<ServerResponse> response = respuesta.execute();
			System.out.println(SERVER + response);
			while(!response.isSuccessful()) {
				Thread.sleep(1000);
			}
			System.out.println(SERVER + response);
			responseServer = response.body();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		return responseServer;
	}

}
