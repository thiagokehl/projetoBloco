package com.infnet.projeto.data;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class QuestaoResposta extends Questao {
	@JsonProperty
	private Likert resposta;

	public int getRespostaInt() {
		if(resposta != null){
			switch (resposta) {
			case UM:
				return 1;
			case DOIS:
				return 2;
			case TRES:
				return 3;
			case QUATRO:
				return 4;
			case CINCO:
				return 5;
			}
		}
		return 0;
	}

	public void setRespostaInt(int respostaInt) {
		if(respostaInt == 1){
			resposta = Likert.UM;
		}else
			if(respostaInt == 2){
				resposta = Likert.DOIS;
			}else
				if(respostaInt == 3){
					resposta = Likert.TRES;
				}else
					if(respostaInt == 4){
						resposta = Likert.QUATRO;
					}else
						if(respostaInt == 5){
							resposta = Likert.CINCO;
						}else{
							resposta = null;
						}
	}

	public Likert getResposta() {
		return resposta;
	}

	public void setResposta(Likert resposta) {
		this.resposta = resposta;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


}
