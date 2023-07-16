package it.polito.ezshop.change2Tests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidLocationException;
import it.polito.ezshop.exceptions.InvalidOrderIdException;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidPaymentException;
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

public class EZShopTestEndReturnTransactionNew {

    private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private Integer saleId = null;
	private String pCode = "012345000010";
	private Integer pId = null;
	private Integer oId = null;
    private Integer ticket = null;
    private Integer rId = null;
	private String rfid1 = "000000000111";
	private String rfid2 = "000000000112";
	
	@Before
	public void init() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException,
    InvalidProductIdException, InvalidLocationException, InvalidProductDescriptionException, InvalidProductCodeException,
    InvalidPricePerUnitException, InvalidQuantityException, InvalidOrderIdException, InvalidRFIDException, InvalidTransactionIdException,
    InvalidPaymentException {
		SQLiteJDBC.reset();
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
        ez.addProductToSaleRFID(saleId, rfid1);
        ez.addProductToSaleRFID(saleId, rfid2);
        ez.endSaleTransaction(saleId);
        ticket = ez.getSaleTransaction(saleId).getTicketNumber();
        ez.receiveCashPayment(ticket, 1.0);
        rId = ez.startReturnTransaction(saleId);
	}
	
	@Test
    public void testCommitTrue() throws InvalidTransactionIdException, UnauthorizedException, InvalidProductCodeException, InvalidQuantityException, InvalidRFIDException {
    	ez.returnProductRFID(rId, rfid1);
        assertTrue(ez.endReturnTransaction(rId, true));
    }
	
	@Test
    public void testCommitFalse() throws InvalidTransactionIdException, UnauthorizedException, InvalidProductCodeException, InvalidQuantityException, InvalidRFIDException {
    	ez.returnProductRFID(rId, rfid1);
        assertTrue(ez.endReturnTransaction(rId, false));
    }

    @Test
    public void testCatch() throws InvalidTransactionIdException, UnauthorizedException, InvalidProductCodeException, InvalidQuantityException {
    	//ez.returnProduct(rId, pCode, 1);
        assertFalse(ez.endReturnTransaction(rId, true));
    }

}
