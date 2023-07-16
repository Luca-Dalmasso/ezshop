package it.polito.ezshop.Tests.BB.CustomerList;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCustomerIdException;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.model.Customer;
import it.polito.ezshop.model.CustomerList;

public class CustomerListTestGetCustomer {

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
			customers.getCustomer(null);
		});
	}

	@Test
	public void testLessOrEqualToZeroCustomerId() {
		assertThrows(InvalidCustomerIdException.class, () -> {
			customers.getCustomer(0);
		});
	}

	@Test
	public void testNotInListCustomerId() throws InvalidCustomerIdException {
		assertTrue(customers.getCustomer(1000) == null);
	}

	@Test
	public void testSuccessfulGetCustomer() throws InvalidCustomerIdException {
		assertTrue(Customer.class.isInstance(customers.getCustomer(1)));
	}

	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
