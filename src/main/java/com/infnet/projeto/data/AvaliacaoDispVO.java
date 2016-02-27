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
@JsonPropertyOrder({"avaliacaoID", "alunoID", "disciplinaNome", "professorNome", "semestreDisciplina"})
public class AvaliacaoDispVO{
        @JsonProperty
	private Long avaliacaoID;
	@JsonProperty
        private Long alunoID;
	@JsonProperty
        private String disciplinaNome;
        @JsonProperty
	private String professorNome;
        @JsonProperty
	private String semestreDisciplina;

	public Long getAvaliacaoID() {
		return avaliacaoID;
	}

	public void setAvaliacaoID(Long avaliacaoID) {
		this.avaliacaoID = avaliacaoID;
	}

	public Long getAlunoID() {
		return alunoID;
	}

	public void setAlunoID(Long alunoID) {
		this.alunoID = alunoID;
	}

	public String getDisciplinaNome() {
		return disciplinaNome;
	}

	public void setDisciplinaNome(String disciplinaNome) {
		this.disciplinaNome = disciplinaNome;
	}

	public String getProfessorNome() {
		return professorNome;
	}

	public void setProfessorNome(String professorNome) {
		this.professorNome = professorNome;
	}

	public String getSemestreDisciplina() {
		return semestreDisciplina;
	}

	public void setSemestreDisciplina(String semestreDisciplina) {
		this.semestreDisciplina = semestreDisciplina;
	}
    
}
