package com.proyecto.tecnobedelias_desktop.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
//import org.json.JSONArray;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;

//import org.json.HTTP;

import com.proyecto.tecnobedelias_desktop.global.Constants;
import com.proyecto.tecnobedelias_desktop.global.Token;
import com.sun.jersey.core.util.Base64;

public class LoginService {

	public String loginResponse(String username, String password) throws IOException {
		URL url = new URL(Constants.getLoginServiceUrl());
		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection)con;
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);
		String jsonToSend = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
		byte[] out = jsonToSend.getBytes(StandardCharsets.UTF_8);
		int length = out.length;

		http.setFixedLengthStreamingMode(length);
		http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		http.connect();
		try(OutputStream os = http.getOutputStream()) {
		    os.write(out);
		    String token = http.getHeaderField("Authorization").toString();
		    String jwtToken = token.substring(7);
	        System.out.println("------------ Decode JWT ------------");
	        String[] split_string = jwtToken.split("\\.");
	        String base64EncodedHeader = split_string[0];
	        String base64EncodedBody = split_string[1];
	        String base64EncodedSignature = split_string[2];

	        System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
	        Base64 base64Url = new Base64();
	        String header = new String(base64Url.decode(base64EncodedHeader));
	        System.out.println("JWT Header : " + header);

	        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
	        String body = new String(base64Url.decode(base64EncodedBody));
	        System.out.println("JWT Body : "+body);

	        int first = body.indexOf("ROLE");
	        int last = body.lastIndexOf("\"");
	        String rol = body.substring(first, last);
	        System.out.println("Rol: " + rol);

	        String sentencia;

	        if(rol.equalsIgnoreCase("ROLE_FUNCIONARIO")) {
	        	sentencia = "BIENVENIDO";
	        	//Guardo el token globalmente
	        	Token.getInstance().setToken(token.substring(7));
	        }else {
	        	sentencia = "ACCESO DENEGADO";
	        }
		    return sentencia;
		}
		// Do something with http.getInputStream()

	}

}
