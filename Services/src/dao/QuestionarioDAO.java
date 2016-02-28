package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Questao;
import model.Questionario;
import vo.AvaliacaoDispVO;
import vo.QuestaoAvlAlunoVO;

public class QuestionarioDAO extends DAO{

	private static final String QUERY_SELECT = "Select * from questionario where id = ?";
	private static final String QUERY_QUESTOES_AVL_ALUNO = "select rsp.id, qct.descricao, qst.texto, rsp.resposta from avaliacao avl, avaliacao_aluno aln, resposta_avaliacao rsp, questionario qtn, questao qst, questao_categoria qct where avl.id = aln.idAvaliacao and rsp.idAvaliacaoAluno = aln.id and rsp.idQuestao = qst.id and avl.idQuestionario = qtn.id and qtn.idQuestao = qst.id  and qst.categoria = qct.id and aln.id = ?";
	
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
	
	public List<QuestaoAvlAlunoVO> consultarQuestoesAvlAluno(Long idAvaliacaoAluno) throws SQLException {
		List<QuestaoAvlAlunoVO> questoesAvaliacaoAluno = new ArrayList<QuestaoAvlAlunoVO>();
		Connection conexao = getConexao();
		PreparedStatement pstm = conexao.prepareStatement(QUERY_QUESTOES_AVL_ALUNO);
		pstm.setLong(1, idAvaliacaoAluno);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			QuestaoAvlAlunoVO questao = new QuestaoAvlAlunoVO();
			questao.setIdResposta(rs.getLong("rsp.id"));
			questao.setCategoriaQuestao(rs.getString("qct.descricao"));
			questao.setQuestao(rs.getString("qst.texto"));
			questao.setResposta(rs.getString("rsp.resposta"));
			
			questoesAvaliacaoAluno.add(questao);
		}
		pstm.close();
		conexao.close();
		rs.close();

		return questoesAvaliacaoAluno;
	}	
}
