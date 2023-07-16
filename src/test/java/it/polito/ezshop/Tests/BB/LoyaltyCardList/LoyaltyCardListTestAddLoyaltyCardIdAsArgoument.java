package it.polito.ezshop.Tests.BB.LoyaltyCardList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCustomerCardException;
import it.polito.ezshop.model.LoyaltyCardList;

public class LoyaltyCardListTestAddLoyaltyCardIdAsArgoument {

	private LoyaltyCardList cardLst = null;

	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		cardLst = new LoyaltyCardList();
	}
	
	@Test
	public void testNullCardId() {
		assertThrows(InvalidCustomerCardException.class, () -> {cardLst.addLoyaltyCard(null);} );
	}
	
	@Test
	public void testEmptyCardId() {
		assertThrows(InvalidCustomerCardException.class, () -> {cardLst.addLoyaltyCard("");} );
	}
	
	@Test
	public void testWrongCardId() {
		assertThrows(InvalidCustomerCardException.class, () -> {cardLst.addLoyaltyCard("thisisnotacorrectformat");} );
	}
	
	@Test
	public void testAlreadyAddedCardId() throws InvalidCustomerCardException {
		cardLst.addLoyaltyCard("1234567890");
		assertFalse(cardLst.addLoyaltyCard("1234567890"));
	}
	
	@Test
	public void testSuccessfulAddCardId() throws InvalidCustomerCardException {
		assertTrue(cardLst.addLoyaltyCard("1234567890"));
	}
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
