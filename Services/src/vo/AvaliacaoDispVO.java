package vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@XmlRootElement
@JsonPropertyOrder({"avaliacaoID", "alunoID", "disciplinaNome", "professorNome", "semestreDisciplina"})
public class AvaliacaoDispVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2074600050970088933L;
	private Long avaliacaoID;
	private Long alunoID;
	private String disciplinaNome;
	private String professorNome;
	private String semestreDisciplina;

	public Long getAvaliacaoID() {
		return avaliacaoID;
	}

	public void setAvaliacaoID(Long avaliacaoID) {
		this.avaliacaoID = avaliacaoID;
	}

	public Long getAlunoID() {
		return alunoID;
	}

	public void setAlunoID(Long alunoID) {
		this.alunoID = alunoID;
	}

	public String getDisciplinaNome() {
		return disciplinaNome;
	}

	public void setDisciplinaNome(String disciplinaNome) {
		this.disciplinaNome = disciplinaNome;
	}

	public String getProfessorNome() {
		return professorNome;
	}

	public void setProfessorNome(String professorNome) {
		this.professorNome = professorNome;
	}

	public String getSemestreDisciplina() {
		return semestreDisciplina;
	}

	public void setSemestreDisciplina(String semestreDisciplina) {
		this.semestreDisciplina = semestreDisciplina;
	}

}
