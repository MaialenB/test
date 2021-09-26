package users;

import java.util.UUID;

public class Usuario {

	// atributos de la clase Usuario
	
	public String name = "";
	public String surname = "";
	public String email = "";
	public int phone = 0;
	public String token = "";
	
	
	// constructores

	public Usuario() {
		super();
	}

	public Usuario(int phone, String name, String surname, String email) {
		super();
		this.phone = phone;
		this.email = email;
		this.surname = surname;
		this.name = email;
		
		// la variable token se creará y será única
		
		token = UUID.randomUUID().toString();
	}
	
	
	// getters y setters
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	/*
	 * Método para imprimir toda la información de un usuario
	 */
	
	@Override
	public String toString() {
		return "Info usuario [Numero telefono =" + phone + ", Nombre =" + name + ", Apellido ="
				+ surname + ", Email =" + email + ", Token ="+ token +"]\n";
	}
	
	
	
}
