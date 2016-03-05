/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infnet.projeto.service;

import java.util.List;

import com.infnet.projeto.data.AvaliacaoAlunoVO;
import com.infnet.projeto.data.AvaliacaoDispVO;
import com.infnet.projeto.data.AvaliacaoTurma;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

/**
 *
 * @author matheus
 */
public class AvaliacaoClient {
	public static List<AvaliacaoDispVO> getAvaliacoesDisp(){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8888/services/rest/avaliacao/1");
		List<AvaliacaoDispVO> avaliacoesDisp  = webResource.get(new GenericType<List<AvaliacaoDispVO>>(){});

		return avaliacoesDisp;
	}

	public static AvaliacaoAlunoVO getAvaliacaoInfo(String avaliacaoAlunoId){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8888/services/rest/avaliacao/aluno/" + avaliacaoAlunoId);
		AvaliacaoAlunoVO avaliacaoAluno  = webResource.get(AvaliacaoAlunoVO.class);

		return avaliacaoAluno;
	}

	public static boolean criarAvaliacao(AvaliacaoTurma avaliacao){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8888/services/rest/avaliacao/create");

		ClientResponse response = webResource.accept("application/json")
				.type("application/json").post(ClientResponse.class, avaliacao);

		if (response.getStatus() != 200) {
			return false;
		}

		return true;
	}
	
	public static boolean update(AvaliacaoAlunoVO avaliacao){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8888/services/rest/avaliacao/resposta");

		ClientResponse response = webResource.accept("application/json")
				.type("application/json").post(ClientResponse.class, avaliacao);

		if (response.getStatus() != 200) {
			return false;
		}

		return true;
	}

}
