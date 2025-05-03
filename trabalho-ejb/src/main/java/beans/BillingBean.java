package beans;

import java.util.List;
import java.util.logging.Logger;

import entities.Billing;
import interfaces.BillingLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import queue.BillingQueueSender;
import support.BillingConverter;
import to.BillingTO;

@Stateless
@Local(BillingLocal.class)
public class BillingBean implements BillingLocal {

	@Inject
	Logger log;
	
	@EJB
	BillingQueueSender queueSender;
	
	@PersistenceContext(unitName = "trabalho_dm110_pu")
	private EntityManager em;
	
	@Override
	public void createBilling(BillingTO billing) {
		log.info("Saving billing number " + billing.getBillCode() + " to Database.");
		Billing entity = BillingConverter.toEntity(billing);
		em.persist(entity);
		queueSender.sendTextMessage("CREATE:" + billing.getBillCode());
	}

	@Override
	public void updateBilling(BillingTO billing) {
		log.info("Updating billing number " + billing.getBillCode() + ".");
		Billing entity = BillingConverter.toEntity(billing);
		em.merge(entity);
		queueSender.sendTextMessage("UPDATE:" + billing.getBillCode());
	}

	@Override
	public void deleteBilling(int billingCode) {
		log.info("Deleting billing number " + billingCode + ".");
		em.remove(em.find(Billing.class, billingCode));
		queueSender.sendTextMessage("DELETE:" + billingCode);
	}

	@Override
	public List<BillingTO> listAllBillings() {
		log.info("Listing all billings.");
		
		String hql = "select b from Billing b";
		TypedQuery<Billing> query = em.createQuery(hql, Billing.class);
		return BillingConverter.toToList(query.getResultList());
	}

	@Override
	public BillingTO getBilling(int billingCode) {
		log.info("Grabbing billing number " + billingCode + ".");
		Billing billing = em.find(Billing.class, billingCode);
		return BillingConverter.toBillingTO(billing);
	}	
}
