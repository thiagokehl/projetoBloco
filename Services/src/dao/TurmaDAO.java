package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Avaliacao;
import model.Professor;
import model.Turma;

public class TurmaDAO extends DAO{
	private static final String QUERY_SELECT_BY_DISCIPLINA = "Select * from turma where idDisciplina = ?";
	private static final String QUERY_SELECT = "Select * from turma where id = ?";
	
	private ProfessorDAO professorDAO = new ProfessorDAO();
	private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
	private AlunoDAO alunoDAO = new AlunoDAO();
	
	public Turma findById(Long id) throws SQLException {
		Turma turma = null;
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT); 
		pstm.setLong(1, id); 
		ResultSet rs = pstm.executeQuery(); 
		if (rs.next()) {
			turma = new Turma();
			turma.setId(id);

			Professor professor = professorDAO.consultar(rs.getLong("idProfessor"));
			turma.setProfessor(professor);
			
			Avaliacao avaliacao = avaliacaoDAO.consultar(id);
			turma.setAvaliacao(avaliacao);
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return turma; 
	} 
	
	public List<Turma> consultar(Long idDisciplina) throws SQLException {
		List<Turma> turmas = new ArrayList<Turma>();
		Connection conexao = getConexao(); 
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT_BY_DISCIPLINA); 
		pstm.setLong(1, idDisciplina); 
		ResultSet rs = pstm.executeQuery(); 
		while (rs.next()) {
			Turma turma = new Turma();
			turma.setId(rs.getLong("id"));
			
			Professor professor = professorDAO.consultar(rs.getLong("idProfessor"));
			turma.setProfessor(professor);
			
			List<Aluno> alunos = alunoDAO.consultar(turma.getId());
			turma.setAlunos(alunos);
			
			turmas.add(turma);
		}
		pstm.close(); 
		conexao.close();
		rs.close();
		
		return turmas; 
	} 
}
