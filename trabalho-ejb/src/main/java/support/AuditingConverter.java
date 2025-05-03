package support;

import java.util.List;
import java.util.stream.Collectors;

import entities.Auditing;
import to.AuditingTO;

public class AuditingConverter {

	public static AuditingTO toAuditingTO(Auditing auditing) {
		return new AuditingTO(auditing.getIdentifier(), auditing.getRegisterCode(),
				auditing.getOperation(), auditing.getTimestamp());
	}
	
	public static  List<AuditingTO> toToList(List<Auditing> auditingList) {
		return auditingList.stream().map(AuditingConverter::toAuditingTO).collect(Collectors.toList());
	}
}
