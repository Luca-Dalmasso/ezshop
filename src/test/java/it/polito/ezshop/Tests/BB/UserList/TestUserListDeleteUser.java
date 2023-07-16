package it.polito.ezshop.Tests.BB.UserList;

import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUserIdException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;
import it.polito.ezshop.model.UserList;

public class TestUserListDeleteUser {
	
	private UserList uListLogged;
	
	@Before
	public void simulateLogin() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		SQLiteJDBC.reset();
		uListLogged = new UserList();
		uListLogged.addUser("LUCA", "abcde1234", "Administrator");
		uListLogged.addUser("MATTEO", "abcde1234", "Cashier");
		uListLogged.setAuthenticatedUser("LUCA", "abcde1234");
	}
	
	@Test
	public void testDeleteUserNullID() throws InvalidUserIdException{
		assertThrows(InvalidUserIdException.class, () -> { uListLogged.deleteUser(null); } );
	}
	
	@Test
	public void testDeleteUserNegID() throws InvalidUserIdException{
		assertThrows(InvalidUserIdException.class, () -> { uListLogged.deleteUser(-1); } );
	}
	
	
	@Test
	public void testDeleteUser() throws InvalidUserIdException, UnauthorizedException, InvalidUsernameException, InvalidPasswordException{
		uListLogged.deleteUser(1);
		uListLogged.setAuthenticatedUser("LUCA", "abcde1234");
	}
	

}
