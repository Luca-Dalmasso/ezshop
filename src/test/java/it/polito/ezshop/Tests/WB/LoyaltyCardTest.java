package it.polito.ezshop.Tests.WB;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import it.polito.ezshop.model.LoyaltyCard;

public class LoyaltyCardTest {

	@Test
	public void testConstuctorsSettersGetters() {

		String id = "8624339451";
		Integer points = 1200;
		LoyaltyCard c = new LoyaltyCard(id, 1000);

		c.setPoints(points);

		assertTrue(c.getPoints() == points && c.getCardID() == id);
	}
}
