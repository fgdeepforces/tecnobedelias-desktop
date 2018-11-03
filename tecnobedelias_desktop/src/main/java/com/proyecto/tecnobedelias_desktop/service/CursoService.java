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
			jo.addProperty("id", estudiante.getId_estudiante());
			jo.addProperty("nota", estudiante.getNota());
			ja.add(jo);
		});

		System.out.println("idCurso: " + idCurso);
		System.out.println(ja.toString());

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

	public boolean modificarCursoResponse(Curso curso) {
		responseServer = false;

		JsonObject jo = new JsonObject();
		JsonArray ja_estudiantes = new JsonArray();
		JsonArray ja_horarios = new JsonArray();
		jo.addProperty("anio", curso.getAnio());

		curso.getCursoEstudiante().forEach(estudiante -> {
			JsonObject jo_estudiante = new JsonObject();
			jo_estudiante.addProperty("apellido", estudiante.getApellido());
			jo_estudiante.addProperty("estado", estudiante.getEstado());
			jo_estudiante.addProperty("id", estudiante.getId());
			jo_estudiante.addProperty("nombre", estudiante.getNombre());
			jo_estudiante.addProperty("nota", estudiante.getNota());
			ja_estudiantes.add(jo_estudiante);
		});

		jo.add("cursoEstudiante", ja_estudiantes);
		jo.addProperty("fechaFin", curso.getFechaFin());
		jo.addProperty("fechaInicio", curso.getFechaInicio());

		curso.getHorarios().forEach(horario -> {
			JsonObject jo_horario = new JsonObject();
			jo_horario.addProperty("dia", horario.getDia());
			jo_horario.addProperty("horaFin", horario.getHoraFin());
			jo_horario.addProperty("horaInicio", horario.getHoraInicio());
			jo_horario.addProperty("id", horario.getId());
			ja_horarios.add(jo_horario);
		});

		jo.add("horarios", ja_horarios);
		jo.addProperty("id", curso.getId());
		jo.addProperty("nombreAsignatura", curso.getNombreAsignatura());
		jo.addProperty("semestre", curso.getSemestre());

		System.out.println(jo.toString());

		Retrofit retro = Token.getInstance().getRetro();
		ServiceInterface interfaz = retro.create(ServiceInterface.class);

		//TODO: Verificar con Leo como recepciona el JsonArray
		Call<Boolean> respuesta = interfaz.modificarCurso("Bearer " + Token.getInstance().getToken(), jo ,curso.getId().toString());

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
