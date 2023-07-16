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

public class EZShopTestAttachCardToCustomer {
	
	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private String card = null;
	private String card1 = null;
	private Integer custId = null;
	private Integer custId1 = null;
	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidCustomerNameException, InvalidCustomerIdException, InvalidCustomerCardException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeCashier, psw);
		card = ez.createCard();
		card1 = ez.createCard();
		custId = ez.defineCustomer("Mark");
		custId1 = ez.defineCustomer("Robert");
		ez.attachCardToCustomer(card1, custId1);
		//ez.login(fakeAdmin, psw);
		//ez.login(fakeShopManager, psw);
	}
	
	@Test
	public void testNotexistingCard() throws InvalidCustomerIdException, InvalidCustomerCardException, UnauthorizedException {
		String fakecard = card.substring(1) + "0";
		assertFalse(ez.attachCardToCustomer(fakecard, custId));	
	}
	
	@Test
	public void testAlreadyAttachedCard() throws InvalidCustomerIdException, InvalidCustomerCardException, UnauthorizedException {
		assertFalse(ez.attachCardToCustomer(card1, custId));
	}
	
	@Test
	public void testSuccesfulAttachedCard() throws InvalidCustomerIdException, InvalidCustomerCardException, UnauthorizedException {
		assertTrue(ez.attachCardToCustomer(card, custId));
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
	
}
