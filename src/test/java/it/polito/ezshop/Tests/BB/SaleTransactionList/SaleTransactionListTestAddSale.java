package it.polito.ezshop.Tests.BB.SaleTransactionList;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.model.BalanceOperation;
import it.polito.ezshop.model.SaleTransaction;
import it.polito.ezshop.model.SaleTransactionList;

public class SaleTransactionListTestAddSale {

	private SaleTransactionList saleLst = null;

	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		saleLst = new SaleTransactionList();
	}

	@Test
	public void testFirstIndexOfSale() {
		assertTrue(saleLst.addSale() == 1);
	}

	@Test
	public void testNIndexOfSale() {
		Integer idx, res;
		idx = res = new Random().nextInt(100) + 1;
		while ((idx--) > 1)
			saleLst.addSale();
		assertTrue(saleLst.addSale() == res);

	}

	@Test
	public void testIndexAfterFakeDBInit() throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Integer idx = 1000;
		BalanceOperation.setIdCounter(idx);
		SaleTransaction.setTicketNumberCounter(idx);
		assertTrue(saleLst.addSale() == idx + 1);
	}

	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
