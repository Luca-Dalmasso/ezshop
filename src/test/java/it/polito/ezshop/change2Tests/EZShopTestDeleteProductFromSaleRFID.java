package it.polito.ezshop.change2Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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

public class EZShopTestDeleteProductFromSaleRFID {

	private static EZShop ez = new EZShop();
	private Integer saleId,saleNoRFIDDED;
	private Integer pId;
	private Integer oID;
	@Before
	public void beforeEachTest()
			throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException,
			InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException,
			InvalidProductIdException, InvalidTransactionIdException, InvalidQuantityException, InvalidOrderIdException, InvalidLocationException, InvalidRFIDException {
		ez.reset();
		ez.createUser("user", "userpass", "Administrator");
		ez.login("user", "userpass");
		saleId = ez.startSaleTransaction();
		pId = ez.createProductType("Tomato", "012345000010", 0.5, "bio");
		ez.updateQuantity(pId, 1000);
		ez.updatePosition(pId, "1-A-9");
		ez.addProductToSale(saleId, "012345000010", 50);
		oID = ez.issueOrder("012345000010", 2, 1.0);
	}
	
	
	
	@Test
	public void testNullTransactionID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidTransactionIdException, InvalidRFIDException, InvalidQuantityException, UnauthorizedException {
		assertThrows(InvalidTransactionIdException.class, () -> {ez.deleteProductFromSaleRFID(null, "000000000001");});
	}
	
	
	
	@Test
	public void testNegTransactionID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidTransactionIdException, InvalidRFIDException, InvalidQuantityException, UnauthorizedException {
		assertThrows(InvalidTransactionIdException.class, () -> {ez.deleteProductFromSaleRFID(0, "000000000001");});
	}
	
	
	@Test
	public void testNotOpenedTransaction() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidTransactionIdException, InvalidRFIDException, InvalidQuantityException, UnauthorizedException {
		assertFalse(ez.deleteProductFromSaleRFID(1, "000000000001"));
	}
	
	
	
	@Test
	public void testNullRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidTransactionIdException, InvalidRFIDException, InvalidQuantityException, UnauthorizedException {
		assertThrows(InvalidRFIDException.class, () -> {ez.deleteProductFromSaleRFID(saleId,null);});
	}
	
	
	@Test
	public void testEmptyRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidTransactionIdException, InvalidRFIDException, InvalidQuantityException, UnauthorizedException {
		assertThrows(InvalidRFIDException.class, () -> {ez.deleteProductFromSaleRFID(saleId,"");});
	}
	
	
	@Test
	public void testInvalidRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidTransactionIdException, InvalidRFIDException, InvalidQuantityException, UnauthorizedException {
		assertThrows(InvalidRFIDException.class, () -> {ez.deleteProductFromSaleRFID(saleId,"00010");});
		assertThrows(InvalidRFIDException.class, () -> {ez.deleteProductFromSaleRFID(saleId,"0001A");});
		assertThrows(InvalidRFIDException.class, () -> {ez.deleteProductFromSaleRFID(saleId,"00000000000A");});
	}
	
	
	@Test
	public void testUnexistingRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidTransactionIdException, InvalidRFIDException, InvalidQuantityException, UnauthorizedException {
		assertFalse(ez.deleteProductFromSaleRFID(saleId, "000000000001"));
	}
	
	
	@Test
	public void testValidDeletion() throws InvalidTransactionIdException, InvalidRFIDException, InvalidQuantityException, UnauthorizedException, InvalidOrderIdException, InvalidLocationException {
		ez.recordBalanceUpdate(20);
		ez.payOrder(oID);
		ez.recordOrderArrivalRFID(oID, "000000000001");
		ez.addProductToSaleRFID(saleId, "000000000001");
		assertTrue(ez.deleteProductFromSaleRFID(saleId, "000000000001"));
	}
	
	
	
}
