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
@JsonPropertyOrder({"idAvaliacaoAluno", "nomeAluno", "matricula", "finalizada"})
public class AvaliacaoAlunoVO {
	@JsonProperty
	private Long idAvaliacaoAluno;
	@JsonProperty
	private String nomeAluno;
	@JsonProperty
	private String matricula;
	@JsonProperty
	private Boolean finalizada;        
	
	public Long getIdAvaliacaoAluno() {
		return idAvaliacaoAluno;
	}
	public void setIdAvaliacaoAluno(Long idAvaliacaoAluno) {
		this.idAvaliacaoAluno = idAvaliacaoAluno;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Boolean getFinalizada() {
		return finalizada;
	}
	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}
}
