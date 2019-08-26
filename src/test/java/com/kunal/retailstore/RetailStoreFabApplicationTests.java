package com.kunal.retailstore;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kunal.retailstore.entity.Billing;
import com.kunal.retailstore.entity.OnlineShoppingCartItem;
import com.kunal.retailstore.entity.OnlineShoppingCategoryProducts;
import com.kunal.retailstore.entity.RetailCustomer;
import com.kunal.retailstore.entity.RetailCustomerType;
import com.kunal.retailstore.model.BillingCustomerQuery;
import com.kunal.retailstore.model.RetailCustomerBill;
import com.kunal.retailstore.model.RetailCustomerQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailStoreFabApplicationTests {

	@Mock
	List<OnlineShoppingCartItem> onlineShoppingCartItem;

	@InjectMocks
	RetailCustomerBill retailCustomerBill;

	@Mock
	Billing billing;

	@Autowired
	BillingCustomerQuery billingCustomerQuery;

	@Autowired
	RetailCustomerQuery retailCustomerQuery;

	@Before
	public void before() {
		System.out.println("Before");

		onlineShoppingCartItem = new ArrayList<OnlineShoppingCartItem>();

		OnlineShoppingCartItem onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Microsoft Office 2019");
		onlineShoppingCartItemList.setItemCost(25000.95);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.SOFTWARES);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		billing = billingCustomerQuery.totalBill(0.0).shoopingItemList(onlineShoppingCartItem)
				.totalBillAfterRetailCustomerTypeDiscount(0.0).finalbillCost(0.0).Build();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void main() {
		RetailStoreFabApplication.main(new String[] {});
	}
	
	@Test
	public void testTotalPurchasedItems() {
		Double billCost = retailCustomerBill.collectPurchasedItems(onlineShoppingCartItem);
		assertEquals(new Double(25000.95), billCost);
	}
	
	@Test
	public void test_Loyal_CustomerType() {
		assertEquals(RetailCustomerType.LOYAL_CUSTOMER, RetailCustomerType.valueOf("LOYAL_CUSTOMER"));
	}
	
	@Test
	public void testOnlineShoppingCategoryProducts() {
		assertEquals(OnlineShoppingCategoryProducts.ELECTRONICS, OnlineShoppingCategoryProducts.valueOf("ELECTRONICS"));
	}
	
	// Calculating 30% Discount
	//	@Test
	//	public void testEmployeeSegmentDicount() {
	//		retailCustomerBill.gatherRetailCustomerInfo((List<RetailCustomer>) retailCustomerQuery.firstName("Kumar").lastName("Kunal").phone("971585773108").userType(RetailCustomerType.EMPLOYEE));
	//		Double billCost = retailCustomerBill.collectPurchasedItems(onlineShoppingCartItem);
	//		Double billCostAfterDiscount = retailCustomerBill.userTypeDiscountApply(billCost);
	//		Double deductionAmount = (billCost - retailCustomerBill.getGroceriedItemCost(onlineShoppingCartItem));
	//		assertEquals(new Double(billCost - new Double((deductionAmount * 30) / 100)), billCostAfterDiscount);
	//	}

}
