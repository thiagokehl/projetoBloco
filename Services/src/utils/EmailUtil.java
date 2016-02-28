package utils;

import java.net.MalformedURLException;
import java.net.URL;

import model.Aluno;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import vo.AvaliacaoDispVO;

public class EmailUtil {
	
	public static void enviaEmail(Aluno aluno, AvaliacaoDispVO avaliacao) throws EmailException, MalformedURLException {
		
		HtmlEmail email = new HtmlEmail();
		URL url = new URL("https://lh6.googleusercontent.com/-exRV04A16KA/AAAAAAAAAAI/AAAAAAAAABA/7keDAMJGNNY/s0-c-k-no-ns/photo.jpg");
		email.embed(url, "Infnet logo");
		String message = "<html> Olá " + aluno.getNome() + ",<br/><br/>" +
				"Com o intuito de melhor atendê-lo, queremos sua opinião sobre o andamento da disciplina " + avaliacao.getDisciplinaNome() +
				" que você está cursando com o professor " + avaliacao.getProfessorNome() + ". <br/><br/>" +
				"Por favor, responda a avaliação clicando <a href=\"http://localhost:8080/projetoBloco/faces/secure/responderAvaliacao.xhtml?id=" + avaliacao.getAvaliacaoID()+ "\">aqui</a> ou se logando na sua área de avaliações. "
						+ "<br/><br/> Atenciosamente, <br/>Equipe Infnet</html>";
		
		email.setHtmlMsg(message);
		email.setHostName("smtp.gmail.com");
		email.addTo(aluno.getEmail(), aluno.getNome());
		email.setFrom("jenkinsmailserver@gmail.com", "Infnet"); 
		email.setSubject("Infnet - Avaliação disponível - " + avaliacao.getDisciplinaNome());
	
		email.setAuthentication("jenkinsmailserver@gmail.com", "Dark@860419");
		email.setSmtpPort(465);
		email.setSSLOnConnect(true);
		// envia email
		email.send();
	}
}