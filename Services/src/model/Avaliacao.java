package model;

import java.util.Calendar;
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
	private List<AvaliacaoAluno> avaliacoesAluno; 
	
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
	public List<AvaliacaoAluno> getAvaliacoesAluno() {
		return avaliacoesAluno;
	}
	public void setAvaliacoesAluno(List<AvaliacaoAluno> avaliacoesAluno) {
		this.avaliacoesAluno = avaliacoesAluno;
	}
	
	public String getStatus(){
		
		Date today = Calendar.getInstance().getTime();
		
		if(inicio.before(today)){
			return "NÃO INICIADA";
		}
		
		if(fim.after(new Date())){
			return "FINALIZADA";
		}
		
		return "ABERTA";
	}
}
