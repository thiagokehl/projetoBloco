package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Questao;
import model.Questionario;

public class QuestionarioDAO extends DAO{

	private static final String QUERY_SELECT = "Select * from questionario where id = ?";
	
	private QuestaoDAO questaoDAO = new QuestaoDAO();
	
	public Questionario findById(Long id) throws SQLException {
		Questionario questionario = new Questionario();
		questionario.setId(id);
		List<Questao> questoes = new ArrayList<Questao>();
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT); 
		pstm.setLong(1, id); 
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Questao questao = questaoDAO.findById(rs.getLong("idQuestao"));
			questoes.add(questao);
		}
		questionario.setQuestoes(questoes);
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return questionario; 
	} 
}
