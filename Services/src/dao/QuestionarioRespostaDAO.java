package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Likert;
import model.Questao;
import model.QuestaoResposta;
import model.QuestionarioResposta;

public class QuestionarioRespostaDAO extends DAO{
	private static final String QUERY_SELECT_BY_AVALIACAO_ALUNO = "select * from resposta_avaliacao where idAvaliacaoAluno = ?";
	private static final String QUERY_INSERT = "INSERT INTO resposta_avaliacao (idAvaliacaoAluno, idQuestao, resposta) VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE resposta = ?;";
	private static final String QUERY_DELETE = "DELETE FROM resposta_avaliacao WHERE idAvaliacaoAluno = ? and idQuestao = ?";

	private QuestaoDAO questaoDAO = new QuestaoDAO();

	public QuestionarioResposta consultar(String idAvaliacaoAluno) throws SQLException {
		QuestionarioResposta questionarioResposta = new QuestionarioResposta();
		List<QuestaoResposta> respostas = new ArrayList<QuestaoResposta>();
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT_BY_AVALIACAO_ALUNO); 
		pstm.setString(1, idAvaliacaoAluno); 
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Questao questao = questaoDAO.findById(rs.getLong("idQuestao"));
			QuestaoResposta questaoResposta = new QuestaoResposta();
			questaoResposta.setCategoria(questao.getCategoria());
			questaoResposta.setId(questao.getId());
			questaoResposta.setTexto(questao.getTexto());
			questaoResposta.setResposta(Likert.valueOf(rs.getString("resposta")));
			respostas.add(questaoResposta);
		}
		questionarioResposta.setRespostas(respostas);
		pstm.close(); 
		conexao.close();
		rs.close();

		return questionarioResposta; 
	} 

	public void update(String avaliacaoAlunoId, Long questaoId, Likert resposta) throws SQLException{
		Connection conexao = getConexao();
		PreparedStatement pstm = conexao.prepareStatement(QUERY_INSERT);
		pstm.setString(1, avaliacaoAlunoId);
		pstm.setLong(2, questaoId);
		pstm.setString(3, resposta.toString());
		pstm.setString(4, resposta.toString());
		pstm.executeUpdate();

		pstm.close();
		conexao.close();
	}
	

	public void delete(String avaliacaoAlunoId, Long questaoId) throws SQLException{
		Connection conexao = getConexao();
		PreparedStatement pstm = conexao.prepareStatement(QUERY_DELETE);
		pstm.setString(1, avaliacaoAlunoId);
		pstm.setLong(2, questaoId);
		pstm.executeUpdate();

		pstm.close();
		conexao.close();
	}
}
