package it.polito.ezshop.Tests.WB.SaleTransaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.model.SaleTransaction;

public class SaleTransactionTestDeleteProduct {

	private SaleTransaction sale = null;

	@Before
	public void beforeEachTest() {
		sale = new SaleTransaction();
		sale.addProduct("thisisabarcode", "Tomato", 0.5, 10);
	}

	@Test
	public void testDeleteAllQuantityOfProductFromEntries() {
		Boolean res = sale.deleteProduct("thisisabarcode", 10);
		assertTrue(res && sale.getEntries().isEmpty() && sale.getPrice() == 0);
	}
	
	@Test
	public void testDeleteAPartOfProductFromEntries() {
		Boolean res = sale.deleteProduct("thisisabarcode", 5);
		assertTrue(res && !sale.getEntries().isEmpty() && sale.getPrice() == 2.5);
	}
	
	@Test
	public void testDeleteAPartOfProductFromEntriesWithADiscount() {
		sale.setDiscountToProduct("thisisabarcode", 0.1);
		Boolean res = sale.deleteProduct("thisisabarcode", 5);
		assertTrue(res && !sale.getEntries().isEmpty() && sale.getPrice() == 2.25);
	}
	
	@Test
	public void testDeleteQuantityOfProductTooHigh() {
		assertFalse(sale.deleteProduct("thisisabarcode", 15));
	}
	
	@Test
	public void testDeleteAProductNotInEntries() {
		assertFalse(sale.deleteProduct("thisisanotherbarcode", 5));
	}
}
