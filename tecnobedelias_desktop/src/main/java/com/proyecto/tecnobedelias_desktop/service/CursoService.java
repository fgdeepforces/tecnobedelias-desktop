package com.proyecto.tecnobedelias_desktop.service;

import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import com.proyecto.tecnobedelias_desktop.model.Curso_Estudiante;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CursoService {

	List<Curso> data = null;
	boolean responseServer = false;

	public List<Curso> listarCursosResponse() {

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<List<Curso>> respuesta = interfaz.obtenerListaCursos("Bearer " + Token.getInstance().getToken());
		respuesta.enqueue(new Callback<List<Curso>>() {

			@Override
			public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
				if (response.isSuccessful()) {
					try {
						data = response.body();
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<List<Curso>> call, Throwable t) {
				t.printStackTrace();
			}

		});
		if (respuesta.isExecuted()) {
			try {
				Thread.sleep(1000); // AVG 1000
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public boolean borrarCursoResponse(String idCurso) {

		responseServer = false;

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<Boolean> respuesta = interfaz.borrarCurso("Bearer " + Token.getInstance().getToken(), idCurso);

		respuesta.enqueue(new Callback<Boolean>() {

			@Override
			public void onResponse(Call<Boolean> call, Response<Boolean> response) {
				if (response.isSuccessful()) {
					try {
						responseServer = response.body();
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<Boolean> call, Throwable t) {
				t.printStackTrace();
			}

		});
		if (respuesta.isExecuted()) {
			try {
				Thread.sleep(1000); // AVG 1000
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return responseServer;
	}

	public boolean cargarCalificacionesCursoResponse(String idCurso, List<Curso_Estudiante> lstNotasCurso) {
		responseServer = false;
		JsonArray ja = new JsonArray();

		lstNotasCurso.forEach(estudiante -> {
			JsonObject jo = new JsonObject();
			jo.addProperty("apellido", estudiante.getApellido());
			jo.addProperty("estado", estudiante.getEstado());
			jo.addProperty("id", estudiante.getId());
			jo.addProperty("nombre", estudiante.getNombre());
			jo.addProperty("nota", estudiante.getNota());
			ja.add(jo);
		});

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);

		//TODO: Verificar con Leo como recepciona el JsonArray
		Call<Boolean> respuesta = interfaz.cargarCalificacionesCurso("Bearer " + Token.getInstance().getToken(), ja ,idCurso);

		respuesta.enqueue(new Callback<Boolean>() {

			@Override
			public void onResponse(Call<Boolean> call, Response<Boolean> response) {
				if (response.isSuccessful()) {
					try {
						responseServer = response.body();
					}catch(NullPointerException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<Boolean> call, Throwable t) {
				t.printStackTrace();
			}

		});
		if (respuesta.isExecuted()) {
			try {
				Thread.sleep(1000); //AVG 1000
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return responseServer;
	}

}
