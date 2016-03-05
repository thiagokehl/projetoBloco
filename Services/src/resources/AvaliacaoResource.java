package resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.mysql.jdbc.StringUtils;

import model.AvaliacaoAlunoVO;
import model.AvaliacaoTurma;
import model.QuestaoResposta;
import vo.AvaliacaoDispVO;
import dao.AvaliacaoDAO;
import dao.QuestionarioRespostaDAO;

@Path("/avaliacao")
public class AvaliacaoResource {
	private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
	private QuestionarioRespostaDAO questionarioRespostaDAO = new QuestionarioRespostaDAO();


	@GET
	@Produces("application/json; charset=utf-8")
	@Path("/{alunoId}")
	public Response getAvaliacacoesDispByAluno(@PathParam("alunoId") Long alunoId){
		List<AvaliacaoDispVO> avaliacoesDisp;
		try {
			avaliacoesDisp = avaliacaoDAO.consultarAvaliacoesDisponiveis(alunoId);			
		} catch (SQLException e) {
			return Response.serverError().build();
		}
		GenericEntity<List<AvaliacaoDispVO>> entity = new GenericEntity<List<AvaliacaoDispVO>>(avaliacoesDisp) {};

		return Response.ok().entity(entity).build();
	}

	@GET
	@Produces("application/json; charset=utf-8")
	@Path("/aluno/{id}")
	public Response getAvaliacaoAluno(@PathParam("id") String avaliacaoAlunoId){
		AvaliacaoAlunoVO avaliacaoAluno;
		try {
			avaliacaoAluno = avaliacaoDAO.get(avaliacaoAlunoId);			
		} catch (SQLException e) {
			return Response.serverError().build();
		}

		return Response.ok().entity(avaliacaoAluno).build();
	}

	@POST
	@Produces("application/json; charset=utf-8")
	@Consumes("application/json; charset=utf-8")
	@Path("/create")
	public Response createAvaliacao(AvaliacaoTurma avaliacao){

		try {
			avaliacaoDAO.create(avaliacao);
		} catch (SQLException e) {
			return Response.serverError().build();
		}

		return Response.ok().build();
	}

	@POST
	@Produces("application/json; charset=utf-8")
	@Consumes("application/json; charset=utf-8")
	@Path("/resposta")
	public Response resposta(AvaliacaoAlunoVO avaliacao){

		try {
			if(avaliacao != null && avaliacao.getResposta() != null){
				if(avaliacao.getFinalizada()){
					avaliacaoDAO.finalizar(avaliacao);
				}
				
				if(avaliacao.getFreeText() != null){
					avaliacaoDAO.update(avaliacao);
				}
				
				for(QuestaoResposta resposta : avaliacao.getResposta().getRespostas()){
					if(resposta.getResposta() != null){
						questionarioRespostaDAO.update(avaliacao.getId(), resposta.getId(), resposta.getResposta());
					}else{
						questionarioRespostaDAO.delete(avaliacao.getId(), resposta.getId());
					}
				}
			}
		} catch (SQLException e) {
			return Response.serverError().build();
		}

		return Response.ok().build();
	}
}
