package com.proyecto.tecnobedelias_desktop.service;

import java.util.List;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Actividad;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsuarioService {

	List<Actividad> data = null;

	public List<Actividad> listarActividadesResponse(String cedula) {

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<List<Actividad>> respuesta = interfaz.obtenerListaActividades("Bearer " + Token.getInstance().getToken(), cedula);
		respuesta.enqueue(new Callback<List<Actividad>>() {

			@Override
			public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
				if (response.isSuccessful()) {
					try {
						System.out.println("onResponse");
						data = response.body();
						if(data != null) {
							System.out.println("data no es null");
							System.out.println("devolvio " + data.size());
						}else {
							System.out.println("data es null");
						}
					}catch(NullPointerException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<List<Actividad>> call, Throwable t) {
				System.out.println("onFailure");
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
