package it.polito.ezshop.Tests.BB.LoyaltyCardList;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.model.LoyaltyCardList;

public class LoyaltyCardListTestAddLoyaltyCard {

	private LoyaltyCardList cardLst = null;

	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		cardLst = new LoyaltyCardList();
	}
	
	@Test
	public void testAddLoyaltyCard() {
		assertTrue(cardLst.addLoyaltyCard().matches("\\d{10}"));
	}
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
