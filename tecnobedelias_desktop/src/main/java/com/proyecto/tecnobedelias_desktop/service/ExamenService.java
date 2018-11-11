package com.proyecto.tecnobedelias_desktop.service;

import java.io.IOException;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.global.Variables;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Estudiante_Examen;
import com.proyecto.tecnobedelias_desktop.model.Examen;
import com.proyecto.tecnobedelias_desktop.model.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ExamenService {

	private static final String SERVER = "SERVER: ";

	List<Examen> data = null;
	ServerResponse responseServer = null;

	public List<Examen> listarExamenesResponse() {
		data = null;
		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<List<Examen>> respuesta = interfaz.obtenerListaExamenes("Bearer " + Token.getInstance().getToken());
		try {
			Response<List<Examen>> response = respuesta.execute();
			data = response.body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public ServerResponse borrarExamenResponse(String idExamen) {
		responseServer = null;
		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<ServerResponse> respuesta = interfaz.borrarExamen("Bearer " + Token.getInstance().getToken(), idExamen);
		try {
			Response<ServerResponse> response = respuesta.execute();
			responseServer = response.body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseServer;
	}

	public ServerResponse cargarCalificacionesExamenResponse(String idExamen, List<Estudiante_Examen> lstNotasExamen) {
		responseServer = null;
		JsonArray ja = new JsonArray();

		lstNotasExamen.forEach(estudiante -> {
			JsonObject jo = new JsonObject();
			jo.addProperty("id", estudiante.getId());
			jo.addProperty("nota", estudiante.getNota());
			ja.add(jo);
		});

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);

		Call<ServerResponse> respuesta = interfaz.cargarCalificacionesExamen("Bearer " + Token.getInstance().getToken(), ja ,idExamen);
		try {
			Response<ServerResponse> response = respuesta.execute();
			responseServer = response.body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseServer;
	}

	public ServerResponse modificarExamenResponse(Examen examen) {
		responseServer = null;
		JsonObject jo = new JsonObject();

		jo.addProperty("fecha", Variables.getSdf().format(examen.getFecha()));
		jo.addProperty("hora", examen.getHora());

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);

		Call<ServerResponse> respuesta = interfaz.modificarExamen("Bearer " + Token.getInstance().getToken(), jo ,examen.getId().toString());
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

	public ServerResponse ingresarExamenResponse(Examen examen) {
		responseServer = null;
		JsonObject jo = new JsonObject();

		jo.addProperty("fecha", Variables.getSdf().format(examen.getFecha()));
		jo.addProperty("hora", examen.getHora());

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);

		Call<ServerResponse> respuesta = interfaz.ingresarExamen("Bearer " + Token.getInstance().getToken(), jo ,examen.getNombreAsignatura());
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
