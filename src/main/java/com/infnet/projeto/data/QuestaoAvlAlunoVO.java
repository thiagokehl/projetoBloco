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
@JsonPropertyOrder({"idResposta", "categoriaQuestao", "questao", "resposta"})
public class QuestaoAvlAlunoVO {

    @JsonProperty
    Long idResposta;
    @JsonProperty
    String categoriaQuestao;
    @JsonProperty
    String questao;
    @JsonProperty
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
