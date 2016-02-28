package resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import dao.AvaliacaoDAO;
import vo.AvaliacaoDispVO;

@Path("/avaliacao")
public class AvaliacaoResource {
	private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

	
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
}
