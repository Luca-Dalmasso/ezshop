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

public class CustomerListTestAttachCustomerCard {

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
			customers.attachCustomerCard(null, "1111111111");
		});
	}

	@Test
	public void testLessOrEqualToZeroCustomerId() {
		assertThrows(InvalidCustomerIdException.class, () -> {
			customers.attachCustomerCard(-1, "1111111111");
		});
	}

	@Test
	public void testNotInListCustomerId() throws InvalidCustomerIdException {
		assertFalse(customers.attachCustomerCard(1000, "1111111111"));
	}

	@Test
	public void testSuccessfulAttachOfCardToCustomer() throws InvalidCustomerIdException {
		assertTrue(customers.attachCustomerCard(1, "1111111111"));
	}

	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
