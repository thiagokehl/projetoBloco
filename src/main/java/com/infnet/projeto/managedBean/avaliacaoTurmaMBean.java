/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infnet.projeto.managedBean;

import com.infnet.projeto.data.Aluno;
import com.infnet.projeto.data.Avaliacao;
import com.infnet.projeto.data.AvaliacaoAluno;
import com.infnet.projeto.data.AvaliacaoAlunoVO;
import com.infnet.projeto.data.AvaliacoesTurmaVO;
import com.infnet.projeto.data.Bloco;
import com.infnet.projeto.data.Curso;
import com.infnet.projeto.data.Disciplina;
import com.infnet.projeto.data.QuestaoResposta;
import com.infnet.projeto.data.Turma;
import com.infnet.projeto.service.CursoClient;
import com.infnet.projeto.service.TurmaClient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author matheus
 */
@ManagedBean
@ViewScoped
public class avaliacaoTurmaMBean extends BaseMBean{
    private List<AvaliacaoAlunoVO> allAvaliacoes;
    private List<AvaliacoesTurmaVO> avalicoesFinalizadas;
            
    private String curso;
    private Disciplina disciplina;
    private String professor;
    private String turma;


    @PostConstruct
    public void init() {
            allAvaliacoes = new ArrayList<AvaliacaoAlunoVO>(); 
            avalicoesFinalizadas = new ArrayList<AvaliacoesTurmaVO>();

            List<Curso> allCursos = CursoClient.getAllCursos();
            INITIALIZED:
            for (Curso oneCurso: allCursos){
                Curso curso = CursoClient.getInfo(oneCurso.getId().toString());
                for(Bloco oneBloco : curso.getBlocos()){
                     for (Disciplina oneDisciplina : oneBloco.getDisciplinas()){
                         if (oneDisciplina.getTurmas() != null){
                            for (Turma oneTurma : oneDisciplina.getTurmas()){                                    
                                Turma turma = TurmaClient.getInfo(oneTurma.getId().toString());

                                if (turma.getAvaliacao() != null && turma.getAvaliacao().getId() == 1){
                                    this.curso = curso.getNome();
                                    this.disciplina = oneDisciplina;
                                    this.professor = turma.getProfessor().getNome();
                                    this.turma = turma.getId().toString();
                                    
                                    if (turma.getAlunos() != null){
                                        for (Aluno oneAluno : turma.getAlunos()){
                                            AvaliacaoAlunoVO avaliacaoAlunoVO = new AvaliacaoAlunoVO();
                                            avaliacaoAlunoVO.setIdAvaliacaoAluno(obterIdAvaliacaoAluno(oneAluno, turma.getAvaliacao().getAvaliacoesAlunos()));
                                            if (avaliacaoAlunoVO.getIdAvaliacaoAluno() != null){
                                                avaliacaoAlunoVO.setFinalizada(Boolean.TRUE);
                                                
                                                AvaliacoesTurmaVO oneAvaliacao = new AvaliacoesTurmaVO();
                                                oneAvaliacao.setIdentificacao(oneAluno.getNome() + " - " + oneDisciplina.getNome() + " - " + oneDisciplina.getSemestre() + " - Turma: " + turma.getId().toString());
                                                oneAvaliacao.setCategoriaQuestao(null);
                                                oneAvaliacao.setQuestao(null);
                                                oneAvaliacao.setResposta(null);
                                                avalicoesFinalizadas.add(oneAvaliacao);
                                                
                                                avalicoesFinalizadas.addAll(conteudoAvlAluno(oneAluno, turma.getAvaliacao().getAvaliacoesAlunos()));
                                            } else {
                                                avaliacaoAlunoVO.setFinalizada(Boolean.FALSE);
                                            }
                                            avaliacaoAlunoVO.setNomeAluno(oneAluno.getNome());
                                            avaliacaoAlunoVO.setMatricula(oneAluno.getMatricula());

                                            allAvaliacoes.add(avaliacaoAlunoVO);
                                        }
                                    
                                        break INITIALIZED;
                                    }
                                }                                   
                            }
                         }
                     }
                 }                       
             }
    }    

    private Long obterIdAvaliacaoAluno(Aluno aluno, List<AvaliacaoAluno> avaliacoesAlunos){            
        for (AvaliacaoAluno oneAvlAluno : avaliacoesAlunos){
            if (oneAvlAluno.getAluno().getId().equals(aluno.getId()) && "S".equals(oneAvlAluno.getFinalizada())){
                return oneAvlAluno.getId();
            }
        }

        return null;
    }        
    
    private List<AvaliacoesTurmaVO> conteudoAvlAluno(Aluno aluno, List<AvaliacaoAluno> avaliacoesAlunos){
        List<AvaliacoesTurmaVO> questoes = new ArrayList<AvaliacoesTurmaVO>();
        
        String categoria = null;
        
        for (AvaliacaoAluno oneAvlAluno : avaliacoesAlunos){
            if (oneAvlAluno.getAluno().getId().equals(aluno.getId()) && "S".equals(oneAvlAluno.getFinalizada())){
                for (QuestaoResposta qstResposta : oneAvlAluno.getQuestionarioResposta().getRespostas()){
                    
                    AvaliacoesTurmaVO questao = new AvaliacoesTurmaVO();
                    questao.setIdentificacao(null);
                    if (!qstResposta.getCategoria().equals(categoria)){
                        categoria = qstResposta.getCategoria();
                        questao.setCategoriaQuestao(categoria);
                    } else {
                        questao.setCategoriaQuestao(null);
                    }
                    
                    questao.setQuestao(qstResposta.getTexto());
                    questao.setResposta(qstResposta.getResposta().getSignificado());   
                    
                    questoes.add(questao);
                }                   
            }
        }
        
        // Resgistro vazio para inserir uma linha em branco na grade
        AvaliacoesTurmaVO questao = new AvaliacoesTurmaVO();
        questoes.add(questao);
        
        return questoes;
    }

    public List<AvaliacaoAlunoVO> getAllAvaliacoes(){
        return this.allAvaliacoes;
    }

    public void setAllAvaliacoes(List<AvaliacaoAlunoVO> allAvaliacoes){
        this.allAvaliacoes = allAvaliacoes;
    }
    
    public List<AvaliacoesTurmaVO> getAvalicoesFinalizadas(){
        return this.avalicoesFinalizadas;
    }
    
    public void setAvalicoesFinalizadas(List<AvaliacoesTurmaVO> avalicoesFinalizadas){
        this.avalicoesFinalizadas = avalicoesFinalizadas;
    }
    
    public String getCurso() {
        return curso;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public String getTurma() {
        return turma;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }        
}
