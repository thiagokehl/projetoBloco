package com.infnet.projeto.managedBean;

import com.infnet.projeto.data.AvaliacaoVO;
import com.infnet.projeto.data.Bloco;
import com.infnet.projeto.data.Curso;
import com.infnet.projeto.data.Disciplina;
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
public class AvaliacaoListMBean extends BaseMBean{

        private List<AvaliacaoVO> allAvaliacoes;
 
         
	@PostConstruct
	public void init() {
                allAvaliacoes = new ArrayList<AvaliacaoVO>();     
                
        	List<Curso> allCursos = CursoClient.getAllCursos();
                for (Curso oneCurso: allCursos){
                    Curso curso = CursoClient.getInfo(oneCurso.getId().toString());
                    for(Bloco oneBloco : curso.getBlocos()){
                         for (Disciplina oneDisciplina : oneBloco.getDisciplinas()){
                             if (oneDisciplina.getTurmas() != null){
                                for (Turma oneTurma : oneDisciplina.getTurmas()){                                    
                                    Turma turma = TurmaClient.getInfo(oneTurma.getId().toString());                                    
                                    if (turma.getAvaliacao() != null){
                                       AvaliacaoVO avaliacaoVO = new AvaliacaoVO(turma.getAvaliacao().getId(), curso.getNome(), oneDisciplina.getNome(), turma.getId().toString());
                                       allAvaliacoes.add(avaliacaoVO);
                                    }
                                }
                             }
                         }
                     }                       
                 }
        }    
       
        public List<AvaliacaoVO> getAllAvaliacoes(){
            return this.allAvaliacoes;
        }
        
        public void setAllAvaliacoes(List<AvaliacaoVO> allAvaliacoes){
            this.allAvaliacoes = allAvaliacoes;
        }
}
