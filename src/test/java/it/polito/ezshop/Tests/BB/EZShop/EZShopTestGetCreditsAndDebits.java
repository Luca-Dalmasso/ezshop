package it.polito.ezshop.Tests.BB.EZShop;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class EZShopTestGetCreditsAndDebits {
	
	private static EZShop ez = new EZShop();
	private String fakeAdmin = "fakeadmin";
	private String fakeShopManager = "fakeshopmanager";
	private String fakeCashier = "fakecashier";
	private String psw = "fakepsw";
	@Before
	public void beforeEachTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException {
		ez.reset();
		ez.createUser(fakeAdmin, psw, "Administrator");
		ez.createUser(fakeShopManager, psw, "ShopManager");
		ez.createUser(fakeCashier, psw, "Cashier");
		ez.login(fakeAdmin, psw);
		ez.recordBalanceUpdate(100.0);
		ez.recordBalanceUpdate(-20.0);
		ez.recordBalanceUpdate(30.0);
		ez.recordBalanceUpdate(-50.0);
		//ez.login(fakeShopManager, psw);
		//ez.login(fakeCashier, psw);
	}
	
	@Test
	public void testNullFromField() throws UnauthorizedException {
		assertTrue(ez.getCreditsAndDebits(null, LocalDate.parse("2500-01-01")).size()==4);
	}
	
	@Test
	public void testNullToField() throws UnauthorizedException {
		assertTrue(ez.getCreditsAndDebits(LocalDate.parse("2019-01-01"), null).size()==4);
	}
	
	@Test
	public void testSuccessfulGet() throws UnauthorizedException {
		assertTrue(ez.getCreditsAndDebits(LocalDate.parse("2019-01-01"), LocalDate.parse("2500-01-01")).size()==4);
	}
	
	@AfterClass
	public static void finalReset() {
		ez.reset();
	}
	
}