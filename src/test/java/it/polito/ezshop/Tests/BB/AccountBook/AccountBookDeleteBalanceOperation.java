package it.polito.ezshop.Tests.BB.AccountBook;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.model.AccountBook;
import it.polito.ezshop.model.BalanceOperation;

public class AccountBookDeleteBalanceOperation {
	private AccountBook account = null;

	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		
		account = new AccountBook();
		BalanceOperation b = new BalanceOperation("SALE", 10.0);
		
		account.addBalanceOperation(b);
		
	}
    
	@Test
	public void testDeleteById() {
		
		assertTrue(account.deleteBalanceOperation(1));
	}
	
	@Test
	public void testDeleteByIdFalse() {
		
		assertFalse(account.deleteBalanceOperation(1000));
	}
	
	@Test
	public void testDeleteById2() {
		BalanceOperation b1 = new BalanceOperation("RETURN", 5.0);
		account.addBalanceOperation(b1);
		assertTrue(account.deleteBalanceOperation(2));
	}
	
	
}
