package com.proyecto.tecnobedelias_desktop.service;

import java.util.List;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Carrera;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CarreraService {

	List<Carrera> data = null;

	public List<Carrera> listarCarrerasResponse() {

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<List<Carrera>> respuesta = interfaz.obtenerListaCarreras("Bearer " + Token.getInstance().getToken());
		respuesta.enqueue(new Callback<List<Carrera>>() {

			@Override
			public void onResponse(Call<List<Carrera>> call, Response<List<Carrera>> response) {
				if (response.isSuccessful()) {
					try {
						System.out.println("onResponse");
						data = response.body();
						if(data != null) {
							System.out.println("data no es null");
							data.forEach(carreras -> {
								System.out.println(carreras.toString());
							});
						}else {
							System.out.println("data es null");
						}
					}catch(NullPointerException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call<List<Carrera>> call, Throwable t) {
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
