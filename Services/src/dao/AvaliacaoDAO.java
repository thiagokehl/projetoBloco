package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Avaliacao;
import model.AvaliacaoAluno;
import model.Questionario;
import model.Turma;
import vo.AvaliacaoDispVO;

public class AvaliacaoDAO extends DAO {

	private static final String QUERY_SELECT_BY_TURMA = "Select * from avaliacao where idTurma = ?";
	private static final String QUERY_SELECT_AVALIACOES_DISP_BY_ALUNO = "select avl.id, aln.id, dsp.nome, prf.nome, dsp.semestre from turma trm, turma_alunos tra, aluno aln, disciplina dsp, professor prf, avaliacao avl where tra.idTurma = trm.id and tra.idAluno = aln.id and avl.idTurma = trm.id and trm.idDisciplina = dsp.id and trm.idProfessor = prf.id and CURDATE() between avl.dataInicial and avl.dataFinal and aln.id = ? ";

	private QuestionarioDAO questionarioDAO = new QuestionarioDAO();
	private AvaliacaoAlunoDAO avaliacaoAlunoDAO = new AvaliacaoAlunoDAO();

	public Avaliacao consultar(Long idTurma) throws SQLException {
		Avaliacao avaliacao = null;
		Connection conexao = getConexao();
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT_BY_TURMA);
		pstm.setLong(1, idTurma);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			avaliacao = new Avaliacao();
			avaliacao.setId(rs.getLong("id"));
			avaliacao.setInicio(rs.getDate("dataInicial"));
			avaliacao.setFim(rs.getDate(("dataFinal")));

			Questionario questionario = questionarioDAO.findById(rs.getLong("idQuestionario"));
			avaliacao.setQuestionario(questionario);

			List<AvaliacaoAluno> avaliacoesAlunos = avaliacaoAlunoDAO.consultar(avaliacao.getId());
			avaliacao.setAvaliacoesAlunos(avaliacoesAlunos);
		}
		pstm.close();
		conexao.close();
		rs.close();

		return avaliacao;
	}

	public List<AvaliacaoDispVO> consultarAvaliacoesDisponiveis(Long idAluno) throws SQLException {
		List<AvaliacaoDispVO> avaliacoesDisp = new ArrayList<AvaliacaoDispVO>();
		Connection conexao = getConexao();
		PreparedStatement pstm = conexao.prepareStatement(QUERY_SELECT_AVALIACOES_DISP_BY_ALUNO);
		pstm.setLong(1, idAluno);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			AvaliacaoDispVO avaliacao = new AvaliacaoDispVO();
			avaliacao.setAvaliacaoID(rs.getLong("avl.id"));
			avaliacao.setAlunoID(rs.getLong("aln.id"));
			avaliacao.setDisciplinaNome(rs.getString("dsp.nome"));
			avaliacao.setProfessorNome(rs.getString("prf.nome"));
			avaliacao.setSemestreDisciplina(rs.getString("dsp.semestre"));

			avaliacoesDisp.add(avaliacao);
		}
		pstm.close();
		conexao.close();
		rs.close();

		return avaliacoesDisp;
	}
}
