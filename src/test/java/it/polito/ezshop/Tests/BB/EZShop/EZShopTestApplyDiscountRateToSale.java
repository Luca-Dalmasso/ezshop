package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidDiscountRateException;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidPaymentException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestApplyDiscountRateToSale {
	
	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private Integer saleId = null;
	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		saleId = ez.startSaleTransaction();
		//ez.login(fakeShopManager, psw);
		//ez.login(fakeCashier, psw);
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
	
	@Test
	public void testNegativeDiscount() {
		assertThrows(InvalidDiscountRateException.class, ()->{ez.applyDiscountRateToSale(saleId, -0.5);});
	}
	
	@Test
	public void testMoreOrEqualToOneDiscount() {
		assertThrows(InvalidDiscountRateException.class, ()->{ez.applyDiscountRateToSale(saleId, 1);});
	}
	
	@Test
	public void testNotExistingSale() throws InvalidTransactionIdException, InvalidDiscountRateException, UnauthorizedException {
		assertFalse(ez.applyDiscountRateToSale(saleId + 1, 0.5));
	}
	
	@Test
	public void testAleradyPayedSale() throws InvalidTransactionIdException, InvalidDiscountRateException, UnauthorizedException, InvalidPaymentException {
		ez.endSaleTransaction(saleId);
		ez.receiveCashPayment(saleId, 10);
		assertFalse(ez.applyDiscountRateToSale(saleId, 0.5));
	}
	
	@Test
	public void testSuccessfulApplyDiscount() throws InvalidTransactionIdException, InvalidDiscountRateException, UnauthorizedException {
		assertTrue(ez.applyDiscountRateToSale(saleId, 0.5));
	}
		
}