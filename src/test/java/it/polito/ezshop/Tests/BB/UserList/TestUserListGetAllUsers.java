package it.polito.ezshop.Tests.BB.UserList;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;
import it.polito.ezshop.model.UserList;

public class TestUserListGetAllUsers {
	
	private UserList uListLogged;
	
	@Before
	public void simulateLogin() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		SQLiteJDBC.reset();
		uListLogged = new UserList();
		uListLogged.addUser("LUCA", "abcde1234", "Administrator");
		uListLogged.addUser("MATTEO", "abcde1234", "Cashier");
	}
	
	
	@Test
	public void testGetAllUsers() throws UnauthorizedException, InvalidUsernameException, InvalidPasswordException {
		assertEquals(uListLogged.getAllUsers().size(),2);
	}
	

}
