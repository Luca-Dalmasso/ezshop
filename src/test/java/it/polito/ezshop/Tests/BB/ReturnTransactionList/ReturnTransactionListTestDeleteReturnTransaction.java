package it.polito.ezshop.Tests.BB.ReturnTransactionList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.model.ReturnTransactionList;

public class ReturnTransactionListTestDeleteReturnTransaction {
	
	private ReturnTransactionList rtl = null;
	
	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		rtl = new ReturnTransactionList();
	}

	@Test
	public void testNullTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.deleteReturnTransaction(null);
		});
	}

	@Test
	public void testNegativeTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.deleteReturnTransaction(-1);
		});
	}

	@Test
	public void testZeroTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.deleteReturnTransaction(0);
		});
	}

	@Test
	public void testMissingTransactionID() throws InvalidTransactionIdException {
		assertFalse(rtl.deleteReturnTransaction(5));
	}

	@Test
	public void testDeleteTransactionID() throws InvalidTransactionIdException {
		rtl.addReturn(1);
		assertTrue(rtl.deleteReturnTransaction(1));
		assertNull(rtl.searchReturnTransaction(1));
	}
	
	@AfterClass
	public static void finalresetDB() {
		SQLiteJDBC.reset();
	}

}
