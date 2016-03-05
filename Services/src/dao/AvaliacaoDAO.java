package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Aluno;
import model.Avaliacao;
import model.AvaliacaoAluno;
import model.AvaliacaoAlunoVO;
import model.AvaliacaoTurma;
import model.Questionario;
import model.QuestionarioResposta;
import utils.EmailUtil;
import vo.AvaliacaoDispVO;

public class AvaliacaoDAO extends DAO {

	private static final String QUERY_SELECT_BY_TURMA = "Select * from avaliacao where idTurma = ?";
	private static final String QUERY_SELECT_AVALIACOES_DISP_BY_ALUNO = "select avl.id, aln.id, dsp.nome, prf.nome, dsp.semestre from turma trm, turma_alunos tra, aluno aln, disciplina dsp, professor prf, avaliacao avl where tra.idTurma = trm.id and tra.idAluno = aln.id and avl.idTurma = trm.id and trm.idDisciplina = dsp.id and trm.idProfessor = prf.id and CURDATE() between avl.dataInicial and avl.dataFinal and aln.id = ? ";
	private static final String QUERY_INSERT_AVALIACAO = "insert into avaliacao (idTurma, idQuestionario, dataInicial, dataFinal) values (?,?,?,?)";
	private static final String QUERY_INSERT_AVALIACAO_ALUNO = "insert into avaliacao_aluno (id, idAluno, idAvaliacao, finalizada) values (?, ?, LAST_INSERT_ID(), 'N')";
	private static final String QUERY_GET_AVALIACAO_ALUNO = "select aluno.nome as aluno, aluno.matricula as matricula, professor.nome as professor, disciplina.nome as disciplina, avaliacao_aluno.finalizada as status, avaliacao.dataInicial as inicio, avaliacao.dataFinal as fim from avaliacao, avaliacao_aluno, aluno, professor, turma, disciplina where aluno.id = idAluno and avaliacao.id = idAvaliacao and avaliacao.idTurma = turma.id and turma.idProfessor = professor.id and turma.idDisciplina = disciplina.id and avaliacao_aluno.id = ?";

	private QuestionarioDAO questionarioDAO = new QuestionarioDAO();
	private AvaliacaoAlunoDAO avaliacaoAlunoDAO = new AvaliacaoAlunoDAO();
	private QuestionarioRespostaDAO questionarioRespostaDAO = new QuestionarioRespostaDAO();

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
	
	public void create(AvaliacaoTurma avaliacao) throws SQLException{
		Connection conexao = getConexao();
		PreparedStatement pstm = conexao.prepareStatement(QUERY_INSERT_AVALIACAO);
		pstm.setLong(1, avaliacao.getTurma().getId());
		pstm.setLong(2, 1L);
		pstm.setDate(3, new Date(avaliacao.getInicio().getTime()));
		pstm.setDate(4, new Date(avaliacao.getFim().getTime()));
		pstm.execute();
		
		if(avaliacao.getTurma().getAlunos() != null){
			pstm = conexao.prepareStatement(QUERY_INSERT_AVALIACAO_ALUNO);
			for(Aluno aluno : avaliacao.getTurma().getAlunos()){
				String id = String.valueOf(UUID.randomUUID());
				pstm.setString(1, id);
				pstm.setLong(2, aluno.getId());
				pstm.execute();
				
				EmailUtil emailUtil = new EmailUtil(id, aluno, avaliacao);
				emailUtil.start();
			}
		}
		
		pstm.close();
		conexao.close();
	}
	
	public AvaliacaoAlunoVO get(String id) throws SQLException {
		AvaliacaoAlunoVO avaliacaoAluno = null;
		Connection conexao = getConexao();
		PreparedStatement pstm = conexao.prepareStatement(QUERY_GET_AVALIACAO_ALUNO);
		pstm.setString(1, id);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			avaliacaoAluno = new AvaliacaoAlunoVO();
			avaliacaoAluno.setInicio(rs.getDate("inicio"));
			avaliacaoAluno.setFim(rs.getDate(("fim")));
			avaliacaoAluno.setProfessor(rs.getString("professor"));
			avaliacaoAluno.setDisciplina(rs.getString("disciplina"));
			avaliacaoAluno.setNomeAluno(rs.getString("aluno"));
			avaliacaoAluno.setMatricula(rs.getString("matricula"));
			if("N".equalsIgnoreCase(rs.getString("status"))){
				avaliacaoAluno.setFinalizada(Boolean.FALSE);	
			}else{
				avaliacaoAluno.setFinalizada(Boolean.TRUE);
			}
			Questionario questionario = questionarioDAO.findById(1L);
			avaliacaoAluno.setQuestionario(questionario);
			
			QuestionarioResposta resposta = questionarioRespostaDAO.consultar(id);
			avaliacaoAluno.setResposta(resposta);

		}
		pstm.close();
		conexao.close();
		rs.close();

		return avaliacaoAluno;
	}
}
