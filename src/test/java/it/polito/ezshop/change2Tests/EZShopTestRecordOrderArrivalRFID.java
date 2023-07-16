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
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestRecordOrderArrivalRFID {

	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	@SuppressWarnings("unused")
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
	}

	@Test
	public void testNullOrderId() {
		assertThrows(InvalidOrderIdException.class, () -> {
			ez.recordOrderArrivalRFID(null, rfid1);
		});
	}

	@Test
	public void testLessOrEqualToZeroOrderId() {
		assertThrows(InvalidOrderIdException.class, () -> {
			ez.recordOrderArrivalRFID(-1, rfid1);
		});
	}

	@Test
	public void testNotAssignedLocation()
			throws InvalidProductIdException, InvalidLocationException, UnauthorizedException {
		ez.updatePosition(pId, "");
		assertThrows(InvalidLocationException.class, () -> {
			ez.recordOrderArrivalRFID(oId, rfid1);
		});
	}

	@Test
	public void testNotEnoughUserRights() {
		ez.logout();
		assertThrows(UnauthorizedException.class, () -> {
			ez.recordOrderArrivalRFID(oId, rfid1);
		});
	}

	@Test
	public void testEmptyRFID() {
		assertThrows(InvalidRFIDException.class, () -> {
			ez.recordOrderArrivalRFID(oId, "");
		});
	}

	@Test
	public void testInvalidRFID() {
		assertThrows(InvalidRFIDException.class, () -> {
			ez.recordOrderArrivalRFID(oId, "notavalidRFID");
		});
	}

	@Test
	public void testSuccesfulRecordOrder()
			throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException, InvalidRFIDException {
		assertTrue(ez.recordOrderArrivalRFID(oId, rfid1));
	}

	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
}
