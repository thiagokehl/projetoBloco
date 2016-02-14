package model;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class QuestaoResposta extends Questao{
	@JsonProperty
	private Likert resposta;

	public Likert getResposta() {
		return resposta;
	}

	public void setResposta(Likert resposta) {
		this.resposta = resposta;
	}
}
