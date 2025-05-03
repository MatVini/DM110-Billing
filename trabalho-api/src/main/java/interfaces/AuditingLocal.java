package interfaces;

import java.util.Date;
import java.util.List;

import to.AuditingTO;

public interface AuditingLocal {

	public void registerOperation(int billingCode, String operation, Date date);
	public List<AuditingTO> listAll();
}
