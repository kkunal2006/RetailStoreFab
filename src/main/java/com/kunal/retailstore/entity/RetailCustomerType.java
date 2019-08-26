package com.kunal.retailstore.entity;

/**
 * @author kkunal 26-Aug-2019
 *
 */

public enum RetailCustomerType {

	EMPLOYEE(1, 30), AFFILIATE(2, 10), LOYAL_CUSTOMER(3, 5);

	private final int typeId;
	private final int discountPercentage;
	
	private RetailCustomerType(Integer type, Integer discPercentage) {
		this.typeId = type;
		this.discountPercentage = discPercentage;
	}

	public int getDiscountPercentage() {
		return this.discountPercentage;
	}

	public int getUserTypeId() {
		return this.typeId;
	}

}
