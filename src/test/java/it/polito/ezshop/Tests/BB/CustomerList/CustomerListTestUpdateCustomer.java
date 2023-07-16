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
import it.polito.ezshop.model.Customer;
import it.polito.ezshop.model.CustomerList;

public class CustomerListTestUpdateCustomer {

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
			customers.updateCustomer(null, "Mark", null);
		});
	}

	@Test
	public void testLessOrEqualToZeroCustomerId() {
		assertThrows(InvalidCustomerIdException.class, () -> {
			customers.updateCustomer(0, "Mark", null);
		});
	}

	@Test
	public void testNotInListCustomerId() throws InvalidCustomerIdException, InvalidCustomerNameException {
		assertFalse(customers.updateCustomer(1000, "Mark", null));
	}

	@Test
	public void testNullCustomerName() {
		assertThrows(InvalidCustomerNameException.class, () -> {
			customers.updateCustomer(1, null, null);
		});
	}

	@Test
	public void testEmptyCustomerName() {
		assertThrows(InvalidCustomerNameException.class, () -> {
			customers.updateCustomer(1, "", null);
		});
	}

	@Test
	public void testUpdateOnlyName() throws InvalidCustomerNameException, InvalidCustomerIdException {
		String name = "Robert";
		customers.updateCustomer(1, name, null);
		Customer c = customers.getCustomer(1);
		assertTrue(c.getCustomerName().equals(name) && c.getCustomerCard() == null && c.getPoints() == 0);
	}

	@Test
	public void testUpdateNameAndDeleteCard() throws InvalidCustomerIdException, InvalidCustomerNameException {
		String name = "Robert";
		Customer c = customers.getCustomer(1);
		c.setCustomerCard("1111111111");
		c.setPoints(1000);
		Boolean res = customers.updateCustomer(1, name, "");
		assertTrue(res && c.getCustomerName().equals(name) && c.getCustomerCard() == null && c.getPoints() == 0);
	}

	@Test
	public void testUpdateNameAndAddNewCard() throws InvalidCustomerIdException, InvalidCustomerNameException {
		String name = "Robert";
		String newCard = "2222222222";
		Customer c = customers.getCustomer(1);
		c.setCustomerCard("1111111111");
		c.setPoints(1000);
		Boolean res = customers.updateCustomer(1, name, newCard);
		assertTrue(
				res && c.getCustomerName().equals(name) && c.getCustomerCard().equals(newCard) && c.getPoints() == 0);
	}

	@AfterClass
	public static void finalDBreset() {
		SQLiteJDBC.reset();
	}
}
