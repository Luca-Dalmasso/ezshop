package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidLocationException;
import it.polito.ezshop.exceptions.InvalidOrderIdException;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.InvalidProductIdException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestPayOrder {
	
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
	public void testNotIssuedOrder() throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidQuantityException, InvalidProductIdException {
		String code = "012345000010";
		int pId = ez.createProductType("milk", code, 1.0, "bio");
		ez.updatePosition(pId, "1-A-9");
		int oId = ez.issueOrder(code, 10, 0.2);
		ez.recordBalanceUpdate(2);
		ez.payOrder(oId);
		assertFalse(ez.payOrder(oId));
	}
	
	@Test
	public void testNotEnoughMoney() throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidQuantityException, InvalidProductIdException {
		String code = "012345000010";
		int pId = ez.createProductType("milk", code, 1.0, "bio");
		ez.updatePosition(pId, "1-A-9");
		int oId = ez.issueOrder(code, 10, 0.2);
		assertFalse(ez.payOrder(oId));
	}
	
	@Test
	public void testPay() throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidQuantityException, InvalidProductIdException {
		String code = "012345000010";
		int pId = ez.createProductType("milk", code, 1.0, "bio");
		ez.updatePosition(pId, "1-A-9");
		int oId = ez.issueOrder(code, 10, 0.2);
		ez.recordBalanceUpdate(2);
		assertTrue(ez.payOrder(oId));
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}
