package it.polito.ezshop.Tests.WB.SaleTransaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.model.SaleTransaction;

public class SaleTransactionTestSetDiscountToProduct {

	private SaleTransaction sale = null;

	@Before
	public void beforeEachTest() {
		sale = new SaleTransaction();
		sale.addProduct("thisisabarcode", "Tomato", 0.5, 10);
	}
	
	@Test
	public void testDiscountProduct() {
		Boolean res = sale.setDiscountToProduct("thisisabarcode", 0.6);
		assertTrue(res && sale.getPrice()==2);
	}
	
	@Test
	public void testDiscountProductWithAGlobalDiscount() {
		sale.setDiscountRate(0.5);
		Boolean res = sale.setDiscountToProduct("thisisabarcode", 0.6);
		assertTrue(res && sale.getPrice()==1);
	}
	
	@Test
	public void testDiscountProductNotInEntries() {
		assertFalse(sale.setDiscountToProduct("thisisanotherbarcode", 0.5));
	}
}
