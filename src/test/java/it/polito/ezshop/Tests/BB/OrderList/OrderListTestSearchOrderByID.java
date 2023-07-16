package it.polito.ezshop.Tests.BB.OrderList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidOrderIdException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.model.OrderList;

public class OrderListTestSearchOrderByID {
	
	private OrderList ol = null;
	
	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		ol = new OrderList();
	}

	@Test
	public void testNullOrderID() {
		assertThrows(InvalidOrderIdException.class, () -> {
			ol.searchOrderByID(null);
		});
	}

	@Test
	public void testNonPositiveTransactionID() {
		assertThrows(InvalidOrderIdException.class, () -> {
			ol.searchOrderByID(-1);
		});
		assertThrows(InvalidOrderIdException.class, () -> {
			ol.searchOrderByID(0);
		});
	}

	@Test
	public void testMissingID() throws InvalidOrderIdException {
		assertNull(ol.searchOrderByID(5));
	}
	
	@Test
	public void testSearch() throws InvalidQuantityException, InvalidPricePerUnitException, InvalidOrderIdException {
		int id = ol.addOrder(1.0, "a", 1.0, 1, "COMPLETED");
		assertFalse(ol.searchOrderByID(id) == null);
	} 
	
	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}

}
