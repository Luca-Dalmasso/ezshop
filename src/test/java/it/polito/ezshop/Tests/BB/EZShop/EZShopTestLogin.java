package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;

public class EZShopTestLogin {

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
		// ez.login(fakeShopManager, psw);
		// ez.login(fakeCashier, psw);
	}

	@Test
	public void testNotExistingUser() throws InvalidUsernameException, InvalidPasswordException {
		assertTrue(ez.login(fakeAdmin, "wrongpsw") == null);
	}

	@Test
	public void testSuccesfulLogin() throws InvalidUsernameException, InvalidPasswordException {
		assertTrue(ez.login(fakeAdmin, psw).getUsername().equals(fakeAdmin));
	}

	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}
