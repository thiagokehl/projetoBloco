package com.infnet.projeto.data;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class AvaliacaoAluno{
	@JsonProperty
	private String id;
	@JsonProperty
	private Aluno aluno;
	@JsonProperty
	private String finalizada;
	@JsonProperty
	private QuestionarioResposta questionarioResposta;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public String getFinalizada() {
		return finalizada;
	}
	public void setFinalizada(String finalizada) {
		this.finalizada = finalizada;
	}
	public QuestionarioResposta getQuestionarioResposta() {
		return questionarioResposta;
	}
	public void setQuestionarioResposta(QuestionarioResposta questionarioResposta) {
		this.questionarioResposta = questionarioResposta;
	}
}
