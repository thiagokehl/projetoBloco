/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infnet.projeto.data;
import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 *
 * @author matheus
 */
@XmlRootElement
@JsonPropertyOrder({"idAvaliacaoAluno", "nomeAluno", "matricula", "finalizada"})
public class AvaliacaoAlunoVO implements Serializable{
	@JsonProperty
	private String id;
	@JsonProperty
	private Long idAvaliacao;
	@JsonProperty
	private String nomeAluno;
	private String email;
	@JsonProperty
	private String matricula;
	@JsonProperty
	private Boolean finalizada;
	private String professor;
	private String disciplina;
	private Date inicio;
	private Date fim;
	private Questionario questionario; 
	private QuestionarioResposta resposta;
	private String freeText;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFreeText() {
		return freeText;
	}
	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Long getIdAvaliacao() {
		return idAvaliacao;
	}
	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
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
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public Questionario getQuestionario() {
		return questionario;
	}
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	public QuestionarioResposta getResposta() {
		return resposta;
	}
	public void setResposta(QuestionarioResposta resposta) {
		this.resposta = resposta;
	}
}
