package utils;

import java.util.List;

import model.Curso;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class Teste {

	public static void main(String[] args) {
		
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8888/services/rest/curso");
		
		List<Curso> cursos  = webResource.get(new GenericType<List<Curso>>(){});

		for(Curso curso : cursos ){
			System.out.println("ID: " + curso.getId() + "\nNOME: " + curso.getNome());
		}
		}

}
