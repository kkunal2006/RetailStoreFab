package com.kunal.retailstore.model;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.kunal.retailstore.entity.Billing;
import com.kunal.retailstore.entity.OnlineShoppingCartItem;
import com.kunal.retailstore.entity.OnlineShoppingCategoryProducts;
import com.kunal.retailstore.entity.RetailCustomer;

/**
 * The Class RetailCustomerBill.
 *
 * @author kkunal 26-Aug-2019
 */

public class RetailCustomerBill implements BillingCustomerInterface {
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(RetailCustomerBill.class);

	/** The retail customer user bill. */
	private Billing retailCustomerUserBill;

	/** The retail customer. */
	private RetailCustomer retailCustomer;

		/**
	 * Gather retail customer info.
	 *
	 * @param retailCustomer the retail customer
	 */
	@Override
	public void gatherRetailCustomerInfo(RetailCustomer retailCustomer) {
		retailCustomerUserBill = new Billing();
		this.retailCustomer = retailCustomer;
	}
	
		/**
	 * Calculate total purchased items for the online products related to billing calculation.
	 *
	 * @param onlineShoppingCartItem the online shopping cart item
	 * @return the double
	 */
	@Override
	public Double calculateTotalPurchasedItems(List<OnlineShoppingCartItem> onlineShoppingCartItem) {
		retailCustomerUserBill.setOnlineShoppingCartItem(onlineShoppingCartItem);
		return getTotalBillCost(onlineShoppingCartItem);
	}

		/**
	 * Gets the total bill cost for all the items purchased.
	 *
	 * @param onlineShoppingItemsList for the total items
	 * @return the total bill cost
	 */
	private Double getTotalBillCost(List<OnlineShoppingCartItem> onlineShoppingItemsList) {
		double cost = 0.0;

		for (OnlineShoppingCartItem shoppingItem : onlineShoppingItemsList) {
			cost += shoppingItem.getItemCost();
		}
		return cost;
	}

		/**
	 * Retail customer type discount apply as per the below categories
	 * If the user is an employee of the store, he gets a 30% discount
	 * If the user is an affiliate of the store, he gets a 10% discount
	 * If the user has been a customer for over 2 years, he gets a 5% discount.
	 *
	 * @param cost the cost
	 * @return the double
	 */
	@Override
	public Double retailCustomerTypeDiscountApply(Double cost) {
		retailCustomerUserBill.setTotalBill(cost);
		double discountAmount;
		int discountPercentage = retailCustomer.getRetailCustomerType().getDiscountPercentage();
		Double onlineShoppingAmount = getOnlineItemCost(retailCustomerUserBill.getOnlineShoppingCartItem());
		logger.info("Total Billing Amount is :" + retailCustomerUserBill.getTotalBill());
		logger.info(
				"RetailCustomer Type is: " + retailCustomer.getRetailCustomerType().getUserTypeId() + "Discount: " + discountPercentage + " %");

		logger.info("Total Online Shopping Products Amount :" + onlineShoppingAmount);
		discountAmount = cost - onlineShoppingAmount;
		logger.info("Total Bill Amount Without Online Shopping Products is :" + discountAmount);

		discountAmount = (discountAmount * discountPercentage) / 100;
		logger.info("Total user Type discount Amount: " + discountAmount);

		retailCustomerUserBill.setTotalBillAfterRetailCustomerTypeDiscount(cost - discountAmount);
		
		logger.info("Total Bill Amount After RetailCustomer Type Discount is :" + retailCustomerUserBill.getTotalBillAfterRetailCustomerTypeDiscount());
		return retailCustomerUserBill.getTotalBillAfterRetailCustomerTypeDiscount();
	}

			/**
	 * Discounts applied on the total bill .
	 * For every $100 on the bill, there would be a $ 5 discount.
	 *
	 * @param billCost the bill cost
	 * @return the double
	 */
	@Override
	public Double discountsAppliedOnTotalBill(Double billCost) {
		logger.info("Bill Amount Beofre Final Discount is : " + billCost);
		// Logic For Decreasing 5 for each 100
		retailCustomerUserBill.setTotalBillCost(billCost - Math.floor(Math.floor(billCost) / 100) * 5);
		logger.info("Bill Amount After Final Discount is : " + retailCustomerUserBill.getTotalBillCost());
		return retailCustomerUserBill.getTotalBillCost();
	}

		/**
	 * Gets the online item cost.
	 *
	 * @param onlineShoppingCartItem the online shopping cart item
	 * @return the online item cost
	 */
	@Override
	public Double getOnlineItemCost(List<OnlineShoppingCartItem> onlineShoppingCartItem) {
		double cost = 0.0;
		for (OnlineShoppingCartItem onlineShoppingCartItemList : retailCustomerUserBill.getOnlineShoppingCartItem()) {
			if (OnlineShoppingCategoryProducts.SOFTWARES.equals(onlineShoppingCartItemList.getOnlineShoppingCategoryProducts())) {
				cost += onlineShoppingCartItemList.getItemCost();
			}
		}

		return cost;
	}

		/**
	 * Prints the final bill invoice for the Retail Customers.
	 *
	 * @param bill the bill
	 * @return the string
	 */
	@Override
	public String printBillInvoice(Billing bill) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("RetailCustomer Details:");
		stringBuilder.append(retailCustomer);
		stringBuilder.append("Shop the following:");
		stringBuilder.append(bill.getOnlineShoppingCartItem());
		stringBuilder.append(" Total Bill Amount : ");
		stringBuilder.append(bill.getTotalBill());
		stringBuilder.append(" Bill Amount After RetailCustomer Discount:");
		stringBuilder.append(bill.getTotalBillAfterRetailCustomerTypeDiscount());
		stringBuilder.append(" Bill Amount After final Discount:");
		stringBuilder.append(bill.getTotalBillCost());
		return stringBuilder.toString();
	}
}
