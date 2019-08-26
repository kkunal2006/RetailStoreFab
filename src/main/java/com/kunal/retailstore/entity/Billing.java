/**
 * 
 */
package com.kunal.retailstore.entity;

import java.util.List;

/**
 * @author kkunal 26-Aug-2019
 *
 */

public class Billing {

	private List<OnlineShoppingCartItem> onlineShoppingCartItem;
	private Double totalBill;
	private Double totalBillAfterRetailCustomerTypeDiscount;
	private Double totalBillCost;

	public Billing() {
		super();
	}

	public Billing(List<OnlineShoppingCartItem> onlineShoppingCartItem, Double totalBill,
			Double totalBillAfterRetailCustomerTypeDiscount, Double totalBillCost) {
		super();
		this.onlineShoppingCartItem = onlineShoppingCartItem;
		this.totalBill = totalBill;
		this.totalBillAfterRetailCustomerTypeDiscount = totalBillAfterRetailCustomerTypeDiscount;
		this.totalBillCost = totalBillCost;
	}

	/**
	 * @return the onlineShoppingCartItem
	 */
	public List<OnlineShoppingCartItem> getOnlineShoppingCartItem() {
		return onlineShoppingCartItem;
	}

	/**
	 * @param onlineShoppingCartItem the onlineShoppingCartItem to set
	 */
	public void setOnlineShoppingCartItem(List<OnlineShoppingCartItem> onlineShoppingCartItem) {
		this.onlineShoppingCartItem = onlineShoppingCartItem;
	}

	/**
	 * @return the totalBill
	 */
	public Double getTotalBill() {
		return totalBill;
	}

	/**
	 * @param totalBill the totalBill to set
	 */
	public void setTotalBill(Double totalBill) {
		this.totalBill = totalBill;
	}

	/**
	 * @return the totalBillAfterRetailCustomerTypeDiscount
	 */
	public Double getTotalBillAfterRetailCustomerTypeDiscount() {
		return totalBillAfterRetailCustomerTypeDiscount;
	}

	/**
	 * @param totalBillAfterRetailCustomerTypeDiscount the
	 *                                                 totalBillAfterRetailCustomerTypeDiscount
	 *                                                 to set
	 */
	public void setTotalBillAfterRetailCustomerTypeDiscount(Double totalBillAfterRetailCustomerTypeDiscount) {
		this.totalBillAfterRetailCustomerTypeDiscount = totalBillAfterRetailCustomerTypeDiscount;
	}

	/**
	 * @return the totalBillCost
	 */
	public Double getTotalBillCost() {
		return totalBillCost;
	}

	/**
	 * @param totalBillCost the totalBillCost to set
	 */
	public void setTotalBillCost(Double totalBillCost) {
		this.totalBillCost = totalBillCost;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Billing [onlineShoppingCartItem=");
		builder.append(onlineShoppingCartItem);
		builder.append(", totalBill=");
		builder.append(totalBill);
		builder.append(", totalBillAfterRetailCustomerTypeDiscount=");
		builder.append(totalBillAfterRetailCustomerTypeDiscount);
		builder.append(", totalBillCost=");
		builder.append(totalBillCost);
		builder.append("]");
		return builder.toString();
	}

}