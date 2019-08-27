package com.kunal.retailstore.model;

import java.util.List;

import com.kunal.retailstore.entity.Billing;
import com.kunal.retailstore.entity.OnlineShoppingCartItem;
import com.kunal.retailstore.entity.RetailCustomer;

/**
 * @author kkunal 26-Aug-2019
 */

public interface BillingCustomerInterface {
	void gatherRetailCustomerInfo(RetailCustomer retailCustomer);

	Double getOnlineItemCost(List<OnlineShoppingCartItem> onlineShoppingCartItem);

	Double calculateTotalPurchasedItems(List<OnlineShoppingCartItem> onlineShoppingCartItem);

	Double retailCustomerTypeDiscountApply(Double retailCustomerTypeDiscountApplyCost);

	Double discountsAppliedOnTotalBill(Double discountsAppliedOnTotalBillCost);
	
	String printBillInvoice(Billing billing);
}
