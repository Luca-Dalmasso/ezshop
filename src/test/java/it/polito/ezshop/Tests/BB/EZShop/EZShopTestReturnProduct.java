package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidCreditCardException;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.InvalidProductIdException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestReturnProduct {
	
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
	public void testInvalidCode() {
		assertThrows(InvalidProductCodeException.class, () -> {
			ez.returnProduct(1, "012345000011", 1);
		});
	}
	
	@Test
	public void testInvalidQuantity() throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException {
		String code = "012345000010";
		ez.createProductType("milk", code, 1.0, "bio");
		assertThrows(InvalidQuantityException.class, () -> {
			ez.returnProduct(1, code, 0);
		});
	}
	
	@Test
	public void testMissingReturn() throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException, InvalidTransactionIdException, InvalidQuantityException {
		String code = "012345000010";
		ez.createProductType("milk", code, 1.0, "bio");
		assertFalse(ez.returnProduct(1, code, 1));
	}
	
	@Test
	public void testReturn() throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException, InvalidTransactionIdException, InvalidQuantityException, InvalidProductIdException, InvalidCreditCardException {
		String code = "012345000010";
		int pId = ez.createProductType("milk", code, 1.0, "bio");
		ez.updateQuantity(pId, 5);
		int sId = ez.startSaleTransaction();
		ez.addProductToSale(sId, code, 3);
		ez.endSaleTransaction(sId);
		ez.receiveCreditCardPayment(sId, "4485370086510891");
		int rId = ez.startReturnTransaction(sId);		
		assertTrue(ez.returnProduct(rId, code, 1));
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
	
}