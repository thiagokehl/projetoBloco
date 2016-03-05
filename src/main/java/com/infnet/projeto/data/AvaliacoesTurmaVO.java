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
@JsonPropertyOrder({"identificacao", "categoriaQuestao", "questao", "resposta"})
public class AvaliacoesTurmaVO {
    @JsonProperty
    private String identificacao;
    @JsonProperty
    private String categoriaQuestao;
    @JsonProperty
    private String questao;
    @JsonProperty
    private String resposta;

    public String getIdentificacao() {
        return identificacao;
    }

    public String getCategoriaQuestao() {
        return categoriaQuestao;
    }

    public String getQuestao() {
        return questao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public void setCategoriaQuestao(String categoriaQuestao) {
        this.categoriaQuestao = categoriaQuestao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
    
}
