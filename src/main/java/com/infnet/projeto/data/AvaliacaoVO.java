/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infnet.projeto.data;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 *
 * @author matheus
 */
@XmlRootElement
@JsonPropertyOrder({"idAvaliacao", "curso", "disciplina", "turma"})
public class AvaliacaoVO {
    
    @JsonProperty
    private Long idAvaliacao;
    @JsonProperty
    private String curso;
    @JsonProperty
    private String disciplina;
    @JsonProperty
    private String turma;

    public AvaliacaoVO(Long idAvaliacao, String curso, String disciplina, String turma) {
        this.idAvaliacao = idAvaliacao;
        this.curso = curso;
        this.disciplina = disciplina;
        this.turma = turma;
    }
    
    
    
    private Long getIdAvaliacao(){
        return this.idAvaliacao;
    }
    
    private void setIdAvaliacao(Long idAvaliacao){
        this.idAvaliacao = idAvaliacao;
    }
    
    private String getCurso(){
        return this.curso;
    }
    
    private void setCurso(String curso){
        this.curso = curso;        
    }

    private String getDisciplina(){
        return this.disciplina;
    }
    
    private void setDisciplina(String disciplina){
        this.disciplina = disciplina;
    }
    
    private String getTurma(){
        return this.turma;
    }

    private void setTurma(String turma){
        this.turma = turma;
    }
 
}
