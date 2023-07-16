package it.polito.ezshop.Tests.BB.CreditCardList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCreditCardException;
import it.polito.ezshop.model.CreditCardList;

public class TestCheckBalance{
	
	
	
private CreditCardList clist;
	
	@Before
	public void init() {
		SQLiteJDBC.reset();
		clist = new CreditCardList();
	}
	
	@Test
	public void testNullCode() throws InvalidCreditCardException{
		assertThrows(InvalidCreditCardException.class, () -> { clist.checkBalance(null, 1.00); } );
	}
	
	@Test
	public void testEmptyCode() throws InvalidCreditCardException{
		assertThrows(InvalidCreditCardException.class, () -> { clist.checkBalance("", 1.00); } );
	}
	
	@Test
	public void testInvalidChecksumCode() throws InvalidCreditCardException{
		assertThrows(InvalidCreditCardException.class, () -> { clist.checkBalance("79927398710",1.00); } );
		assertThrows(InvalidCreditCardException.class, () -> { clist.checkBalance("79927398711",1.00); } );
		assertThrows(InvalidCreditCardException.class, () -> { clist.checkBalance("79927398719",1.00); } );
	}
	
	@Test
	public void testNullCost() throws InvalidCreditCardException {
		assertFalse(clist.checkBalance("5100293991053009", null));
	}
	
	
	@Test
	public void testValidBalanceCheck() throws InvalidCreditCardException {
		assertTrue(clist.checkBalance("5100293991053009",10.00));
		assertFalse(clist.checkBalance("4716258050958645",1.00));
		assertTrue(clist.checkBalance("4485370086510891",150.00));
		assertFalse(clist.checkBalance("4485370086510891",151.00));
	}
	
	
	

}
