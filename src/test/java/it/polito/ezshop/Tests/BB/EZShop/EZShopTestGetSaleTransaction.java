package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;
import it.polito.ezshop.model.SaleTransaction;

public class EZShopTestGetSaleTransaction {

	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private Integer saleId = null;
	private Integer saleId1 = null;

	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException,
			InvalidTransactionIdException, UnauthorizedException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		ez.login(fakeAdmin, psw);
		saleId = ez.startSaleTransaction();
		saleId1 = ez.startSaleTransaction();
		ez.endSaleTransaction(saleId);
		// ez.login(fakeShopManager, psw);
		// ez.login(fakeCashier, psw);
	}

	@Test
	public void testNotExistingSale() throws InvalidTransactionIdException, UnauthorizedException {
		assertTrue(ez.getSaleTransaction(saleId + 100) == null);
	}

	@Test
	public void testNotClosedSale() throws InvalidTransactionIdException, UnauthorizedException {
		assertTrue(ez.getSaleTransaction(saleId1) == null);
	}

	@Test
	public void testSuccessfulGetTransactions() throws InvalidTransactionIdException, UnauthorizedException {
		assertTrue(SaleTransaction.class.isInstance(ez.getSaleTransaction(saleId)));
	}

	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}