/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infnet.projeto.managedBean;

import com.infnet.projeto.data.AvaliacaoDispVO;
import com.infnet.projeto.service.AvaliacaoClient;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 *
 * @author matheus
 */
@ManagedBean
@ViewScoped
public class AvaliacaoBean {
    
    private List<AvaliacaoDispVO> avaliacoesDisp = AvaliacaoClient.getAvaliacoesDisp();
    
//    private ArrayList<AvaliacaoDispVO> testeInitAvaliacoesDisp(){
//        ArrayList<AvaliacaoDispVO> avaliacaoDisp = new ArrayList<AvaliacaoDispVO>();
//        
//        AvaliacaoDispVO avaliacao =  new AvaliacaoDispVO();
//        avaliacao.setDisciplinaNome("Analise OO");
//        avaliacao.setProfessorNome("juca");
//        avaliacao.setSemestreDisciplina("012016");
//        
//        avaliacaoDisp.add(avaliacao);
//        
//        return avaliacaoDisp;
//    }
    
    public List<AvaliacaoDispVO> getAvaliacoesDisp(){
        return this.avaliacoesDisp;
    }
    
    public void setAvaliacoesDisp(List<AvaliacaoDispVO> avaliacoesDisp){
        this.avaliacoesDisp = avaliacoesDisp;
    } 
    
}
