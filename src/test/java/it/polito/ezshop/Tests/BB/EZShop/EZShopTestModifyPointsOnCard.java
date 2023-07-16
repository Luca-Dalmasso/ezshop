package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidCustomerCardException;
import it.polito.ezshop.exceptions.InvalidCustomerIdException;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestModifyPointsOnCard {

	
	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		//ez.login(fakeAdmin, psw);
		//ez.login(fakeShopManager, psw);
		ez.login(fakeCashier, psw);
	}
	
	@Test
	public void testInvalidCard() throws InvalidCustomerCardException, UnauthorizedException {
		assertFalse(ez.modifyPointsOnCard("9999999999", 1));
	}
	
	@Test
	public void testNullCustomer() throws InvalidCustomerCardException, UnauthorizedException, InvalidCustomerNameException, InvalidCustomerIdException {
		String card = ez.createCard();
		assertFalse(ez.modifyPointsOnCard(card, 1));
	}
	
	@Test
	public void testModify() throws InvalidCustomerCardException, UnauthorizedException, InvalidCustomerNameException, InvalidCustomerIdException {
		String card = ez.createCard();
		int customer = ez.defineCustomer("Mickey Mouse");
		ez.attachCardToCustomer(card, customer);
		assertTrue(ez.modifyPointsOnCard(card, 10));
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
}