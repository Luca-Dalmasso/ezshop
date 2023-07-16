package it.polito.ezshop.Tests.BB.UserList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;


import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUserIdException;
import it.polito.ezshop.model.UserList;

public class TestUpdateUserRights {
	
	private UserList uList;
	
	@Before
	public void init() {
		SQLiteJDBC.reset();
		uList = new UserList();
	}
	
	@Test
	public void testNullUserID() throws InvalidUserIdException{
		assertThrows(InvalidUserIdException.class, () -> { uList.updateUserRights(null, "whatever"); } );
	}
	
	@Test
	public void testNegUserID() throws InvalidUserIdException{
		assertThrows(InvalidUserIdException.class, () -> { uList.updateUserRights(-1, "whatever"); } );
	}
	
	@Test
	public void testNullRole() throws InvalidRoleException{
		assertThrows(InvalidRoleException.class, () -> { uList.updateUserRights(10, null); } );
	}
	
	@Test
	public void testEmptyRole() throws InvalidRoleException{
		assertThrows(InvalidRoleException.class, () -> { uList.updateUserRights(10, ""); } );
	}
	
	@Test
	public void testInvalidRole() throws InvalidRoleException{
		assertThrows(InvalidRoleException.class, () -> { uList.updateUserRights(10, "Stealer"); } );
	}
	
	@Test
	public void testUpdateUserRights() throws InvalidUserIdException, InvalidRoleException{
		/*Should return false, no user is saved*/
		assertFalse(uList.updateUserRights(10, "Administrator"));
	}

}
