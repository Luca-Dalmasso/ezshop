package it.polito.ezshop.Tests.BB.CustomerList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCustomerIdException;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.model.CustomerList;

public class CustomerListTestDeleteCustomer {

	private CustomerList customers = null;

	@Before
	public void beforeEachTest() throws InvalidCustomerNameException {
		SQLiteJDBC.reset();
		customers = new CustomerList();
		customers.addCustomer("Mark");
	}

	@Test
	public void testNullCustomerId() {
		assertThrows(InvalidCustomerIdException.class, () -> {
			customers.deleteCustomer(null);
		});
	}

	@Test
	public void testLessOrEqualToZeroCustomerId() {
		assertThrows(InvalidCustomerIdException.class, () -> {
			customers.deleteCustomer(0);
		});
	}

	@Test
	public void testNotInListCustomerId() throws InvalidCustomerIdException {
		assertFalse(customers.deleteCustomer(1000));
	}

	@Test
	public void testSuccessfulDeleteOfCustomer() throws InvalidCustomerIdException {
		assertTrue(customers.deleteCustomer(1));
	}

	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
