package vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@XmlRootElement
@JsonPropertyOrder({"idResposta", "categoriaQuestao", "questao", "resposta"})
public class QuestaoAvlAlunoVO implements Serializable{

 
	/**
	 * 
	 */
	private static final long serialVersionUID = 2267921422522449401L;
	
	Long idResposta;
	String categoriaQuestao;
	String questao;
	String resposta;
	public Long getIdResposta() {
		return idResposta;
	}
	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}
	public String getCategoriaQuestao() {
		return categoriaQuestao;
	}
	public void setCategoriaQuestao(String categoriaQuestao) {
		this.categoriaQuestao = categoriaQuestao;
	}
	public String getQuestao() {
		return questao;
	}
	public void setQuestao(String questao) {
		this.questao = questao;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
}
