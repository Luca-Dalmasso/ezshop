package it.polito.ezshop.Tests.BB.UserList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.model.UserList;


public class TestUserListSetAuthenticatedUser {
	
	private UserList ulist;
	
	@Before
	public void init() {
		SQLiteJDBC.reset();
		ulist = new UserList();
	}

	@Test
	public void testNullUsername() throws InvalidUsernameException{
		assertThrows(InvalidUsernameException.class, () -> { ulist.setAuthenticatedUser(null, "whatever"); } );
	}
	
	@Test
	public void testNullPassword() throws InvalidPasswordException{
		assertThrows(InvalidPasswordException.class, () -> { ulist.setAuthenticatedUser("whatever",null); } );
	}
	
	@Test
	public void testEmptyUsername() throws InvalidUsernameException{
		assertThrows(InvalidUsernameException.class, () -> { ulist.setAuthenticatedUser("","whatever"); } );
	}

	@Test
	public void testEmptyPassword() throws InvalidPasswordException{
		assertThrows(InvalidPasswordException.class, () -> { ulist.setAuthenticatedUser("whatever",""); } );
	}
	
	@Test
	public void TestSetAuthenticatedUser() throws InvalidUsernameException, InvalidPasswordException{
		/*no user is saved, so even if username is valid i should get FALSE as return*/
		assertFalse(ulist.setAuthenticatedUser("paolo12","whatever"));
	}
	
}
