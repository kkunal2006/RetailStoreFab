/**
 * 
 */
package com.kunal.retailstore.model;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.kunal.retailstore.entity.Billing;
import com.kunal.retailstore.entity.OnlineShoppingCartItem;
import com.kunal.retailstore.entity.OnlineShoppingCategoryProducts;
import com.kunal.retailstore.entity.RetailCustomer;


/**
 * @author kkunal 26-Aug-2019
 *
 */

public class RetailCustomerBill implements BillingCustomerInterface {
	
	private static final Logger logger = LogManager.getLogger(RetailCustomerBill.class);

	private Billing retailCustomerUserBill;

	private RetailCustomer retailCustomer;

	
	@Override
	public Double gatherRetailCustomerInfo(List<RetailCustomer> retailCustomer) {
		retailCustomerUserBill = new Billing();
		this.retailCustomer = (RetailCustomer) retailCustomer;
		return null;

	}
	
	/*
	 *  This function used to prepare shopping list for bill calculation
	 * @param shoopingItemList list of item user buy
	 * 
	 * @return total bill cost
	 */
	@Override
	public Double collectPurchasedItems(List<OnlineShoppingCartItem> onlineShoppingCartItem) {
		retailCustomerUserBill.setOnlineShoppingCartItem(onlineShoppingCartItem);
		return getTotalBillCost(onlineShoppingCartItem);
	}

	/*
	 * This function to calculate shopping cost for all items
	 * 
	 * @param shoopingItemList list of item user buy
	 * 
	 * @return total bill cost
	 */
	private Double getTotalBillCost(List<OnlineShoppingCartItem> shoopingItemList) {
		Double cost = 0.0;

		for (OnlineShoppingCartItem shoppingItem : shoopingItemList) {
			cost += shoppingItem.getItemCost();
		}
		return cost;
	}

	/**
	 * This function user to apply discount per user segment as the following
	 * Employee of the store, he gets a 30% discount 
	 * Affiliate of the store, he gets a 10% discount 
	 * Customer for over 2 years, he gets a 5% discount
	 * 
	 * @param shoopingItemList  list of item user buy
	 * @return total bill cost after user type discount
	 */
	@Override
	public Double userTypeDiscountApply(Double cost) {
		retailCustomerUserBill.setTotalBill(cost);
		Double discountAmount = 0.0;
		int discountPercentage = retailCustomer.getRetailCustomerType().getDiscountPercentage();
		Double groceriesAmount = getGroceriedItemCost(retailCustomerUserBill.getOnlineShoppingCartItem());
		logger.info("Total Billing Amount is :" + retailCustomerUserBill.getTotalBill());
		logger.info(
				"RetailCustomer Type is: " + retailCustomer.getRetailCustomerType().getUserTypeId() + "Discount: " + discountPercentage + " %");

		logger.info("Total GROCERIES items is :" + groceriesAmount);
		discountAmount = (cost - groceriesAmount);
		logger.info("Total Bill Amount Without GROCERIES is :" + discountAmount);

		discountAmount = ((discountAmount * discountPercentage) / 100);
		logger.info("Total user Type discount Amount: " + discountAmount);

		retailCustomerUserBill.setTotalBillAfterRetailCustomerTypeDiscount(cost - discountAmount);
		
		logger.info("Total Bill Amount After RetailCustomer Type Discount is :" + retailCustomerUserBill.getTotalBillAfterRetailCustomerTypeDiscount());
		return retailCustomerUserBill.getTotalBillAfterRetailCustomerTypeDiscount();
	}

	/*
	 * This function used to calculate final discount on bill using the following
	 * For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
	 * 
	 * @param billCost after apply first discount for user type
	 * @return final bill amount after apply discount
	 */
	
	@Override
	public Double totalBillDiscountApply(Double billCost) {

		logger.info("Bill Amount Beofre Final Discount is : " + billCost);

		// Decrease 5 for each 100
		retailCustomerUserBill.setTotalBillCost(billCost - (Math.floor(Math.floor(billCost) / 100) * 5));
		logger.info("Bill Amount After Final Discount is : " + retailCustomerUserBill.getTotalBillCost());

		return retailCustomerUserBill.getTotalBillCost();
	}

	/*
	 * This function used to return the total of grocieres items
	 * 
	 * @param shoopingItemList  list of item user buy
	 * @return cost of groceries items
	 */
	@Override
	public Double getGroceriedItemCost(List<OnlineShoppingCartItem> onlineShoppingCartItem) {
		Double cost = 0.0;
		for (OnlineShoppingCartItem onlineShoppingCartItemList : retailCustomerUserBill.getOnlineShoppingCartItem()) {
			if (onlineShoppingCartItemList.getOnlineShoppingCategoryProducts().equals(OnlineShoppingCategoryProducts.SOFTWARES)) {
				cost += onlineShoppingCartItemList.getItemCost();
			}
		}

		return cost;
	}

	/*
	 * This function used to print full trace of bill statement
	 * @param Bill 
	 * 
	 */
	@Override
	public String printBillDetails(Billing bill) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("RetailCustomer Details:");
		stringBuilder.append(retailCustomer.toString());
		stringBuilder.append("Shop the following:");
		stringBuilder.append(bill.getOnlineShoppingCartItem().toString());
		stringBuilder.append(" Total Bill Amount : ");
		stringBuilder.append(bill.getTotalBill());
		stringBuilder.append(" Bill Amount After RetailCustomer Discount:");
		stringBuilder.append(bill.getTotalBillAfterRetailCustomerTypeDiscount());
		stringBuilder.append(" Bill Amount After final Discount:");
		stringBuilder.append(bill.getTotalBillCost());
		return stringBuilder.toString();

	}

	@Override
	public void gatherRetailCustomerInfo(RetailCustomer user) {
		
	}

}
