package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;

public class EZShopTestCreateUser {

	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";

	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		ez.reset();
	}

	@Test
	public void testSuccesfulCreateAdmin()
			throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		assertTrue(ez.createUser(fakeAdmin, psw, "Administrator") == 1);
	}

	@Test
	public void testSuccesfulCreateShopManager()
			throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		assertTrue(ez.createUser(fakeShopManager, psw, "Administrator") == 1);
	}
	
	@Test
	public void testSuccesfulCreateCashier()
			throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		assertTrue(ez.createUser(fakeCashier, psw, "Administrator") == 1);
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}
