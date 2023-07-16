package it.polito.ezshop.Tests.BB.OrderList;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.model.OrderList;

public class OrderListTestAddOrder {
	
	private OrderList ol = null;
	
	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		ol = new OrderList();
	}

	@Test
	public void testNonPositiveQuantity() {
		assertThrows(InvalidQuantityException.class, () -> {
			ol.addOrder(0.0, "code", 0.0, 0, "status");
		});
		assertThrows(InvalidQuantityException.class, () -> {
			ol.addOrder(0.0, "code", 0.0, -1, "status");
		});
	}

	@Test
	public void testNonPositivePrice() {
		assertThrows(InvalidPricePerUnitException.class, () -> {
			ol.addOrder(0.0, "code", 0.0, 1, "status");
		});
		assertThrows(InvalidPricePerUnitException.class, () -> {
			ol.addOrder(0.0, "code", -1.0, 1, "status");
		});
	}

	@Test
	public void testAddOrder() throws InvalidQuantityException, InvalidPricePerUnitException {
		assertTrue(ol.addOrder(1.0, "code", 1.0, 1, "COMPLETED")>0);
	}
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}

}
