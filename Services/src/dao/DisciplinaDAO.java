package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;

public class DisciplinaDAO extends DAO{
	private static final String QUERY_SELECT_BY_BLOCO = "Select * from disciplina where idBloco = ?";

	public List<Disciplina> consultar(Long idBloco) throws SQLException {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT_BY_BLOCO); 
		pstm.setLong(1, idBloco); 
		ResultSet rs = pstm.executeQuery(); 
		while (rs.next()) {
			Disciplina disciplina = new Disciplina();
			disciplina.setId(rs.getLong("id"));
			disciplina.setNome(rs.getString("nome"));
			disciplina.setSemestre(rs.getString("semestre"));
			disciplinas.add(disciplina);
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return disciplinas; 
	} 
}
