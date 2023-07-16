package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertFalse;
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
import it.polito.ezshop.exceptions.InvalidProductIdException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestDeleteProductFromSale {

	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private Integer saleId = null;
	private String pCode = "012345000010";
	private Integer pId = null;

	@Before
	public void beforeEachTest()
			throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException,
			InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException,
			InvalidProductIdException, InvalidTransactionIdException, InvalidQuantityException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		saleId = ez.startSaleTransaction();
		pId = ez.createProductType("Tomato", pCode, 0.5, "bio");
		ez.updateQuantity(pId, 10);
		ez.addProductToSale(saleId, pCode, 5);
		// ez.login(fakeShopManager, psw);
		// ez.login(fakeCashier, psw);
	}

	@Test
	public void testNegativeAmount() {
		assertThrows(InvalidQuantityException.class, () -> {
			ez.deleteProductFromSale(saleId, pCode, -10);
		});
	}

	@Test
	public void testNotExistingProduct() throws InvalidTransactionIdException, InvalidProductCodeException,
			InvalidQuantityException, UnauthorizedException {
		assertFalse(ez.deleteProductFromSale(saleId, "012345000027", 2));
	}

	@Test
	public void testNotExistingSale() throws InvalidTransactionIdException, InvalidProductCodeException,
			InvalidQuantityException, UnauthorizedException {
		assertFalse(ez.deleteProductFromSale(saleId + 1, pCode, 2));
	}

	@Test
	public void testNotOpenSale() throws InvalidTransactionIdException, InvalidProductCodeException,
			InvalidQuantityException, UnauthorizedException {
		ez.endSaleTransaction(saleId);
		assertFalse(ez.deleteProductFromSale(saleId, pCode, 2));
	}

	@Test
	public void testSuccesfulDeleteProduct() throws InvalidTransactionIdException, InvalidProductCodeException,
			InvalidQuantityException, UnauthorizedException {
		assertTrue(ez.deleteProductFromSale(saleId, pCode, 3));
	}

	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}