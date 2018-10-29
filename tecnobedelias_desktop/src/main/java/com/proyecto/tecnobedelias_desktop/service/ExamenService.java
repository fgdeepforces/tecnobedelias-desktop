package com.proyecto.tecnobedelias_desktop.service;

import java.util.List;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Examen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ExamenService {

	List<Examen> data = null;

	public List<Examen> listarExamenesResponse() {

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<List<Examen>> respuesta = interfaz.obtenerListaExamenes("Bearer " + Token.getInstance().getToken());
		respuesta.enqueue(new Callback<List<Examen>>() {

			@Override
			public void onResponse(Call<List<Examen>> call, Response<List<Examen>> response) {
				if (response.isSuccessful()) {
					try {
						data = response.body();
					}catch(NullPointerException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<List<Examen>> call, Throwable t) {
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
