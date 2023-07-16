package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestGetProductTypeByBarCode {

	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private String barcode = "012345000010";
	private String description = "Tomato";

	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException,
			InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException,
			UnauthorizedException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		ez.createProductType(description, barcode, 0.5, "bio");
		// ez.login(fakeShopManager, psw);
		// ez.login(fakeCashier, psw);
	}
	
	@Test
	public void testNotExistingBarcode() throws InvalidProductCodeException, UnauthorizedException {
		assertTrue((ez.getProductTypeByBarCode("012345000027") == null));
	}

	@Test
	public void testSuccesfulGetProduct() throws InvalidProductCodeException, UnauthorizedException {
		assertTrue(ez.getProductTypeByBarCode(barcode).getProductDescription().equals(description));
	}

	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}
