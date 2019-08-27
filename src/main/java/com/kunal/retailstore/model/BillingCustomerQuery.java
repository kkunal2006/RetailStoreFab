package com.kunal.retailstore.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kunal.retailstore.entity.Billing;
import com.kunal.retailstore.entity.OnlineShoppingCartItem;

/**
 * @author kkunal 26-Aug-2019
 */

@Component
public class BillingCustomerQuery {
	private List<OnlineShoppingCartItem> onlineShoppingCartItem;
	private Double totalBill;
	private Double totalBillAfterRetailCustomerTypeDiscount;
	private Double finalbillCost;

	public BillingCustomerQuery shoopingItemList(List<OnlineShoppingCartItem> onlineShoppingCartItem) {
		this.onlineShoppingCartItem = onlineShoppingCartItem;
		return this;
	}

	public BillingCustomerQuery totalBill(Double totalBill) {
		this.totalBill = totalBill;
		return this;
	}

	public BillingCustomerQuery totalBillAfterRetailCustomerTypeDiscount(Double totalBillAfterRetailCustomerTypeDiscount) {
		this.totalBillAfterRetailCustomerTypeDiscount = totalBillAfterRetailCustomerTypeDiscount;
		return this;
	}

	public BillingCustomerQuery finalbillCost(Double finalbillCost) {
		this.finalbillCost = finalbillCost;
		return this;
	}

	public Billing Build() {
		return new Billing(onlineShoppingCartItem, totalBill, totalBillAfterRetailCustomerTypeDiscount, finalbillCost);
	}
}
