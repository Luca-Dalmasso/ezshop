package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidCustomerIdException;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestDeleteCustomer {
	
	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	private Integer cusId = null;
	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidCustomerNameException, UnauthorizedException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		cusId = ez.defineCustomer("Mark");
		//ez.login(fakeShopManager, psw);
		//ez.login(fakeCashier, psw);
	}
	
	@Test
	public void testSuccesfulDeleteCustomer() throws InvalidCustomerIdException, UnauthorizedException {
		assertTrue(ez.deleteCustomer(cusId));
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
	
}
