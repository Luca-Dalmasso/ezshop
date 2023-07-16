package it.polito.ezshop.Tests.BB.OrderList;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.model.OrderList;

public class OrderListTestSearchOrderByFields {
	
	private OrderList ol = null;
	
	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		ol = new OrderList();
	}

	@Test
	public void testNonPositiveQuantity() {
		assertThrows(InvalidQuantityException.class, () -> {
			ol.searchOrderByFields("a", 0, 1.0);
		});
		assertThrows(InvalidQuantityException.class, () -> {
			ol.searchOrderByFields("a", -1, 1.0);
		});
	}

	@Test
	public void testNonPositivePrice() {
		assertThrows(InvalidPricePerUnitException.class, () -> {
			ol.searchOrderByFields("a", 1, 0.0);
		});
		assertThrows(InvalidPricePerUnitException.class, () -> {
			ol.searchOrderByFields("a", 1, -1.0);
		});
	}

	@Test
	public void testMissingOrder() throws InvalidPricePerUnitException, InvalidQuantityException {
		assertNull(ol.searchOrderByFields("a", 1, 1.0));
		ol.addOrder(1.0, "a", 1.0, 1, "COMPLETED");
		assertNull(ol.searchOrderByFields("b", 1, 1.0));
	}

	@Test
	public void testSearch() throws InvalidQuantityException, InvalidPricePerUnitException {
		ol.addOrder(1.0, "a", 1.0, 1, "COMPLETED");
		assertTrue(ol.searchOrderByFields("a", 1, 1.0) != null);
	} 
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}

}
