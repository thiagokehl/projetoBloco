/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infnet.projeto.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;

import com.infnet.projeto.data.AvaliacaoDispVO;
import com.infnet.projeto.data.Bloco;
import com.infnet.projeto.data.Curso;
import com.infnet.projeto.data.Disciplina;
import com.infnet.projeto.data.Turma;
import com.infnet.projeto.service.AvaliacaoClient;
import com.infnet.projeto.service.CursoClient;

/**
 *
 * @author matheus
 */
@ManagedBean
@ViewScoped
public class AvaliacaoMBean extends BaseMBean{

	private List<AvaliacaoDispVO> avaliacoesDisp = AvaliacaoClient.getAvaliacoesDisp();
	private Date dataInicio;
	private Date dataFim;
	private List<SelectItem> cursos;
	private String cursoId;
	private Curso cursoInfo;
	private List<SelectItem> blocos;
	private Long blocoId;
	private Bloco blocoSelected;
	private List<SelectItem> disciplinas;
	private Long disciplinaId;
	private Disciplina disciplinaSelected;
	private List<SelectItem> turmas;
	private Long turmaId;
	private Turma turmaSelected;

	@PostConstruct
	public void init() {
		cursos = new ArrayList<SelectItem>();
		List<Curso> allCursos = CursoClient.getAllCursos();
		for(Curso oneCurso : allCursos){
			cursos.add(new SelectItem(oneCurso.getId(), oneCurso.getNome()));
		}
	}

	public List<SelectItem> getCursos() {
		return cursos;
	}

	public void setCursos(List<SelectItem> cursos) {
		this.cursos = cursos;
	}

	public String getCursoId() {
		return cursoId;
	}

	public void setCursoId(String cursoId) {
		if(StringUtils.isNotBlank(cursoId)){
			cursoInfo = CursoClient.getInfo(cursoId);
		}
		this.cursoId = cursoId;
	}

	public List<SelectItem> getBlocos() {
		blocos = new ArrayList<SelectItem>();
		if(cursoInfo == null){
			blocos.add(new SelectItem("","Selecione um curso para carregar os blocos"));
		}else{
			if(cursoInfo.getBlocos() == null || cursoInfo.getBlocos().isEmpty()){
				disciplinas.add(new SelectItem("","Curso selecionado não contém blocos cadastrados"));
			}
			blocos.add(new SelectItem("","Selecione um bloco"));
			for(Bloco bloco : cursoInfo.getBlocos()){
				blocos.add(new SelectItem(bloco.getId(),bloco.getNome()));
			}
		}
		
		return blocos;
	}

	public void setBlocos(List<SelectItem> blocos) {
		this.blocos = blocos;
	}
	
	public List<SelectItem> getDisciplinas() {
		disciplinas = new ArrayList<SelectItem>();
		if(blocoSelected == null){
			disciplinas.add(new SelectItem("","Selecione um bloco para carregar as disciplinas"));
		}else{
			if(blocoSelected.getDisciplinas() == null || blocoSelected.getDisciplinas().isEmpty()){
				disciplinas.add(new SelectItem("","Bloco selecionado não contém disciplinas cadastradas"));
			}else{
				disciplinas.add(new SelectItem("","Selecione uma disciplina"));
				for(Disciplina disciplina : blocoSelected.getDisciplinas()){
					disciplinas.add(new SelectItem(disciplina.getId(), disciplina.getSemestre() + " " + disciplina.getNome()));
				}
			}
		}
		
		return disciplinas;
	}

	public Long getBlocoId() {
		return blocoId;
	}

	public void setBlocoId(Long blocoId) {
		this.blocoId = blocoId;
		blocoSelected = getBlocoSelected(blocoId);
	}
	
	private Bloco getBlocoSelected(Long blocoId){
		for(Bloco bloco : cursoInfo.getBlocos()){
			if(bloco.getId().equals(blocoId)){
				return bloco;
			}
		}
		
		return null;
	}
	
	private Disciplina getDisciplinaSelected(Long disciplinaId){
		for(Disciplina disciplina : blocoSelected.getDisciplinas()){
			if(disciplina.getId().equals(disciplinaId)){
				return disciplina;
			}
		}
		
		return null;
	}

	public Long getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(Long disciplinaId) {
		this.disciplinaId = disciplinaId;
		disciplinaSelected = getDisciplinaSelected(disciplinaId);
	}

	public void setDisciplinas(List<SelectItem> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<SelectItem> getTurmas() {
		turmas = new ArrayList<SelectItem>();
		if(disciplinaSelected == null){
			turmas.add(new SelectItem("","Selecione uma disciplina para carregar as turmas"));
		}else{
			if(disciplinaSelected.getTurmas() == null || disciplinaSelected.getTurmas().isEmpty()){
				turmas.add(new SelectItem("","Disciplina selecionada não contém turmas ativas"));
			}else{
				turmas.add(new SelectItem("","Selecione a Turma"));
				for(Turma turma : disciplinaSelected.getTurmas()){
					turmas.add(new SelectItem(turma.getId(), "Turma " + turma.getId()));
				}
			}
		}
		
		return turmas;
	}

	public void setTurmas(List<SelectItem> turmas) {
		this.turmas = turmas;
	}

	public Long getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(Long turmaId) {
		this.turmaId = turmaId;
		turmaSelected = getTurmaSelected(turmaId);
	}

	public Turma getTurmaSelected(Long turmaId) {
		
		for(Turma turma : disciplinaSelected.getTurmas()){
			if(turma.getId().equals(turmaId)){
				return turma;
			}
		}

		return null;
	}
	
	public String getProfessorNome(){
		if(turmaSelected != null){
			return turmaSelected.getProfessor().getNome();
		}
		
		return "N/A";
	}

	public List<AvaliacaoDispVO> getAvaliacoesDisp(){
		return this.avaliacoesDisp;
	}

	public void setAvaliacoesDisp(List<AvaliacaoDispVO> avaliacoesDisp){
		this.avaliacoesDisp = avaliacoesDisp;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	} 
	
    public void salvarAvaliacao(ActionEvent actionEvent) {
        addMessage("Avaliação criada!");
    }

}
