package it.polito.ezshop.change2Tests;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidCreditCardException;
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

public class EZShopTestReturnProductRFID {
	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private String rfid = "000010000233";
	private Integer rId = null;

	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException,
			InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException,
			UnauthorizedException, InvalidProductIdException, InvalidLocationException, InvalidQuantityException,
			InvalidOrderIdException, InvalidRFIDException, InvalidTransactionIdException, InvalidCreditCardException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		String code = "012345000010";
		int pId = ez.createProductType("milk", code, 1.0, "bio");
		ez.updatePosition(pId, "10-A-1");
		ez.recordBalanceUpdate(100);
		Integer oId = ez.issueOrder(code, 3, 0.5);
		ez.payOrder(oId);
		ez.recordOrderArrivalRFID(oId, rfid);
		Integer sId = ez.startSaleTransaction();
		ez.addProductToSaleRFID(sId, rfid);
		ez.endSaleTransaction(sId);
		ez.receiveCreditCardPayment(sId, "4485370086510891");
		rId = ez.startReturnTransaction(sId);
		// ez.login(fakeShopManager, psw);
		// ez.login(fakeCashier, psw);
	}

	@Test
	public void testInvalidCode() {
		assertThrows(InvalidTransactionIdException.class, () -> {
			ez.returnProductRFID(-11, "012345000011");
		});
	}

	@Test
	public void testInvalidRFID() {
		assertThrows(InvalidRFIDException.class, () -> {
			ez.returnProductRFID(1, null);
		});
	}

	@Test
	public void testReturn() throws InvalidProductDescriptionException, InvalidProductCodeException,
			InvalidPricePerUnitException, UnauthorizedException, InvalidTransactionIdException,
			InvalidQuantityException, InvalidProductIdException, InvalidCreditCardException, InvalidRFIDException {

		assertTrue(ez.returnProductRFID(rId, rfid));
	}

	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
}
