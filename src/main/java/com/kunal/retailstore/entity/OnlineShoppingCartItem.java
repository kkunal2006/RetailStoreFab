package com.kunal.retailstore.entity;

/**
 * @author kkunal 26-Aug-2019
 */

public class OnlineShoppingCartItem {
	private String itemName;
	private Double itemCost;
	private int count;

	private OnlineShoppingCategoryProducts onlineShoppingCategoryProducts;

	public OnlineShoppingCartItem() {
	}

	public OnlineShoppingCartItem(String itemName, Double itemCost, int count,
			OnlineShoppingCategoryProducts onlineShoppingCategoryProducts) {
		this.itemName = itemName;
		this.itemCost = itemCost;
		this.count = count;
		this.onlineShoppingCategoryProducts = onlineShoppingCategoryProducts;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemCost
	 */
	public Double getItemCost() {
		return itemCost;
	}

	/**
	 * @param itemCost the itemCost to set
	 */
	public void setItemCost(Double itemCost) {
		this.itemCost = itemCost;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the onlineShoppingCategoryProducts
	 */
	public OnlineShoppingCategoryProducts getOnlineShoppingCategoryProducts() {
		return onlineShoppingCategoryProducts;
	}

	/**
	 * @param onlineShoppingCategoryProducts the onlineShoppingCategoryProducts to
	 *                                       set
	 */
	public void setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts onlineShoppingCategoryProducts) {
		this.onlineShoppingCategoryProducts = onlineShoppingCategoryProducts;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OnlineShoppingCartItem [itemName=");
		builder.append(itemName);
		builder.append(", itemCost=");
		builder.append(itemCost);
		builder.append(", count=");
		builder.append(count);
		builder.append(", onlineShoppingCategoryProducts=");
		builder.append(onlineShoppingCategoryProducts);
		builder.append("]");
		return builder.toString();
	}
}
