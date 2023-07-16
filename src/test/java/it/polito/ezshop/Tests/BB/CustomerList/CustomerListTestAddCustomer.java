package it.polito.ezshop.Tests.BB.CustomerList;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.model.CustomerList;

public class CustomerListTestAddCustomer {

	private CustomerList customers = null;

	@Before
	public void beforeEachTest() {
		SQLiteJDBC.reset();
		customers = new CustomerList();
	}

	@Test
	public void testNullCustomerName() {
		assertThrows(InvalidCustomerNameException.class, () -> {
			customers.addCustomer(null);
		});
	}

	@Test
	public void testEmptyCustomerName() {
		assertThrows(InvalidCustomerNameException.class, () -> {
			customers.addCustomer("");
		});
	}

	@Test
	public void testFirstIndexOfCustomer() throws InvalidCustomerNameException {
		assertTrue(customers.addCustomer("Mark") == 1);
	}

	@Test
	public void testNIndexOfCustomer() throws InvalidCustomerNameException {
		Integer idx, res;
		idx = res = new Random().nextInt(100) + 1;
		while ((idx--) > 1)
			customers.addCustomer("Mark" + idx);
		assertTrue(customers.addCustomer("Mark0") == res);

	}

	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
