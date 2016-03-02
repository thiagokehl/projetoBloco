package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.AvaliacaoAluno;
import model.QuestionarioResposta;

public class AvaliacaoAlunoDAO extends DAO{
	private static final String QUERY_SELECT_BY_AVALIACAO = "select * from avaliacao_aluno where idAvaliacao = ?";

	private AlunoDAO alunoDAO = new AlunoDAO();
	private QuestionarioRespostaDAO questionarioRespostaDAO = new QuestionarioRespostaDAO();
	
	public List<AvaliacaoAluno> consultar(Long idAvaliacao) throws SQLException {
		List<AvaliacaoAluno> avaliacoes = new ArrayList<AvaliacaoAluno>();
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT_BY_AVALIACAO); 
		pstm.setLong(1, idAvaliacao); 
		ResultSet rs = pstm.executeQuery(); 
		while (rs.next()) {   
			AvaliacaoAluno avaliacaoAluno = new AvaliacaoAluno();
			avaliacaoAluno.setId(rs.getLong("id"));
			avaliacaoAluno.setFinalizada(rs.getString("finalizada"));
			
			Aluno aluno = alunoDAO.findById(rs.getLong("idAluno"));
			avaliacaoAluno.setAluno(aluno);
			
			QuestionarioResposta resposta = questionarioRespostaDAO.consultar(avaliacaoAluno.getId());
			avaliacaoAluno.setQuestionarioResposta(resposta);
			
			avaliacoes.add(avaliacaoAluno);
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return avaliacoes; 
	} 
}
