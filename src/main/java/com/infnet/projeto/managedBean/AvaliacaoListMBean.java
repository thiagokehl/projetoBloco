package com.infnet.projeto.managedBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.infnet.projeto.data.AvaliacaoVO;
import com.infnet.projeto.data.Bloco;
import com.infnet.projeto.data.Curso;
import com.infnet.projeto.data.Disciplina;
import com.infnet.projeto.data.Turma;
import com.infnet.projeto.service.CursoClient;
import com.infnet.projeto.service.TurmaClient;
 

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
                                       String periodoDisponibilizado = null; 
                                       if (turma.getAvaliacao().getInicio() != null && turma.getAvaliacao().getFim() != null){
                                           periodoDisponibilizado = dtFormat(turma.getAvaliacao().getInicio()) + " a " + dtFormat(turma.getAvaliacao().getFim());
                                       }
                                       AvaliacaoVO avaliacaoVO = new AvaliacaoVO(turma.getAvaliacao().getId(), curso.getNome(), oneDisciplina.getNome(), turma.getId().toString(), periodoDisponibilizado);
                                       allAvaliacoes.add(avaliacaoVO);
                                    }
                                }
                             }
                         }
                     }                       
                 }
        }    
        
        private String dtFormat(Date data){
            if (data != null){                 
                 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                 String date1 = format1.format(data);
                 return date1;
            } else {
                return null;
            }
        }
       
        public List<AvaliacaoVO> getAllAvaliacoes(){
            return this.allAvaliacoes;
        }
        
        public void setAllAvaliacoes(List<AvaliacaoVO> allAvaliacoes){
            this.allAvaliacoes = allAvaliacoes;
        }
        
}
