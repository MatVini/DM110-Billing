package support;

import java.util.List;
import java.util.stream.Collectors;

import entities.Billing;
import to.BillingTO;

public class BillingConverter {
	
	public static BillingTO toBillingTO(Billing billing) {
		return new BillingTO(billing.getBillCode(), billing.getDescription(), billing.getValue(),
				billing.getExpirationDate(), billing.getPaidDate());
	}
	
	public static Billing toEntity(BillingTO to) {
		return new Billing(to.getBillCode(), to.getDescription(), to.getValue(),
				to.getExpirationDate(), to.getPaidDate());
	}
	
	public static List<BillingTO> toToList(List<Billing> billingList) {
		return billingList.stream().map(BillingConverter::toBillingTO).collect(Collectors.toList());
	}

}
