package com.proyecto.tecnobedelias_desktop.interfaces;

import java.util.List;
import com.google.gson.JsonObject;
import com.proyecto.tecnobedelias_desktop.model.Carrera;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import com.proyecto.tecnobedelias_desktop.model.Examen;
import com.proyecto.tecnobedelias_desktop.model.RespuestaApiLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceInterface{

	@POST("login")
	Call <RespuestaApiLogin> obtenerRespuesta(@Body JsonObject DataUsuario);

	@GET("curso/listar")
	Call <List<Curso>> obtenerListaCursos(@Header("Authorization") String token);

	@GET("examen/listar")
	Call <List<Examen>> obtenerListaExamenes(@Header("Authorization") String token);

	@GET("carrera/listar")
	Call <List<Carrera>> obtenerListaCarreras(@Header("Authorization") String token);
}
