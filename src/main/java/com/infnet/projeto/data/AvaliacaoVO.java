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
    @JsonProperty
    private String periodoDisponibilizado;

    public AvaliacaoVO(Long idAvaliacao, String curso, String disciplina, String turma, String periodoDisponibilizado) {
        this.idAvaliacao = idAvaliacao;
        this.curso = curso;
        this.disciplina = disciplina;
        this.turma = turma;
        this.periodoDisponibilizado = periodoDisponibilizado;
    }
    
    
    
    public Long getIdAvaliacao(){
        return this.idAvaliacao;
    }
    
    public void setIdAvaliacao(Long idAvaliacao){
        this.idAvaliacao = idAvaliacao;
    }
    
    public String getCurso(){
        return this.curso;
    }
    
    public void setCurso(String curso){
        this.curso = curso;        
    }

    public String getDisciplina(){
        return this.disciplina;
    }
    
    public void setDisciplina(String disciplina){
        this.disciplina = disciplina;
    }
    
    public String getTurma(){
        return this.turma;
    }

    public void setTurma(String turma){
        this.turma = turma;
    }

    public String getPeriodoDisponibilizado() {
        return periodoDisponibilizado;
    }

    public void setPeriodoDisponibilizado(String periodoDisponibilizado) {
        this.periodoDisponibilizado = periodoDisponibilizado;
    }
 
}
