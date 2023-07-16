package it.polito.ezshop.Tests.BB.CreditCardList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCreditCardException;
import it.polito.ezshop.model.CreditCardList;

public class TestSearchCardByCode {
	
	private CreditCardList clist;
	
	@Before
	public void init() {
		SQLiteJDBC.reset();
		clist = new CreditCardList();
	}
	
	@Test
	public void testNullCode() throws InvalidCreditCardException{
		assertThrows(InvalidCreditCardException.class, () -> { clist.searchCardByCode(null); } );
	}
	
	@Test
	public void testEmptyCode() throws InvalidCreditCardException{
		assertThrows(InvalidCreditCardException.class, () -> { clist.searchCardByCode(""); } );
	}
	
	@Test
	public void testInvalidChecksumCode() throws InvalidCreditCardException{
		assertThrows(InvalidCreditCardException.class, () -> { clist.searchCardByCode("79927398710"); } );
		assertThrows(InvalidCreditCardException.class, () -> { clist.searchCardByCode("79927398711"); } );
		assertThrows(InvalidCreditCardException.class, () -> { clist.searchCardByCode("79927398719"); } );
	}
	
	@Test
	public void testValidCode() throws InvalidCreditCardException{
		assertEquals(clist.searchCardByCode("5100293991053009").getCreditCardCode(),"5100293991053009");
		assertEquals(clist.searchCardByCode("4716258050958645").getCreditCardCode(),"4716258050958645");
		assertEquals(clist.searchCardByCode("4485370086510891").getCreditCardCode(),"4485370086510891");
	}
	

}
