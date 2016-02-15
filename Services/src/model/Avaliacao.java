package model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@XmlRootElement
@JsonPropertyOrder({"id", "inicio", "fim"})
public class Avaliacao {
	@JsonProperty
	private Long id;
	@JsonProperty
	private Date inicio;
	@JsonProperty
	private Date fim;
	@JsonProperty 
	private Questionario questionario;
	@JsonProperty
	private List<AvaliacaoAluno> avaliacoesAlunos; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public List<AvaliacaoAluno> getAvaliacoesAlunos() {
		return avaliacoesAlunos;
	}
	public void setAvaliacoesAlunos(List<AvaliacaoAluno> avaliacoesAlunos) {
		this.avaliacoesAlunos = avaliacoesAlunos;
	}
}
