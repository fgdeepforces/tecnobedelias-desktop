package com.proyecto.tecnobedelias_desktop.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.itextpdf.text.log.SysoCounter;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Actividad;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import com.proyecto.tecnobedelias_desktop.model.ServerResponse;
import com.proyecto.tecnobedelias_desktop.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsuarioService {

	List<Actividad> data = null;
	Usuario user = null;

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

	public Usuario obtenerUsuarioPorCedulaResponse(String cedula) {
		user = null;
		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<Usuario> respuesta = interfaz.obtenerUsuarioPorCedula("Bearer " + Token.getInstance().getToken(), cedula);
		try {
			Response<Usuario> response = respuesta.execute();
			while(!response.isSuccessful()) {
				System.out.println("respuesta fallida");
				Thread.sleep(1000);
			}
			user = response.body();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return user;
	}

}
