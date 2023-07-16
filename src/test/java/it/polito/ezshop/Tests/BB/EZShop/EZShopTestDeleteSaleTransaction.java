package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidDiscountRateException;
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

public class EZShopTestDeleteSaleTransaction {
	
	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private Integer saleId = null;
	private String pCode = "012345000010";
	private Integer pId = null;
	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductIdException, InvalidProductDescriptionException, InvalidPricePerUnitException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		saleId = ez.startSaleTransaction();
		pId = ez.createProductType("Tomato", pCode, 0.5, "bio");
		ez.updateQuantity(pId, 10);
		ez.addProductToSale(saleId, pCode, 5);
		//ez.login(fakeShopManager, psw);
		//ez.login(fakeCashier, psw);
	}
	
	@Test
	public void testNotExistingSale() throws InvalidTransactionIdException, InvalidDiscountRateException, UnauthorizedException {
		assertFalse(ez.deleteSaleTransaction(saleId + 1));
	}
	
	@Test
	public void testNotDeletablePayedSale() throws InvalidTransactionIdException, UnauthorizedException, InvalidPaymentException {
		ez.endSaleTransaction(saleId);
		ez.receiveCashPayment(saleId, 10);
		assertFalse(ez.deleteSaleTransaction(saleId));
	}
	
	@Test
	public void testSuccessfulDeleteOpenedSale() throws InvalidTransactionIdException, UnauthorizedException {
		assertTrue(ez.deleteSaleTransaction(saleId));
	}
	
	@Test
	public void testSuccessfulDeleteClosedSale() throws InvalidTransactionIdException, UnauthorizedException {
		ez.endSaleTransaction(saleId);
		assertTrue(ez.deleteSaleTransaction(saleId));
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
	
}