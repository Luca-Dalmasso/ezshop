package it.polito.ezshop.Tests.BB.AccountBook;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.model.AccountBook;

public class AccountBookSetBalance {
private AccountBook account = null;
	
	@Before
	public void beforeEachTest() throws InvalidCustomerNameException {
		SQLiteJDBC.reset();
		account = new AccountBook();
	}
	
	@Test
	public void testSetBalancezero() {
		account.setBalance(15.0);
		assertEquals(15, account.getBalance(), 0.01);
	}
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
