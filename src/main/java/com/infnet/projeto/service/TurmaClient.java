/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infnet.projeto.service;

import com.infnet.projeto.data.Turma;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 *
 * @author matheus
 */
public class TurmaClient {
    
	public static Turma getInfo(String id){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8888/services/rest/turma/" + id);
		Turma turma  = webResource.get(Turma.class);

		return turma;
	}    
}
