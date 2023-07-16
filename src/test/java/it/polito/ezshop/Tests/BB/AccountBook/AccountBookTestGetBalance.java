package it.polito.ezshop.Tests.BB.AccountBook;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.model.AccountBook;
import it.polito.ezshop.model.BalanceOperation;

public class AccountBookTestGetBalance {
       
private AccountBook account = null;
	
	@Before
	public void beforeEachTest() throws InvalidCustomerNameException {
		SQLiteJDBC.reset();
		account = new AccountBook();
	}
	
	@Test
	public void testBalancezero() {
		BalanceOperation b = new BalanceOperation("SALE", 15.0);
		account.addBalanceOperation(b);
		double bal = account.getBalance();
		assertEquals(15.0, bal, 0.01);
	}
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
