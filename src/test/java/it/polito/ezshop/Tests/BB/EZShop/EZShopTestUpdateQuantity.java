package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidProductIdException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestUpdateQuantity {
	
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
	public void testMissingProduct() throws InvalidProductIdException, UnauthorizedException {
		assertFalse(ez.updateQuantity(5, 5));
	}
	
	@Test
	public void testNegativeQuantity() throws InvalidProductIdException, UnauthorizedException, InvalidPricePerUnitException, InvalidProductDescriptionException, InvalidProductCodeException {
		int id = ez.createProductType("milk", "012345000010", 1.0, "bio");
		assertFalse(ez.updateQuantity(id, -1));
	}
	
	@Test
	public void testUpdateQuantity() throws InvalidProductIdException, UnauthorizedException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException {
		int id = ez.createProductType("milk", "012345000010", 1.0, "bio");
		assertTrue(ez.updateQuantity(id, 5));
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}
