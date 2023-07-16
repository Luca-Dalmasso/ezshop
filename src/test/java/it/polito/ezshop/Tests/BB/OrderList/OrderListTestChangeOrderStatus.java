package it.polito.ezshop.Tests.BB.OrderList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidOrderIdException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.model.OrderList;

public class OrderListTestChangeOrderStatus {
	
	private OrderList ol = null;
	
	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		ol = new OrderList();
	}

	@Test
	public void testMissingOrderID() throws InvalidOrderIdException {
		assertFalse(ol.changeOrderStatus("status", 5));
	}

	@Test
	public void testChangeStatus() throws InvalidQuantityException, InvalidPricePerUnitException, InvalidOrderIdException {
		int id = ol.addOrder(1.0, "a", 1.0, 1, "COMPLETED");
		assertTrue(ol.changeOrderStatus("PAYED", id));
	}
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}

}
