package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Bloco;
import model.Curso;

public class CursoDAO extends DAO {

	private static final String QUERY_LISTAR = "Select * from curso";
	private static final String QUERY_SELECT = "Select * from curso where id = ?";
	private BlocoDAO blocoDAO = new BlocoDAO();
	
	public List<Curso> listar() throws SQLException { 
		List<Curso> lista = new ArrayList<>();
		
		Connection conexao = getConexao(); 
		Statement stm = conexao.createStatement(); 
		ResultSet rs = stm.executeQuery(QUERY_LISTAR); 
		while (rs.next()) { 
			Curso curso = new Curso(); 
			curso.setNome(rs.getString("nome")); 
			curso.setId(rs.getLong("id"));
			lista.add(curso); 
		} 
		stm.close(); 
		conexao.close(); 
		rs.close();
		return lista; 
	}
	public Curso consultar(Long id) throws SQLException {
		Curso curso = null;
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT); 
		pstm.setLong(1, id); 
		ResultSet rs = pstm.executeQuery(); 
		if (rs.next()) {
			curso = new Curso();
			curso.setId(id);
			curso.setNome(rs.getString("nome"));
			
			List<Bloco> blocos = blocoDAO.consultar(id);
			curso.setBlocos(blocos);
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return curso; 
	} 
}