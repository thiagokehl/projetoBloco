/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infnet.projeto.managedBean;

import com.infnet.projeto.data.QuestaoAvlAlunoVO;
import com.infnet.projeto.service.QuestionarioClient;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author matheus
 */
@ManagedBean
@ViewScoped
public class QuestionarioMBean {
    private List<QuestaoAvlAlunoVO> questionario = QuestionarioClient.getQuestionarioAvlAluno();

    public void setQuestionario(List<QuestaoAvlAlunoVO> questionario) {
        this.questionario = questionario;
    }

    public List<QuestaoAvlAlunoVO> getQuestionario() {
        return questionario;
    }
}
