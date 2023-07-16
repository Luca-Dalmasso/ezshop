package it.polito.ezshop.Tests.BB.ReturnTransactionList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.model.ReturnTransactionList;

public class ReturnTransactionListTestSearchReturnTransaction {
	
	private ReturnTransactionList rtl = null;
	
	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		rtl = new ReturnTransactionList();
	}

	@Test
	public void testNullTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.searchReturnTransaction(null);
		});
	}

	@Test
	public void testNegativeTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.searchReturnTransaction(-1);
		});
	}

	@Test
	public void testZeroTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.searchReturnTransaction(0);
		});
	}

	@Test
	public void testMissingTransactionID() throws InvalidTransactionIdException {
		assertNull(rtl.searchReturnTransaction(5));
	}

	@Test
	public void testSearchTransactionID() throws InvalidTransactionIdException {
		rtl.addReturn(2);
		assertEquals(1, rtl.searchReturnTransaction(1).getBalanceId());
		assertEquals(2, (int) rtl.searchReturnTransaction(1).getSaleTransactionID());
	}

	@AfterClass
	public static void finalresetDB() {
		SQLiteJDBC.reset();
	}

}
