package it.polito.ezshop.Tests.BB.SaleTransactionList;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.model.SaleTransaction;
import it.polito.ezshop.model.SaleTransactionList;

public class SaleTransactionListTestSearchSale {

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
		assertThrows(InvalidTransactionIdException.class, () -> {
			saleLst.searchSale(null);
		});
	}

	@Test
	public void testLessOrEqualToZeroSaleId() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			saleLst.searchSale(0);
		});
	}

	@Test
	public void testNotExistingSaleId() throws InvalidTransactionIdException {
		assertTrue(saleLst.searchSale(idx + 1) == null);
	}

	@Test
	public void testSuccessfulSearchSale() throws InvalidTransactionIdException {
		assertTrue(SaleTransaction.class.isInstance(saleLst.searchSale(idx)));
	}

	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
