package com.proyecto.tecnobedelias_desktop.interfaces;

import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.proyecto.tecnobedelias_desktop.model.Actividad;
import com.proyecto.tecnobedelias_desktop.model.Carrera;
import com.proyecto.tecnobedelias_desktop.model.Curso;
import com.proyecto.tecnobedelias_desktop.model.Examen;
import com.proyecto.tecnobedelias_desktop.model.RespuestaApiLogin;
import com.proyecto.tecnobedelias_desktop.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceInterface{

	@POST("login")
	Call <RespuestaApiLogin> obtenerRespuesta(@Body JsonObject DataUsuario);

	@GET("curso/listar")
	Call <List<Curso>> obtenerListaCursos(@Header("Authorization") String token);

	@POST("curso/crear")
	Call <ServerResponse> ingresarCurso(@Header("Authorization") String token, @Body JsonObject DataCurso, @Query("nombre") String nombre );

	@POST("curso/modificar")
	Call <ServerResponse> modificarCurso(@Header("Authorization") String token, @Body JsonObject DataCurso, @Query("cursoId") String idCurso );

	@GET("curso/borrar")
	Call <ServerResponse> borrarCurso(@Header("Authorization") String token, @Query("cursoId") String idCurso );

	@POST("inscripcion/ingresarcalificacionescurso")
	Call <ServerResponse> cargarCalificacionesCurso(@Header("Authorization") String token, @Body JsonArray DataNotasCurso, @Query("cursoId") String idCurso );

	@POST("examen/crear")
	Call <ServerResponse> ingresarExamen(@Header("Authorization") String token, @Body JsonObject DataExamen, @Query("nombre") String nombre );

	@GET("examen/listar")
	Call <List<Examen>> obtenerListaExamenes(@Header("Authorization") String token);

	@POST("examen/modificar")
	Call <ServerResponse> modificarExamen(@Header("Authorization") String token, @Body JsonObject DataExamen, @Query("examenId") String idExamen );

	@GET("examen/borrar")
	Call <ServerResponse> borrarExamen(@Header("Authorization") String token, @Query("examenId") String idExamen );

	@POST("inscripcion/ingresarcalificacionesexamen")
	Call <ServerResponse> cargarCalificacionesExamen(@Header("Authorization") String token, @Body JsonArray DataNotasExamen, @Query("cursoId") String idExamen );

	@GET("carrera/listar")
	Call <List<Carrera>> obtenerListaCarreras(@Header("Authorization") String token);

	@GET("usuario/escolaridad")
	Call <List<Actividad>> obtenerListaActividades(@Header("Authorization") String token, @Query("cedula") String cedula );
}
