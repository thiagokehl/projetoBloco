package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Questao;

public class QuestaoDAO extends DAO{
	private static final String QUERY_SELECT = "select texto, descricao from questao, questao_categoria where questao.categoria = questao_categoria.id and questao.id = ?;";
	
	public Questao findById(Long id) throws SQLException {
		Questao questao = null;
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT); 
		pstm.setLong(1, id); 
		ResultSet rs = pstm.executeQuery(); 
		if (rs.next()) {
			questao = new Questao();
			questao.setId(id);
			questao.setTexto(rs.getString("texto"));
			questao.setCategoria(rs.getString("descricao"));
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return questao; 
	} 
}
