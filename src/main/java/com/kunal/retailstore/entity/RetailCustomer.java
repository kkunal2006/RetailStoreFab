/**
 * 
 */
package com.kunal.retailstore.entity;

/**
 * @author kkunal 26-Aug-2019
 *
 */

public class RetailCustomer {

	private String firstName;
	private String lastName;
	private String phone;
	private RetailCustomerType retailCustomerType;

	public RetailCustomer(String firstName, String lastName, String phone, RetailCustomerType retailCustomerType) {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setRetailCustomerType(retailCustomerType);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the retailCustomerType
	 */
	public RetailCustomerType getRetailCustomerType() {
		return retailCustomerType;
	}

	/**
	 * @param retailCustomerType the retailCustomerType to set
	 */
	public void setRetailCustomerType(RetailCustomerType retailCustomerType) {
		this.retailCustomerType = retailCustomerType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RetailCustomer [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", retailCustomerType=");
		builder.append(retailCustomerType);
		builder.append("]");
		return builder.toString();
	}

}
