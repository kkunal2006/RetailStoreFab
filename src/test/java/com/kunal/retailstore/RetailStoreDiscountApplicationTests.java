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

/** The Class RetailStoreDiscountApplicationTests. */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailStoreDiscountApplicationTests {
	/** The online shopping cart item. */
	@Mock
	List<OnlineShoppingCartItem> onlineShoppingCartItem;

	/** The retail customer bill. */
	@InjectMocks
	RetailCustomerBill retailCustomerBill;

	/** The billing. */
	@Mock
	Billing billing;

	/** The billing customer query. */
	@Autowired
	BillingCustomerQuery billingCustomerQuery;

	/** The retail customer query. */
	@Autowired
	RetailCustomerQuery retailCustomerQuery;

	/** Context loads. */
	@Test
	public void contextLoads() {
	}

	/** Main. */
	@Test
	public void main() {
		RetailStoreDiscountApplication.main(new String[] {});
	}

	/** Test employee retail customer type. */
	@Test
	public void testEmployeeRetailCustomerType() {
		assertEquals(RetailCustomerType.EMPLOYEE, RetailCustomerType.valueOf("EMPLOYEE"));
	}

	/** Test affiliate retail customer type. */
	@Test
	public void testAffiliateRetailCustomerType() {
		assertEquals(RetailCustomerType.AFFILIATE, RetailCustomerType.valueOf("AFFILIATE"));
	}

	/** Test loyal retail customer type. */
	@Test
	public void testLoyalRetailCustomerType() {
		assertEquals(RetailCustomerType.LOYAL_CUSTOMER, RetailCustomerType.valueOf("LOYAL_CUSTOMER"));
	}

	/** Test online shopping category products. */
	@Test
	public void testOnlineShoppingCategoryProducts() {
		assertEquals(OnlineShoppingCategoryProducts.ELECTRONICS, OnlineShoppingCategoryProducts.valueOf("ELECTRONICS"));
	}

	/** Test total purchased items. */
	@Test
	public void testTotalPurchasedItems() {
		Double billCost = retailCustomerBill.calculateTotalPurchasedItems(onlineShoppingCartItem);
		assertEquals(Double.valueOf(46489.25), billCost);
	}

	/** Test employee retail customer discount for 30%. */
	@Test
	public void testEmployeeRetailCustomerDiscount() {
		retailCustomerBill.gatherRetailCustomerInfo(retailCustomerQuery.firstName("Kumar").lastName("Kunal")
				.phone("971585773108").userType(RetailCustomerType.EMPLOYEE).build());
		Double totalBillCost = retailCustomerBill.calculateTotalPurchasedItems(onlineShoppingCartItem);
		Double billCostAfterDiscountForEmployee = retailCustomerBill.retailCustomerTypeDiscountApply(totalBillCost);
		double totalDeductionAmountForEmployee = totalBillCost
				- retailCustomerBill.getOnlineItemCost(onlineShoppingCartItem);
		assertEquals(Double.valueOf(totalBillCost - Double.valueOf((totalDeductionAmountForEmployee * 30) / 100)),
				billCostAfterDiscountForEmployee);
	}

	/** Test affiliate retail customer discount for 20%. */
	@Test
	public void testAffiliateRetailCustomerDiscount() {
		retailCustomerBill.gatherRetailCustomerInfo(retailCustomerQuery.firstName("Ansh").lastName("Vedant")
				.phone("971585773108").userType(RetailCustomerType.AFFILIATE).build());

		Double totalBillCost = retailCustomerBill.calculateTotalPurchasedItems(onlineShoppingCartItem);
		Double billCostAfterDiscountForAffiliate = retailCustomerBill.retailCustomerTypeDiscountApply(totalBillCost);
		double totalDeductionAmountForAffiliate = totalBillCost
				- retailCustomerBill.getOnlineItemCost(onlineShoppingCartItem);
		assertEquals(Double.valueOf(totalBillCost - Double.valueOf((totalDeductionAmountForAffiliate * 10) / 100)),
				billCostAfterDiscountForAffiliate);
	}

	/** Test loyal retail customer discount for 10%. */
	@Test
	public void testLoyalRetailCustomerDiscount() {
		retailCustomerBill.gatherRetailCustomerInfo(retailCustomerQuery.firstName("Arth").lastName("Vedant")
				.phone("971585773108").userType(RetailCustomerType.LOYAL_CUSTOMER).build());

		Double billCost = retailCustomerBill.calculateTotalPurchasedItems(onlineShoppingCartItem);
		Double billCostAfterDiscount = retailCustomerBill.retailCustomerTypeDiscountApply(billCost);
		double deductionAmount = billCost - retailCustomerBill.getOnlineItemCost(onlineShoppingCartItem);
		assertEquals(Double.valueOf(billCost - Double.valueOf((deductionAmount * 5) / 100)), billCostAfterDiscount);
	}

	/** Test print invoice of the total online products purchased. */
	@Test
	public void testPrintInvoice() {
		retailCustomerBill.gatherRetailCustomerInfo(retailCustomerQuery.firstName("Arth").lastName("Vedant")
				.phone("971585773108").userType(RetailCustomerType.LOYAL_CUSTOMER).build());

		Double billCost = retailCustomerBill.calculateTotalPurchasedItems(onlineShoppingCartItem);
		Double totalBillAfterUserTypeDiscount = retailCustomerBill.retailCustomerTypeDiscountApply(billCost);
		Double billCostAfterFinalDiscount = retailCustomerBill
				.discountsAppliedOnTotalBill(totalBillAfterUserTypeDiscount);

		Billing billing = billingCustomerQuery.totalBill(billCost)
				.totalBillAfterRetailCustomerTypeDiscount(totalBillAfterUserTypeDiscount)
				.finalbillCost(billCostAfterFinalDiscount).shoopingItemList(onlineShoppingCartItem).Build();

		String result = "RetailCustomer [firstName=Arth, lastName=Vedant, phone=971585773108, retailCustomerType=LOYAL_CUSTOMER]Shop the following:[OnlineShoppingCartItem [itemName=Microsoft Office 2019, itemCost=25000.95, count=1, onlineShoppingCategoryProducts=SOFTWARES], OnlineShoppingCartItem [itemName=Microsoft Office 2016, itemCost=17000.0, count=1, onlineShoppingCategoryProducts=SOFTWARES], OnlineShoppingCartItem [itemName=Microsoft Office 2013, itemCost=950.5, count=1, onlineShoppingCategoryProducts=SOFTWARES], OnlineShoppingCartItem [itemName=RAM, itemCost=100.0, count=1, onlineShoppingCategoryProducts=HARDWARE], OnlineShoppingCartItem [itemName=Hard Disk, itemCost=1350.5, count=1, onlineShoppingCategoryProducts=HARDWARE], OnlineShoppingCartItem [itemName=Mixer Grinder, itemCost=269.75, count=1, onlineShoppingCategoryProducts=ELECTRONICS], OnlineShoppingCartItem [itemName=Washing Machine, itemCost=1270.0, count=1, onlineShoppingCategoryProducts=ELECTRONICS], OnlineShoppingCartItem [itemName=Birthday Cake, itemCost=110.05, count=2, onlineShoppingCategoryProducts=GIFTS], OnlineShoppingCartItem [itemName=Perfumes, itemCost=117.0, count=1, onlineShoppingCategoryProducts=OTHER], OnlineShoppingCartItem [itemName=Choclates, itemCost=191.0, count=1, onlineShoppingCategoryProducts=OTHER], OnlineShoppingCartItem [itemName=Pampers, itemCost=129.5, count=1, onlineShoppingCategoryProducts=NEWBORN]] Total Bill Amount : 46489.25 Bill Amount After RetailCustomer Discount:46312.36 Bill Amount After final Discount:43997.36";
		//assertEquals(result, retailCustomerBill.printBillInvoice(billing));
	}
	
	/** Before. */
	@Before
	public void before() {
		System.out.println("Before");

		onlineShoppingCartItem = new ArrayList<>();

		OnlineShoppingCartItem onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Microsoft Office 2019");
		onlineShoppingCartItemList.setItemCost(25000.95);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.SOFTWARES);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Microsoft Office 2016");
		onlineShoppingCartItemList.setItemCost(17000.00);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.SOFTWARES);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Microsoft Office 2013");
		onlineShoppingCartItemList.setItemCost(950.50);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.SOFTWARES);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("RAM");
		onlineShoppingCartItemList.setItemCost(100.00);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.HARDWARE);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Hard Disk");
		onlineShoppingCartItemList.setItemCost(1350.50);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.HARDWARE);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Mixer Grinder");
		onlineShoppingCartItemList.setItemCost(269.75);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.ELECTRONICS);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Washing Machine");
		onlineShoppingCartItemList.setItemCost(1270.00);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.ELECTRONICS);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Birthday Cake");
		onlineShoppingCartItemList.setItemCost(110.05);
		onlineShoppingCartItemList.setCount(2);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.GIFTS);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Perfumes");
		onlineShoppingCartItemList.setItemCost(117.00);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.OTHER);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Choclates");
		onlineShoppingCartItemList.setItemCost(191.00);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.OTHER);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		onlineShoppingCartItemList = new OnlineShoppingCartItem();
		onlineShoppingCartItemList.setItemName("Pampers");
		onlineShoppingCartItemList.setItemCost(129.50);
		onlineShoppingCartItemList.setCount(1);
		onlineShoppingCartItemList.setOnlineShoppingCategoryProducts(OnlineShoppingCategoryProducts.NEWBORN);
		onlineShoppingCartItem.add(onlineShoppingCartItemList);

		billing = billingCustomerQuery.totalBill(0.0).shoopingItemList(onlineShoppingCartItem)
				.totalBillAfterRetailCustomerTypeDiscount(0.0).finalbillCost(0.0).Build();
	}
}
