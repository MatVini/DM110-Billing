package resource;

import java.util.List;

import interfaces.BillingLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import to.BillingTO;

@RequestScoped
@Path("/billing")
public class BillingResource implements BillingLocal {

	@EJB
	private BillingLocal billingBean;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void createBilling(BillingTO billing) {
		billingBean.createBilling(billing);
	}

	@GET
	@Path("/{billingCode}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public BillingTO getBilling(@PathParam("billingCode") int billingCode) {
		return billingBean.getBilling(billingCode);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void updateBilling(BillingTO billing) {
		billingBean.updateBilling(billing);
	}

	@DELETE
	@Path("/{billingCode}")
	@Override
	public void deleteBilling(@PathParam("billingCode") int billingCode) {
		billingBean.deleteBilling(billingCode);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<BillingTO> listAllBillings() {
		return billingBean.listAllBillings();
	}

}
