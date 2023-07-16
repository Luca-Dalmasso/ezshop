package it.polito.ezshop.Tests.BB.OrderList;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.model.OrderList;

public class OrderListTestGetAllOrders {
	
	private OrderList ol = null;
	
	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		ol = new OrderList();
	}

	@Test
	public void testGetOrders() throws InvalidQuantityException, InvalidPricePerUnitException {
		assertEquals(0, ol.getAllOrders().size());
		ol.addOrder(1.0, "a", 1.0, 1, "COMPLETED");
		assertEquals(1, ol.getAllOrders().size());
		ol.addOrder(1.0, "b", 1.0, 1, "PAYED");
		assertEquals(2, ol.getAllOrders().size());
	}
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}

}
