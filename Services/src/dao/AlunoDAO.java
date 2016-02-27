package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;

public class AlunoDAO extends DAO{
	private static final String QUERY_SELECT_BY_TURMA = "select * from aluno, turma_alunos where aluno.id = turma_alunos.idAluno and turma_alunos.idTurma = ?;";
	private static final String QUERY_SELECT = "select * from aluno where id = ?";
 
	public Aluno findById(Long id) throws SQLException {
		Aluno aluno = null;
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT); 
		pstm.setLong(1, id); 
		ResultSet rs = pstm.executeQuery(); 
		if (rs.next()) {
			aluno = new Aluno();
			aluno.setId(rs.getLong("id"));
			aluno.setNome(rs.getString("nome"));
			aluno.setEmail(rs.getString("email"));
			aluno.setMatricula(rs.getString("matricula"));
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return aluno; 
	} 
	
	public List<Aluno> consultar(Long idTurma) throws SQLException {
		List<Aluno> alunos = new ArrayList<Aluno>();
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT_BY_TURMA); 
		pstm.setLong(1, idTurma); 
		ResultSet rs = pstm.executeQuery(); 
		while (rs.next()) {
			Aluno aluno = new Aluno();
			aluno.setId(rs.getLong("id"));
			aluno.setNome(rs.getString("nome"));
			aluno.setEmail(rs.getString("email"));
			aluno.setMatricula(rs.getString("matricula"));
			alunos.add(aluno);
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return alunos; 
	} 
	
}
