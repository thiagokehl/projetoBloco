package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Avaliacao;
import model.AvaliacaoAluno;
import model.Questionario;

public class AvaliacaoDAO extends DAO{
	
	private static final String QUERY_SELECT_BY_TURMA = "Select * from avaliacao where idTurma = ?";
	private QuestionarioDAO questionarioDAO = new QuestionarioDAO();
	private AvaliacaoAlunoDAO avaliacaoAlunoDAO = new AvaliacaoAlunoDAO();
	
	public Avaliacao consultar(Long idTurma) throws SQLException {
		Avaliacao avaliacao = null;
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT_BY_TURMA); 
		pstm.setLong(1, idTurma); 
		ResultSet rs = pstm.executeQuery(); 
		if (rs.next()) {
			avaliacao = new Avaliacao();
			avaliacao.setId(rs.getLong("id"));
			avaliacao.setInicio(rs.getDate("dataInicial"));
			avaliacao.setFim(rs.getDate(("dataFinal")));
			
			Questionario questionario = questionarioDAO.findById(rs.getLong("idQuestionario"));
			avaliacao.setQuestionario(questionario);
			
			List<AvaliacaoAluno> avaliacoesAlunos = avaliacaoAlunoDAO.consultar(avaliacao.getId());
			avaliacao.setAvaliacoesAlunos(avaliacoesAlunos);
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return avaliacao; 
	} 
}
