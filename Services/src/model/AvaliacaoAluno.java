package model;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class AvaliacaoAluno{
	@JsonProperty
	private Aluno aluno;
	@JsonProperty
	private String finalizada;
	private QuestionarioResposta questionarioResposta;
	
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
