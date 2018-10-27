package com.proyecto.tecnobedelias_desktop.service;

import java.util.List;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CursoService {

	List<Curso> data = null;

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

}
