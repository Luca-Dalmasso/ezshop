package it.polito.ezshop.Tests.BB.AccountBook;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.model.AccountBook;
import it.polito.ezshop.model.BalanceOperation;

public class AccountBookTestGetAccountingList {
	
	private AccountBook account = null;
	
	@Before
	public void beforeEachTest() throws InvalidCustomerNameException {
		SQLiteJDBC.reset();
		account = new AccountBook();
	}
    
	@Test
	public void testEmptyAccountingList() {
		assertTrue(account.getAccountingList().isEmpty());
	}
	
	@Test
	public void testAccountList() throws InvalidCustomerNameException {
		
		account.addBalanceOperation(new BalanceOperation("SALE", 3.0));
		account.addBalanceOperation(new BalanceOperation("RETURN", 3.0));
		assertTrue(account.getAccountingList().size() == 2);
	}
    
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
