/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infnet.projeto.service;

import com.infnet.projeto.data.AvaliacaoDispVO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.List;

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
}
