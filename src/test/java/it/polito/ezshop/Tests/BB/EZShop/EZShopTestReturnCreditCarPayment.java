package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidCreditCardException;
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

public class EZShopTestReturnCreditCarPayment {
	
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
	public void testMissingRT() throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
		assertTrue(-1 == ez.returnCreditCardPayment(9, "4485370086510891"));
	}
	
	@Test
	public void testOpenRT() throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidQuantityException, InvalidProductIdException, InvalidPaymentException {
		String code = "012345000010";
		ez.createProductType("milk", code, 1.0, "bio");
		int sId = ez.startSaleTransaction();
		ez.endSaleTransaction(sId);
		ez.receiveCashPayment(sId, 10.0);
		int rId = ez.startReturnTransaction(sId);
		ez.returnProduct(rId, code, 1);
		assertTrue(-1 == ez.returnCreditCardPayment(rId, "4485370086510891"));
	}
	
	@Test
	public void testInsufficientMoney() throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidQuantityException, InvalidProductIdException {
		String code = "012345000010";
		int pId = ez.createProductType("milk", code, 1.0, "bio");
		ez.updateQuantity(pId, 5);
		int sId = ez.startSaleTransaction();
		ez.addProductToSale(sId, code, 3);
		ez.endSaleTransaction(sId);
		ez.receiveCreditCardPayment(sId, "4485370086510891");
		int rId = ez.startReturnTransaction(sId);
		ez.returnProduct(rId, code, 1);
		ez.endReturnTransaction(rId, true);
		assertTrue(1.0 == ez.returnCreditCardPayment(rId, "4485370086510891"));
	}
	
	@Test
	public void testReturn() throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidQuantityException, InvalidProductIdException {
		String code = "012345000010";
		ez.recordBalanceUpdate(5);
		int pId = ez.createProductType("milk", code, 1.0, "bio");
		ez.updateQuantity(pId, 5);
		int sId = ez.startSaleTransaction();
		ez.addProductToSale(sId, code, 3);
		ez.endSaleTransaction(sId);
		ez.receiveCreditCardPayment(sId, "4485370086510891");
		int rId = ez.startReturnTransaction(sId);
		ez.returnProduct(rId, code, 1);
		ez.endReturnTransaction(rId, true);
		assertTrue(1.0 == ez.returnCreditCardPayment(rId, "4485370086510891"));
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
	
}