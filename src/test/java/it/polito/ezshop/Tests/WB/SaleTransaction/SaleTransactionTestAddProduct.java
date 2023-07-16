package it.polito.ezshop.Tests.WB.SaleTransaction;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.model.SaleTransaction;

public class SaleTransactionTestAddProduct {

	private SaleTransaction sale = null;
	@Before
	public void beforeEachTest() {
		sale = new SaleTransaction();
	}
	
	@Test
	public void testAddProductToEmptyEntries() {
		sale.addProduct("thisisabarcode", "Tomato", 0.5, 10);
		assertTrue(sale.getPrice()==5.0);
	}
	
	@Test
	public void testAddProductAlreadyInEntries() {
		sale.addProduct("thisisabarcode", "Tomato", 0.5, 10);
		sale.addProduct("thisisabarcode", "Tomato", 0.5, 20);
		assertTrue(sale.getPrice()==15.0);
	}
}
