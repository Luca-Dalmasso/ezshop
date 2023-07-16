package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestReset {
	
	private static EZShop ez = new EZShop();

	@Test
	public void testReset() throws UnauthorizedException, InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		ez.reset();
		String fakeAdmin = "fakeadmin";
		String psw = "fakepsw";
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.login(fakeAdmin, psw);
		assertTrue(ez.getAllUsers().size() == 1);
		assertTrue(ez.getAllProductTypes().isEmpty());
		assertTrue(ez.getAllOrders().isEmpty());
		assertTrue(ez.getAllCustomers().isEmpty());
		assertTrue(ez.getCreditsAndDebits(null, null).isEmpty());
	}

}
