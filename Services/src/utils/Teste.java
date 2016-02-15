package utils;

import model.Curso;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Teste {

	public static void main(String[] args) {
		
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/services/rest/curso");
		Curso curso = webResource.path("1").get(Curso.class);
		System.out.println("ID: " + curso.getId() + "\nNOME: " + curso.getNome());
	}

}
