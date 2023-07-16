package it.polito.ezshop.Tests.BB.CustomerList;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.model.CustomerList;

public class CustomerListTestGetCustomerList {

	private CustomerList customers = null;

	@Before
	public void beforeEachTest() throws InvalidCustomerNameException {
		SQLiteJDBC.reset();
		customers = new CustomerList();
	}

	@Test
	public void testEmptyCustomerList() {
		assertTrue(customers.getCustomerList().isEmpty());
	}

	@Test
	public void testCustomerList() throws InvalidCustomerNameException {
		customers.addCustomer("Mark");
		customers.addCustomer("Robert");
		assertTrue(customers.getCustomerList().size() == 2);
	}

	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
