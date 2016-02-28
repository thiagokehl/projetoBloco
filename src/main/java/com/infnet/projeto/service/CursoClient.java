/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.infnet.projeto.service;

import com.infnet.projeto.data.Curso;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.List;


/**
 *
 * @author Kehlt
 */
public class CursoClient {
	public static List<Curso> getAllCursos(){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8888/services/rest/curso");
		List<Curso> cursos  = webResource.get(new GenericType<List<Curso>>(){});

		return cursos;
	}

	public static Curso getInfo(String id){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8888/services/rest/curso/" + id);
		Curso curso  = webResource.get(Curso.class);

		return curso;
	}
}
