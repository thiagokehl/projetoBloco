package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Professor;

public class ProfessorDAO extends DAO {

	private static final String QUERY_SELECT = "Select * from professor where id = ?";

	public Professor consultar(Long id) throws SQLException {
		Professor professor = null;
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT); 
		pstm.setLong(1, id); 
		ResultSet rs = pstm.executeQuery(); 
		if (rs.next()) {
			professor = new Professor();
			professor.setId(id);
			professor.setNome(rs.getString("nome")); 
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return professor; 
	} 
}