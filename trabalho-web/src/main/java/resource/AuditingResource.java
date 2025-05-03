package resource;

import java.util.List;

import interfaces.AuditingLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import to.AuditingTO;

@RequestScoped
@Path("/auditing")
public class AuditingResource {

	@EJB
	private AuditingLocal auditBean;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AuditingTO> listAll() {
		return auditBean.listAll();
	}
}
