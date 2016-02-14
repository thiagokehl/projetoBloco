package resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Turma;
import dao.TurmaDAO;

@Path("/turma")
public class TurmaResource {
	private TurmaDAO turmaDAO = new TurmaDAO();

	@GET
	@Produces("application/json; charset=utf-8")
	@Path("/{id}")
	public Response getAvaliacao(@PathParam("id") Long idTurma){

		Turma turma;
		try {
			turma = turmaDAO.findById(idTurma);
		} catch (SQLException e) {
			return Response.serverError().build();
		}

		return Response.ok().entity(turma).build();
	}
}
