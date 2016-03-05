package com.infnet.projeto.managedBean;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;

import com.infnet.projeto.data.AvaliacaoAlunoVO;
import com.infnet.projeto.data.Questao;
import com.infnet.projeto.data.QuestaoResposta;
import com.infnet.projeto.data.QuestionarioResposta;
import com.infnet.projeto.service.AvaliacaoClient;

@ManagedBean
@ViewScoped
public class RespostaMBean extends BaseMBean{
	private AvaliacaoAlunoVO avaliacaoAluno;
	private List<QuestaoResposta> respostas;

	@PostConstruct
	public void init(){
		Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String avaliacaoAlunoId = parameterMap.get("id");

		avaliacaoAluno = AvaliacaoClient.getAvaliacaoInfo(avaliacaoAlunoId);
		if(avaliacaoAluno.getResposta() != null){
			respostas = avaliacaoAluno.getResposta().getRespostas();
		}

		if(respostas == null){
			respostas = new ArrayList<QuestaoResposta>();
			QuestionarioResposta questionarioResposta = new QuestionarioResposta();
			questionarioResposta.setRespostas(respostas);
			avaliacaoAluno.setResposta(questionarioResposta);
		}

		for(Questao questao : avaliacaoAluno.getQuestionario().getQuestoes()){
			if(!respostas.contains(questao)){
				QuestaoResposta resposta = new QuestaoResposta();
				resposta.setCategoria(questao.getCategoria());
				resposta.setTexto(questao.getTexto());
				resposta.setId(questao.getId());
				respostas.add(resposta);
			}
		}
		
		

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

	public String getCategoria1(){
		return avaliacaoAluno.getQuestionario().getQuestoes().get(0).getCategoria();
	}

	public String getCategoria2(){
		return avaliacaoAluno.getQuestionario().getQuestoes().get(7).getCategoria();
	}

	public String getCategoria3(){
		return avaliacaoAluno.getQuestionario().getQuestoes().get(14).getCategoria();
	}

	public List<QuestaoResposta> getQuestoes1(){
		List<QuestaoResposta> questoes = new ArrayList<QuestaoResposta>();
		for(QuestaoResposta questao : respostas){
			if(questao.getCategoria().equalsIgnoreCase(getCategoria1())){
				questoes.add(questao);
			}
		}
		
		return questoes;
	}

	public List<QuestaoResposta> getQuestoes2(){
		List<QuestaoResposta> questoes = new ArrayList<QuestaoResposta>();
		for(QuestaoResposta questao : respostas){
			if(questao.getCategoria().equalsIgnoreCase(getCategoria2())){
				questoes.add(questao);
			}
		}
		
		return questoes;
	}

	public List<QuestaoResposta> getQuestoes3(){
		List<QuestaoResposta> questoes = new ArrayList<QuestaoResposta>();
		for(QuestaoResposta questao : respostas){
			if(questao.getCategoria().equalsIgnoreCase(getCategoria3())){
				questoes.add(questao);
			}
		}
		
		return questoes;
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

		AvaliacaoClient.update(avaliacaoAluno);
	}

	public void oncancel() {
		addInfoMessage("Não sei avaliar!");
		AvaliacaoClient.update(avaliacaoAluno);
	}
	
	public boolean isFinalizada(){
		return avaliacaoAluno.getFinalizada();
	}
	
	public boolean isExpirada(){
		Calendar c = Calendar.getInstance();
		c.setTime(avaliacaoAluno.getFim());
		c.add(Calendar.DATE, 1);
		return c.before(new Date()) && !isFinalizada();
	}
	
	public boolean isAberta(){
		return !isFinalizada() && ! isExpirada();
	}
	
	public String getDataFinal(){
		Format format = new SimpleDateFormat("dd-M-yyyy");
        return format.format(avaliacaoAluno.getFim());
	}
	
	public String submit(){
		addInfoMessage("Avaliação enviada. A InfNet agradece!");
		avaliacaoAluno.setFinalizada(true);
		AvaliacaoClient.update(avaliacaoAluno);
		
		return "";
	}
	
	public String getFreeText(){
		return avaliacaoAluno.getFreeText();
	}
	
	public void setFreeText(String text){
		avaliacaoAluno.setFreeText(text);
		AvaliacaoClient.update(avaliacaoAluno);
	}
	
	public void saveFreeText(String text){
		avaliacaoAluno.setFreeText(text);
		AvaliacaoClient.update(avaliacaoAluno);
	}
}
