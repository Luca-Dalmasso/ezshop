package it.polito.ezshop.change2Tests;

import static org.junit.Assert.assertThrows;
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
import it.polito.ezshop.exceptions.InvalidRFIDException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestEndSaleTransactionNew {

	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private Integer saleId = null;
	private String pCode = "012345000010";
	private Integer pId = null;
	private Integer oId = null;
	private String rfid = "000010000233";

	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException,
			InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, UnauthorizedException,
			InvalidProductIdException, InvalidProductDescriptionException, InvalidPricePerUnitException,
			InvalidRFIDException, InvalidLocationException, InvalidOrderIdException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		saleId = ez.startSaleTransaction();
		pId = ez.createProductType("Tomato", pCode, 0.5, "bio");
		ez.updatePosition(pId, "10-A-1");
		ez.recordBalanceUpdate(100);
		oId = ez.issueOrder(pCode, 3, 0.5);
		ez.payOrder(oId);
		ez.recordOrderArrivalRFID(oId, rfid);
		ez.addProductToSaleRFID(saleId, rfid);
		// ez.addProductToSale(saleId, pCode, 5);
		// ez.login(fakeShopManager, psw);
		// ez.login(fakeCashier, psw);
	}

	@Test
	public void testSuccesfulEndSaleNew() throws InvalidTransactionIdException, UnauthorizedException {

		assertTrue(ez.endSaleTransaction(saleId));

	}

	@Test
	public void testNullEndSaleId() throws InvalidTransactionIdException, UnauthorizedException {
		assertThrows(InvalidTransactionIdException.class, () -> {
			ez.endSaleTransaction(null);
		});

	}

	@Test
	public void testUnauthorizedUser() throws InvalidTransactionIdException, UnauthorizedException {
		ez.logout();
		assertThrows(UnauthorizedException.class, () -> {
			ez.endSaleTransaction(saleId);
		});

	}

	@AfterClass
	public static void finalReset() {
		ez.reset();
	}

}
