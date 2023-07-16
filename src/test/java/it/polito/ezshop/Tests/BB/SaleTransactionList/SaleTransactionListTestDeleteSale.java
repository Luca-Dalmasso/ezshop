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

public class SaleTransactionListTestDeleteSale {

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
			saleLst.deleteSale(null);
		});
	}

	@Test
	public void testLessOrEqualToZeroSaleId() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			saleLst.deleteSale(0);
		});
	}

	@Test
	public void testNotExistingSaleId() throws InvalidTransactionIdException {
		assertFalse(saleLst.deleteSale(idx + 1));
	}

	@Test
	public void testSuccesfulDeleteOpenedSale() throws InvalidTransactionIdException {
		assertTrue(saleLst.deleteSale(idx));
	}

	@Test
	public void testSuccesfulDeleteClosedSale() throws InvalidTransactionIdException {
		saleLst.closeSale(idx);
		assertTrue(saleLst.deleteSale(idx));
	}

	@Test
	public void testDeletePayedSale() throws InvalidTransactionIdException {
		saleLst.closeSale(idx);
		saleLst.getClosedSale(idx).setState("PAYED");
		assertFalse(saleLst.deleteSale(idx));
	}

	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
