package it.polito.ezshop.Tests.BB.ReturnTransactionList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;

import it.polito.ezshop.model.ReturnTransactionList;

public class ReturnTransactionListTestAddReturn {
	
	private ReturnTransactionList rtl = null;
	
	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		rtl = new ReturnTransactionList();
	}

	@Test
	public void testNullTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.addReturn(null);
		});
	}

	@Test
	public void testNegativeTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.addReturn(-1);
		});
	}

	@Test
	public void testZeroTransactionID() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			rtl.addReturn(0);
		});
	}

	@Test
	public void testID() throws InvalidTransactionIdException {
		assertEquals(1, (int) rtl.addReturn(1));
	} 
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}

}
