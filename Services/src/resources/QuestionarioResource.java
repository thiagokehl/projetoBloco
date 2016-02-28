package resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import dao.QuestionarioDAO;
import vo.QuestaoAvlAlunoVO;

@Path("/questionario")
public class QuestionarioResource {
	private QuestionarioDAO questionarioDAO = new QuestionarioDAO();
	
	@GET
	@Produces("application/json; charset=utf-8")
	@Path("/{idAvaliacaoAluno}")
	public Response getAvaliacacoesDispByAluno(@PathParam("idAvaliacaoAluno") Long idAvaliacaoAluno){
		List<QuestaoAvlAlunoVO> questoes;
		try {
			questoes = questionarioDAO.consultarQuestoesAvlAluno(idAvaliacaoAluno);			
		} catch (SQLException e) {
			return Response.serverError().build();
		}
		GenericEntity<List<QuestaoAvlAlunoVO>> entity = new GenericEntity<List<QuestaoAvlAlunoVO>>(questoes) {};
		
		return Response.ok().entity(entity).build();
	}
}
