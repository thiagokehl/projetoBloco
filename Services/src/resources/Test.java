package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class Test {

	@GET
	@Produces("application/json")
	public String getTest(){
		return "HELLO WORLD TEST";
	}
}
