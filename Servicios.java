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

	
	public static List<Usuario> usuarios = new ArrayList<Usuario>();
	//public HttpServletRequest httpReq = (HttpServletRequest) request;

	public Servicios( List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
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
		
			String header = httpHeaders.getRequestHeader("X-WEB-KEY").get(0);
			if (header == "Test2021") {
				usuarios.add(usuario);
				return Response.status(201).entity(usuario).build();
			}
			return Response.status(400).build();
		
	}
	
	
	@GET
	@Path("/check")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response check(@Context HttpHeaders httpHeaders) {
		try {
			String header_key = httpHeaders.getRequestHeader("X-WEB-KEY").get(0);
			String header_token = httpHeaders.getRequestHeader("X-DS-TOKEN").get(0);
			if (header_key == "Test2021") {
				for (Usuario user: usuarios) {
					if (user.getToken().equals(header_token)){
						return Response.status(200).build();
					}	
				}	
			}

		}finally {
			throw new WebApplicationException(401);
		}
	
	}
	
	/*
	@PostMapping("/register")
	public ResponseEntity<Usuario> registerUser(@RequestBody Usuario user) {
	    //log.info("Request to create student: {}", user);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String url = "https://{url}/register";
		HttpPost httpPost = new HttpPost(url);

		httpPost.addHeader("X-WEB-KEY" , "Test2021");

		HttpResponse response = httpclient.execute(httpPost);
		
		
	    Usuario newUser = usuarios.add(user);
	    return new ResponseEntity<>(user.token, HttpStatus.CREATED);
	}
	
	@GetMapping("/check")
	public ResponseEntity<> checkToken(@){
		return ResponseEntity<Success>();
	}*/
	
	
	
	
}
