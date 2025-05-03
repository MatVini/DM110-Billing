package beans;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import entities.Auditing;
import interfaces.AuditingLocal;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import support.AuditingConverter;
import to.AuditingTO;

@Stateless
@Local(AuditingLocal.class)
public class AuditingBean implements AuditingLocal {

	@Inject
	Logger log;
	
	@PersistenceContext(unitName = "trabalho_dm110_pu")
	private EntityManager em;
	
	@Override
	public void registerOperation(int billingCode, String operation, Date date) {
		Auditing entity = new Auditing();
		entity.setRegisterCode(billingCode);
		entity.setOperation(operation);
		entity.setTimestamp(date);
		em.persist(entity);
	}

	@Override
	public List<AuditingTO> listAll() {
		log.info("Listing all audits.");

		String hql = "select a from Auditing a";
		TypedQuery<Auditing> query = em.createQuery(hql, Auditing.class);
		return AuditingConverter.toToList(query.getResultList());		
	}
}
