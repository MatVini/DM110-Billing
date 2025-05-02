package interfaces;

import java.util.List;

import to.BillingTO;

public interface BillingLocal {
	
	public void createBilling(BillingTO billing);
	public BillingTO getBilling(int billingCode);
	public void updateBilling(BillingTO billing);
	public void deleteBilling(int billingCode);
	public List<BillingTO> listAllBillings();
}
