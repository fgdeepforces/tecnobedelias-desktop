package com.proyecto.tecnobedelias_desktop.service;

import java.util.List;
import com.google.gson.JsonObject;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Curso;
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
					}catch(NullPointerException e) {
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
				Thread.sleep(1000); //AVG 1000
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

	public boolean borrarCursoResponse(String idCurso) {

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<Boolean> respuesta = interfaz.borrarCurso("Bearer " + Token.getInstance().getToken(), idCurso);

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return responseServer;
	}

}
