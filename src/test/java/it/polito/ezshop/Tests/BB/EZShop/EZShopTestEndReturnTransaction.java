package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidPaymentException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.InvalidProductIdException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestEndReturnTransaction {

	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private String code = "012345000010";
	private Integer sId = null;
	private Integer rId = null;
	private Integer pId = null;

	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException,
			InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, UnauthorizedException,
			InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidPaymentException,
			InvalidProductIdException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		ez.recordBalanceUpdate(5);
		pId = ez.createProductType("milk", code, 1.0, "bio");
		ez.updateQuantity(pId, 10);
		sId = ez.startSaleTransaction();
		ez.addProductToSale(sId, code, 1);
		ez.endSaleTransaction(sId);
		ez.receiveCashPayment(sId, 10.0);
		rId = ez.startReturnTransaction(sId);
		ez.returnProduct(rId, code, 1);
		// ez.login(fakeShopManager, psw);
		// ez.login(fakeCashier, psw);
	}

	@Test
	public void testNotExistingRetId() throws InvalidTransactionIdException, UnauthorizedException {
		assertFalse(ez.endReturnTransaction(rId + 1, false));
	}

	@Test
	public void testAlreadyClosedReturn() throws InvalidTransactionIdException, UnauthorizedException {
		ez.endReturnTransaction(rId, true);
		assertFalse(ez.endReturnTransaction(rId, false));
	}

	@Test
	public void testCommitFalseReturn() throws InvalidTransactionIdException, UnauthorizedException {
		assertTrue(ez.endReturnTransaction(rId, false));
	}

	@Test
	public void testCommitTrueReturn() throws InvalidTransactionIdException, UnauthorizedException {
		assertTrue(ez.endReturnTransaction(rId, true));
	}

	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}