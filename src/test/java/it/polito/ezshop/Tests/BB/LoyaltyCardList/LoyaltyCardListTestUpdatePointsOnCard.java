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

public class LoyaltyCardListTestUpdatePointsOnCard {

	private LoyaltyCardList cardLst = null;

	@Before
	public void beforeEachTest() throws InvalidCustomerCardException {
		SQLiteJDBC.reset();
		cardLst = new LoyaltyCardList();
		cardLst.addLoyaltyCard("9876543210");
	}
	
	@Test
	public void testNullCardId() {
		assertThrows(InvalidCustomerCardException.class, () -> {cardLst.updatePointsOnCard(null, 100);} );
	}
	
	@Test
	public void testEmptyCardId() {
		assertThrows(InvalidCustomerCardException.class, () -> {cardLst.updatePointsOnCard("", 100);} );
	}
	
	@Test
	public void testWrongCardId() {
		assertThrows(InvalidCustomerCardException.class, () -> {cardLst.updatePointsOnCard("thisisnotacorrectformat", 100);} );
	}
	
	@Test
	public void testNotInListCardId() throws InvalidCustomerCardException {
		assertFalse(cardLst.updatePointsOnCard("1234567890", 100));
	}
	
	@Test
	public void testNegativePointsInCaseOfUpdate() throws InvalidCustomerCardException {
		assertFalse(cardLst.updatePointsOnCard("9876543210", -100));
	}
	
	@Test
	public void testSuccessfulUpdatePointsOfCard() throws InvalidCustomerCardException {
		assertTrue(cardLst.updatePointsOnCard("9876543210", 100));
	}
	
	@Test
	public void testSuccessfulUpdateWithNegativePoints() throws InvalidCustomerCardException {
		cardLst.updatePointsOnCard("9876543210", 100);
		assertTrue(cardLst.updatePointsOnCard("9876543210", -90));
	}
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
