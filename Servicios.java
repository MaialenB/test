package services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import users.Usuario;

public class Servicios {

	// lista de usuarios creados, atributos de la clase Servicio
	
	public static List<Usuario> usuarios = new ArrayList<Usuario>();

	// constructor
	
	public Servicios( List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	// getters y setters
	
	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(List<Usuario> usuarios) {
		Servicios.usuarios = usuarios;
	}
	
	
	/*
	 * End-point encargado de registrar al usuario / cliente según los 
	 * datos facilitados en formato JSON.
	 */
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Context HttpHeaders httpHeaders, Usuario usuario) {
			
			// se recogen los headers que necesitamos
		
			String header = httpHeaders.getRequestHeader("X-WEB-KEY").get(0);
			
			// si el header anterior cumple los requisitos, se creará un usuario con los datos y se 
			// devolverá un código 201
			
			if (header == "Test2021") {
				usuarios.add(usuario);
				return Response.status(201).entity(usuario).build();
			}
			
			// si no se cumplen los requisitos, se devolverá un código 400
			
			return Response.status(400).build();
		
	}
	
	/*
	 * End-point encargado de checkear que existe un usuario / cliente con el token único.
	 */
	
	@GET
	@Path("/check")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response check(@Context HttpHeaders httpHeaders) {
		try {
			
			// se recogen los headers que necesitamos
			
			String header_key = httpHeaders.getRequestHeader("X-WEB-KEY").get(0);
			String header_token = httpHeaders.getRequestHeader("X-DS-TOKEN").get(0);
			
			// se comprueba si son correctos y si es así se crea un código 200
			
			if (header_key == "Test2021") {
				for (Usuario user: usuarios) {
					if (user.getToken().equals(header_token)){
						return Response.status(200).build();
					}	
				}	
			}
			
			// si no son correctos los headers se devuelve un código 401
			
			return Response.status(401).build();
			
			
			// si ha habido algún error y el código ha fallado y no ha podido terminar de ejecutar el try,
			// se devuelve un código 401
			
		}finally {
			return Response.status(401).build();
		}
	
	}
	
	
}
