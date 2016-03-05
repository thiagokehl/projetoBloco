package com.infnet.projeto.managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;

import com.infnet.projeto.data.AvaliacaoAlunoVO;
import com.infnet.projeto.data.Questao;
import com.infnet.projeto.service.AvaliacaoClient;

@ManagedBean
@ViewScoped
public class RespostaMBean extends BaseMBean{
	private Integer rating;
	private AvaliacaoAlunoVO avaliacaoAluno;

	@PostConstruct
	public void init(){
		Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String avaliacaoAlunoId = parameterMap.get("id");

		avaliacaoAluno = AvaliacaoClient.getAvaliacaoInfo(avaliacaoAlunoId);
	} 


	public String getProfessor(){
		return avaliacaoAluno.getProfessor();
	}

	public String getAluno(){
		return avaliacaoAluno.getNomeAluno();
	}

	public String getDisciplina(){
		return avaliacaoAluno.getDisciplina();
	}

	public String getMatricula(){
		return avaliacaoAluno.getMatricula();
	}

	public List<Questao> getQuestoes1(){
		List<Questao> questoes1 = new ArrayList<Questao>();
		for(Questao questao : avaliacaoAluno.getQuestionario().getQuestoes()){
			if(questao.getCategoria().equalsIgnoreCase(getCategoria1())){
				questoes1.add(questao);
			}
		}
		return questoes1;
	}

	public List<Questao> getQuestoes2(){
		List<Questao> questoes2 = new ArrayList<Questao>();
		for(Questao questao : avaliacaoAluno.getQuestionario().getQuestoes()){
			if(questao.getCategoria().equalsIgnoreCase(getCategoria3())){
				questoes2.add(questao);
			}
		}
		return questoes2;
	}

	public List<Questao> getQuestoes3(){
		List<Questao> questoes3 = new ArrayList<Questao>();
		for(Questao questao : avaliacaoAluno.getQuestionario().getQuestoes()){
			if(questao.getCategoria().equalsIgnoreCase(getCategoria3())){
				questoes3.add(questao);
			}
		}
		return questoes3;
	}

	public String getCategoria1(){
		return avaliacaoAluno.getQuestionario().getQuestoes().get(0).getCategoria();
	}

	public String getCategoria2(){
		return avaliacaoAluno.getQuestionario().getQuestoes().get(7).getCategoria();
	}

	public String getCategoria3(){
		return avaliacaoAluno.getQuestionario().getQuestoes().get(14).getCategoria();
	}


	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getRating1() {
		return rating;
	}

	public void setRating1(Integer rating) {
		this.rating = rating;
	}
	


	public void onrate(RateEvent rateEvent) {
		if(((Integer) rateEvent.getRating()).intValue() == 1){
			addInfoMessage("Discordo totalmente!");
		}

		if(((Integer) rateEvent.getRating()).intValue() == 2){
			addInfoMessage("Discordo!");
		}

		if(((Integer) rateEvent.getRating()).intValue() == 3){
			addInfoMessage("Não concordo nem discordo!");
		}

		if(((Integer) rateEvent.getRating()).intValue() == 4){
			addInfoMessage("Concordo!");
		}

		if(((Integer) rateEvent.getRating()).intValue() == 5){
			addInfoMessage("Concordo totalmente!");
		}

	}

	public void oncancel() {
		addInfoMessage("Não sei avaliar!");
	}

}
