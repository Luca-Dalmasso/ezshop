package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertThrows;
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

public class EZShopTestCreateProductType {

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
		// ez.login(fakeShopManager, psw);
		// ez.login(fakeCashier, psw);
	}

	@Test
	public void testWrongBarcodeValue() {
		assertThrows(InvalidProductCodeException.class, () -> {
			ez.createProductType("Tomato", "thisisnotavalidbarcode", 0.5, "bio");
		});
	}

	@Test
	public void testWrongBarcode12Value() {
		assertThrows(InvalidProductCodeException.class, () -> {
			ez.createProductType("Tomato", "111111111111", 0.5, "bio");
		});
	}

	@Test
	public void testWrongBarcode13Value() {
		assertThrows(InvalidProductCodeException.class, () -> {
			ez.createProductType("Tomato", "1111111111111", 0.5, "bio");
		});
	}

	@Test
	public void testWrongBarcode14Value() {
		assertThrows(InvalidProductCodeException.class, () -> {
			ez.createProductType("Tomato", "11111111111111", 0.5, "bio");
		});
	}

	@Test
	public void testSuccesfulAddProduct() throws InvalidProductDescriptionException, InvalidProductCodeException,
			InvalidPricePerUnitException, UnauthorizedException {
		assertTrue(ez.createProductType("Tomato", "012345000010", 0.5, "bio") == 1);
	}

	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}
