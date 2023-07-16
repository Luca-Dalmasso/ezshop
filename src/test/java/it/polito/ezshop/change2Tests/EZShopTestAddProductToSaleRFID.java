package it.polito.ezshop.change2Tests;

import static org.junit.Assert.assertFalse;
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

public class EZShopTestAddProductToSaleRFID {

	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private Integer saleId = null;
	private String pCode = "012345000010";
	private Integer pId = null;
	private Integer oId = null;
	private String rfid1 = "000000000111";

	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException,
			InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException,
			UnauthorizedException, InvalidProductIdException, InvalidQuantityException, InvalidOrderIdException,
			InvalidLocationException, InvalidRFIDException {
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
		ez.recordOrderArrivalRFID(oId, rfid1);
	}

	@Test
	public void testNullSaleId() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			ez.addProductToSaleRFID(null, rfid1);
		});
	}

	@Test
	public void testLessOrEqualToZeroSaleId() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			ez.addProductToSaleRFID(-1, rfid1);
		});
	}

	@Test
	public void testNotEnoughUserRights() {
		ez.logout();
		assertThrows(UnauthorizedException.class, () -> {
			ez.addProductToSaleRFID(saleId, rfid1);
		});
	}

	@Test
	public void testNullRFID() {
		assertThrows(InvalidRFIDException.class, () -> {
			ez.addProductToSaleRFID(saleId, null);
		});
	}

	@Test
	public void testEmptyRFID() {
		assertThrows(InvalidRFIDException.class, () -> {
			ez.addProductToSaleRFID(saleId, "");
		});
	}

	@Test
	public void testInvalidRFID() {
		assertThrows(InvalidRFIDException.class, () -> {
			ez.addProductToSaleRFID(saleId, "notavalidRFID");
		});
	}
	
	@Test
	public void testAlreadyClosedSale() throws InvalidTransactionIdException, UnauthorizedException,
			InvalidRFIDException, InvalidQuantityException {
		ez.endSaleTransaction(saleId);
		assertFalse(ez.addProductToSaleRFID(saleId, rfid1));
	}

	@Test
	public void testSuccessfulAddProductToSaleRFID() throws InvalidTransactionIdException, InvalidRFIDException,
			InvalidQuantityException, UnauthorizedException {
		assertTrue(ez.addProductToSaleRFID(saleId, rfid1));
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
}
