package it.polito.ezshop.Tests.WB;

import org.junit.Test;

import it.polito.ezshop.model.Customer;

import static org.junit.Assert.assertTrue;

public class CustomerTest {

	@SuppressWarnings("unused")
	@Test
	public void testConstuctorsSettersGetters() {

		Integer id = 1000;
		String name = "Mark";
		String card = "zzzzzzz";
		Integer points = 1000;
		Customer c = new Customer(999, "Luke");
		Customer c1 = new Customer(id, name, card, points);

		c.setId(id);
		c.setCustomerName(name);
		c.setCustomerCard(card);
		c.setPoints(points);

		assertTrue(c.getId() == id && c.getCustomerName() == name && c.getCustomerCard() == card
				&& c.getPoints() == points);
	}
}
