package resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Bloco;
import model.Curso;
import dao.BlocoDAO;
import dao.CursoDAO;

@Path("/curso")
public class CursoResource {

	private CursoDAO cursoDAO = new CursoDAO();
	private BlocoDAO blocoDAO = new BlocoDAO();
	
	@GET
	@Produces("application/json; charset=utf-8")
	public Response getAll(){
		List<Curso> cursos;
		try {
			cursos = cursoDAO.listar();
		} catch (SQLException e) {
			return Response.serverError().build();
		}
		GenericEntity<List<Curso>> entity = new GenericEntity<List<Curso>>(cursos) {};
		
		return Response.ok().entity(entity).build();
	}
	
	@GET
	@Produces("application/json; charset=utf-8")
	@Path("/{cursoId}")
	public Response getCurso(@PathParam("cursoId") Long cursoId){
		Curso curso;
		try {
			curso = cursoDAO.consultar(cursoId);
			List<Bloco> blocos = blocoDAO.consultar(cursoId);
			
			curso.setBlocos(blocos);
			
		} catch (SQLException e) {
			return Response.serverError().build();
		}
		
		return Response.status(Status.OK).entity(curso).build();
	}
}
