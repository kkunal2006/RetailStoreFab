/**
 * 
 */
package com.kunal.retailstore.model;

import java.util.List;

import com.kunal.retailstore.entity.Billing;
import com.kunal.retailstore.entity.OnlineShoppingCartItem;
import com.kunal.retailstore.entity.RetailCustomer;


/**
 * @author kkunal 26-Aug-2019
 *
 */

public interface BillingCustomerInterface {

	public void gatherRetailCustomerInfo(RetailCustomer user);

	public Double getGroceriedItemCost(List<OnlineShoppingCartItem> onlineShoppingCartItem);

	public Double collectPurchasedItems(List<OnlineShoppingCartItem> onlineShoppingCartItem);

	public Double userTypeDiscountApply(Double cost);

	public Double totalBillDiscountApply(Double cost);
	
	public String printBillDetails(Billing billing);
	
	Double gatherRetailCustomerInfo(List<RetailCustomer> retailCustomer);

}
