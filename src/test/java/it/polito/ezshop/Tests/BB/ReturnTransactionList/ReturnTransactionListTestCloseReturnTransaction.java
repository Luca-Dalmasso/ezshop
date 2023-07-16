package it.polito.ezshop.Tests.BB.ReturnTransactionList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.model.ReturnTransactionList;

public class ReturnTransactionListTestCloseReturnTransaction {
	
	private ReturnTransactionList rtl = null;
	
	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		rtl = new ReturnTransactionList();
	}


	@Test
	public void testNullTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.closeReturnTransaction(null);
		});
	}

	@Test
	public void testZeroTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.closeReturnTransaction(0);
		});
	}

	@Test
	public void testNegativeTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.closeReturnTransaction(-1);
		});
	}

	@Test
	public void testMissingTransactionID() throws InvalidTransactionIdException {
		assertFalse(rtl.closeReturnTransaction(5));
	}

	@Test
	public void testCloseTransactionID() throws InvalidTransactionIdException {
		int id = rtl.addReturn(1);
		rtl.searchReturnTransaction(id).setProductCode("code");;
		assertTrue(rtl.closeReturnTransaction(1)); 
	}
	
	@AfterClass
	public static void finalresetDB() {
		SQLiteJDBC.reset();
	}

}
