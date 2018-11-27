package com.proyecto.tecnobedelias_desktop.service;

import java.io.IOException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.proyecto.tecnobedelias_desktop.interfaces.ServiceInterface;
import com.proyecto.tecnobedelias_desktop.model.Authorizacion;
import com.proyecto.tecnobedelias_desktop.model.RespuestaApiLogin;
import com.proyecto.tecnobedelias_desktop.model.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginService {

	public String loginResponse(String username, String password) {
		Retrofit retro = Token.getInstance().getRetro();
		JsonObject datos = new JsonObject();
		datos.addProperty("username", username);
		datos.addProperty("password", password);
		ServiceInterface interfaz = retro.create(ServiceInterface.class);
		Call<RespuestaApiLogin> respuesta = interfaz.obtenerRespuesta(datos);
		try {
			Response<RespuestaApiLogin> response = respuesta.execute();
			if (response.isSuccessful()) {
				String token = response.headers().get("Authorization");
				try {
					DecodedJWT jwt = JWT.decode(token.substring(7));
					System.out.println("Subject: " + jwt.getSubject());
					System.out.println("Token: " + jwt.getToken());
					String rol = jwt.getClaims().get("roles").asList(Authorizacion.class).get(0).getAuthority();
					System.out.println("Rol: " + rol);
					if (rol != null) {
						if (rol.equalsIgnoreCase("ROLE_FUNCIONARIO")) {
							Token.getInstance().setToken(jwt.getToken());
							Token.getInstance().setMensaje("BIENVENIDO");
						} else {
							Token.getInstance().setMensaje("ACCESO DENEGADO");
						}
					} else {
						Token.getInstance().setMensaje("Usuario y contraseña incorrectos");
					}
				} catch (JWTDecodeException exception) {
					exception.printStackTrace();
				}
			} else {
				Token.getInstance().setMensaje("Usuario y contraseña incorrectos");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		respuesta.enqueue(new Callback<RespuestaApiLogin>() {
//
//			@Override
//			public void onResponse(Call<RespuestaApiLogin> call, Response<RespuestaApiLogin> response) {
//				// TODO Auto-generated method stub
//				if (response.isSuccessful()) {
//					String token = response.headers().get("Authorization");
//					try {
//						DecodedJWT jwt = JWT.decode(token.substring(7));
//						System.out.println("Subject: " + jwt.getSubject());
//						System.out.println("Token: " + jwt.getToken());
//						String rol = jwt.getClaims().get("roles").asList(Authorizacion.class).get(0).getAuthority();
//						System.out.println("Rol: " + rol);
//						if (rol != null) {
//							if (rol.equalsIgnoreCase("ROLE_FUNCIONARIO")) {
//								Token.getInstance().setToken(jwt.getToken());
//								Token.getInstance().setMensaje("BIENVENIDO");
//							} else {
//								Token.getInstance().setMensaje("ACCESO DENEGADO");
//							}
//						} else {
//							Token.getInstance().setMensaje("Usuario y contraseña incorrectos");
//						}
//					} catch (JWTDecodeException exception) {
//						exception.printStackTrace();
//					}
//				} else {
//					Token.getInstance().setMensaje("Usuario y contraseña incorrectos");
//				}
//			}
//
//			@Override
//			public void onFailure(Call<RespuestaApiLogin> call, Throwable t) {
//				// TODO Auto-generated method stub
//				t.printStackTrace();
//			}
//
//		});
//		if (respuesta.isExecuted()) {
//			try {
//				Thread.sleep(1000); //AVG 1000
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		System.out.println("Respuesta loginResponse -> " + Token.getInstance().getMensaje());
		return Token.getInstance().getMensaje();
	}

}