package it.polito.ezshop.Tests.BB.SaleTransactionList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.model.SaleTransactionList;

public class SaleTransactionListTestCloseSale {

	private SaleTransactionList saleLst = null;
	private Integer idx = null;

	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		saleLst = new SaleTransactionList();
		idx = saleLst.addSale();
	}
	
	@Test
	public void testNullSaleId() {
		assertThrows(InvalidTransactionIdException.class, () -> {saleLst.closeSale(null);} );
	}
	
	@Test
	public void testLessOrEqualToZeroSaleId() {
		assertThrows(InvalidTransactionIdException.class, () -> {saleLst.closeSale(0);} );
	}
	
	@Test
	public void testNotExistingSaleId() throws InvalidTransactionIdException {
		assertFalse(saleLst.closeSale(idx + 1));
	}
	
	@Test
	public void testSuccesfulClosedSale() throws InvalidTransactionIdException {
		assertTrue(saleLst.closeSale(idx));
	}
	
	@Test
	public void testAlreadyClosedSale() throws InvalidTransactionIdException {
		saleLst.closeSale(idx);
		assertFalse(saleLst.closeSale(idx));
	}
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
