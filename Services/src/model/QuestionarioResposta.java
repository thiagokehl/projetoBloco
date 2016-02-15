package model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class QuestionarioResposta{
	@JsonProperty
	private List<QuestaoResposta> respostas;

	public List<QuestaoResposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<QuestaoResposta> respostas) {
		this.respostas = respostas;
	}
}
