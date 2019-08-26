/**
 * 
 */
package com.kunal.retailstore.model;

import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.kunal.retailstore.entity.RetailCustomer;
import com.kunal.retailstore.entity.RetailCustomerType;

/**
 * @author kkunal 26-Aug-2019
 *
 */

@Component
public class RetailCustomerQuery {

	private String firstName;
	private String lastName;
	private String phone;
	private RetailCustomerType userType;

	public RetailCustomerQuery() {
		super();
	}

	public RetailCustomerQuery firstName(String firstname) {
		this.firstName = firstname;
		return this;
	}

	public RetailCustomerQuery lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public RetailCustomerQuery phone(String phone) {
		this.phone = phone;
		return this;
	}

	public RetailCustomerQuery userType(RetailCustomerType userType) {
		this.userType = userType;
		return this;
	}

	public RetailCustomer build() {
		return new RetailCustomer(firstName, lastName, phone, userType);
	}
}
