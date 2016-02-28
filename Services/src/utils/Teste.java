package utils;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;

import model.Aluno;
import vo.AvaliacaoDispVO;

public class Teste {

	public static void main(String[] args) throws MalformedURLException, EmailException {
		Aluno aluno = new Aluno();
		aluno.setEmail("thiagokehl@gmail.com");
		aluno.setNome("Thiago");
		AvaliacaoDispVO avaliacao = new AvaliacaoDispVO();
		avaliacao.setAlunoID(2L);
		avaliacao.setAvaliacaoID(1L);
		avaliacao.setDisciplinaNome("Engenharia de Software");
		avaliacao.setProfessorNome("Aquino");
		
		EmailUtil.enviaEmail(aluno, avaliacao);
	}
}
