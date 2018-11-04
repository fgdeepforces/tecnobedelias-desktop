package com.proyecto.tecnobedelias_desktop.service;

import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Estudiante_Examen;
import com.proyecto.tecnobedelias_desktop.model.Examen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ExamenService {

	List<Examen> data = null;
	boolean responseServer = false;

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
				e.printStackTrace();
			}
		}
		return data;
	}

	public boolean borrarExamenResponse(String idExamen) {

		responseServer = false;

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<Boolean> respuesta = interfaz.borrarExamen("Bearer " + Token.getInstance().getToken(), idExamen);

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

	public boolean cargarCalificacionesExamenResponse(String idExamen, List<Estudiante_Examen> lstNotasExamen) {
		responseServer = false;
		JsonArray ja = new JsonArray();

		lstNotasExamen.forEach(estudiante -> {
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
		Call<Boolean> respuesta = interfaz.cargarCalificacionesExamen("Bearer " + Token.getInstance().getToken(), ja ,idExamen);

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

	public boolean modificarExamenResponse(Examen examen) {
		responseServer = false;

		JsonObject jo = new JsonObject();
		jo.addProperty("fecha", examen.getFecha());
		jo.addProperty("hora", examen.getHora());
		jo.addProperty("nombreAsignatura", examen.getNombreAsignatura());

		System.out.println(jo.toString());

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);

		Call<Boolean> respuesta = interfaz.modificarExamen("Bearer " + Token.getInstance().getToken(), jo ,examen.getId().toString());

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

	public boolean ingresarExamenResponse(Examen examen) {
		responseServer = false;

		JsonObject jo = new JsonObject();
		jo.addProperty("fecha", examen.getFecha());
		jo.addProperty("hora", examen.getHora());
		jo.addProperty("nombreAsignatura", examen.getNombreAsignatura());

		System.out.println(jo.toString());

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);

		Call<Boolean> respuesta = interfaz.ingresarExamen("Bearer " + Token.getInstance().getToken(), jo ,examen.getNombreAsignatura());

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
