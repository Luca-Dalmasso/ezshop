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

public class LoyaltyCardListTestDeleteLoyaltyCard {

	private LoyaltyCardList cardLst = null;

	@Before
	public void beforeEachTest() throws InvalidCustomerCardException {
		SQLiteJDBC.reset();
		cardLst = new LoyaltyCardList();
		cardLst.addLoyaltyCard("9876543210");
	}
	
	@Test
	public void testNullCardId() {
		assertThrows(InvalidCustomerCardException.class, () -> {cardLst.deleteLoyaltyCard(null);} );
	}
	
	@Test
	public void testEmptyCardId() {
		assertThrows(InvalidCustomerCardException.class, () -> {cardLst.deleteLoyaltyCard("");} );
	}
	
	@Test
	public void testWrongCardId() {
		assertThrows(InvalidCustomerCardException.class, () -> {cardLst.deleteLoyaltyCard("thisisnotacorrectformat");} );
	}
	
	@Test
	public void testNotInListCardId() throws InvalidCustomerCardException {
		assertFalse(cardLst.deleteLoyaltyCard("1234567890"));
	}
	
	@Test
	public void testSuccessfulDelete() throws InvalidCustomerCardException {
		assertTrue(cardLst.deleteLoyaltyCard("9876543210"));
	}
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
