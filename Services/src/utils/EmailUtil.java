package utils;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.Format;
import java.text.SimpleDateFormat;

import model.Aluno;
import model.AvaliacaoTurma;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil extends Thread {
	
	private String id;
	private Aluno aluno;
	private AvaliacaoTurma avaliacao;
	
	public EmailUtil(String id, Aluno aluno, AvaliacaoTurma avaliacao) {
		super();
		this.id = id;
		this.aluno = aluno;
		this.avaliacao = avaliacao;
	}

	public void enviaEmail() throws EmailException, MalformedURLException, UnknownHostException {
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		Format format = new SimpleDateFormat("dd-M-yyyy");
        String dataInicio = format.format(avaliacao.getInicio());
        String dataFim = format.format(avaliacao.getFim());
        
		HtmlEmail email = new HtmlEmail();
		URL url = new URL("https://lh6.googleusercontent.com/-exRV04A16KA/AAAAAAAAAAI/AAAAAAAAABA/7keDAMJGNNY/s0-c-k-no-ns/photo.jpg");
		email.embed(url, "Infnet logo");
		String message = "<html> Olá " + aluno.getNome() + ",<br/><br/>" +
				"Com o intuito de melhor atendê-lo, queremos sua opinião sobre o andamento da disciplina " + avaliacao.getDisciplina().getNome() +
				" que você está cursando com o professor " + avaliacao.getTurma().getProfessor().getNome() + ". <br/><br/>" +
				"Por favor, responda a avaliação clicando <a href=\"http://" + ip + ":8080/projetoBloco/faces/responderAvaliacao.xhtml?id=" + id + "\">aqui</a>. <br/><br/>"
						+ "NOTA: Está avaliação estará aberta para preenchimento a partir do dia " + dataInicio + " até " + dataFim 
						+ "<br/><br/> Atenciosamente, <br/>Equipe Infnet</html>";
		
		email.setHtmlMsg(message);
		email.setHostName("smtp.gmail.com");
		email.addTo(aluno.getEmail(), aluno.getNome());
		email.setFrom("jenkinsmailserver@gmail.com", "Infnet"); 
		email.setSubject("Infnet - Avaliação disponível - " + avaliacao.getDisciplina().getNome());
	
		email.setAuthentication("jenkinsmailserver@gmail.com", "Dark@860419");
		email.setSmtpPort(465);
		email.setSSLOnConnect(true);
		// envia email
		email.send();
	}

	@Override
	public void run() {
		try {
			enviaEmail();
		} catch (MalformedURLException | EmailException | UnknownHostException e) {
			e.printStackTrace();
		}
	}
}