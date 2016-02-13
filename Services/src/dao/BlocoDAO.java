package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bloco;
import model.Disciplina;

public class BlocoDAO extends DAO{
	private static final String QUERY_SELECT_BY_CURSO = "Select * from bloco where idCurso = ?";
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	
	public List<Bloco> consultar(Long idCurso) throws SQLException {
		List<Bloco> blocos = new ArrayList<Bloco>();
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT_BY_CURSO); 
		pstm.setLong(1, idCurso); 
		ResultSet rs = pstm.executeQuery(); 
		while (rs.next()) {
			Bloco bloco = new Bloco();
			bloco.setId(rs.getLong("id"));
			bloco.setNome(rs.getString("nome"));
			
			List<Disciplina> disciplinas = disciplinaDAO.consultar(bloco.getId());
			bloco.setDisciplinas(disciplinas);
			
			blocos.add(bloco);
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return blocos; 
	} 
}
