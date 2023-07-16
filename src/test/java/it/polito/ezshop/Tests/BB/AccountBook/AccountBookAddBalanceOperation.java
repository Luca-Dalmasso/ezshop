package it.polito.ezshop.Tests.BB.AccountBook;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.model.AccountBook;
import it.polito.ezshop.model.BalanceOperation;


public class AccountBookAddBalanceOperation {
	
	private AccountBook account = null;

	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		account = new AccountBook();
	}

	/*
	 * @Test public void testUnknowOperation(){ BalanceOperation b = new
	 * BalanceOperation("PEPE", 3.0); assertFalse(account.addBalanceOperation(b));
	 * 
	 * }
	 */
	
	@Test
	public void testFalseOperation1(){
		BalanceOperation b = new BalanceOperation("RETURN", 15.0);
		assertFalse(account.addBalanceOperation(b));
		
	}
   
	@Test
	public void testRightBalanceOp() {
		BalanceOperation b = new BalanceOperation("SALE", 3.0);
		assertTrue(account.addBalanceOperation(b));
	}

	@Test
	public void testNegativeBalance() throws InvalidQuantityException, InvalidPricePerUnitException {
		BalanceOperation b = new BalanceOperation("SALE", 3.0);
		account.addBalanceOperation(b);
		account.addBalanceOperation(new BalanceOperation("RETURN", 1.0));
		assertFalse(account.deleteBalanceOperation(b.getBalanceId()));
	}

	@Test
	public void testComputeBalance() throws InvalidQuantityException, InvalidPricePerUnitException {
		account.addBalanceOperation(new BalanceOperation("SALE", 3.0));
		account.addBalanceOperation(new BalanceOperation("RETURN", 1.0));
		AccountBook ab = new AccountBook();
		assertTrue(ab.getBalance() == 2.0);
	}

	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}

}
