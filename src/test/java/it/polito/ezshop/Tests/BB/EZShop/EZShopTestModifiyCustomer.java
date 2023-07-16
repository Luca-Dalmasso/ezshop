package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidCustomerCardException;
import it.polito.ezshop.exceptions.InvalidCustomerIdException;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestModifiyCustomer {
	
	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		//ez.login(fakeShopManager, psw);
		//ez.login(fakeCashier, psw);
	}
	
	@Test
	public void testNullCustomer() throws InvalidCustomerNameException, InvalidCustomerCardException, InvalidCustomerIdException, UnauthorizedException {
		assertFalse(ez.modifyCustomer(9, "Goofy", "9999999999"));		
	}
	
	@Test
	public void testInvalidCard() throws InvalidCustomerNameException, InvalidCustomerCardException, InvalidCustomerIdException, UnauthorizedException, InvalidUsernameException, InvalidPasswordException {
		int id = ez.defineCustomer("Mickey");
		int id2 = ez.defineCustomer("Mouse");
		ez.logout();
		ez.login(fakeCashier, psw);
		String card = ez.createCard();
		String card2 = ez.createCard();
		ez.attachCardToCustomer(card2, id2);
		assertFalse(ez.modifyCustomer(id, "Goofy", card));
		assertFalse(ez.modifyCustomer(id, "Goofy", card2));	
	}
	
	@Test
	public void testModify() throws InvalidCustomerNameException, UnauthorizedException, InvalidCustomerCardException, InvalidCustomerIdException, InvalidUsernameException, InvalidPasswordException {
		int id = ez.defineCustomer("Mickey");
		assertTrue(ez.modifyCustomer(id, "Goofy", ""));
		assertTrue(ez.modifyCustomer(id, "Goofy", null));
		assertTrue(ez.modifyCustomer(id, "Goofy", "5555555555"));
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}
