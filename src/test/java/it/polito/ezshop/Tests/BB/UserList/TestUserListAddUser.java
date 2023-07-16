package it.polito.ezshop.Tests.BB.UserList;

import org.junit.Before;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.model.UserList;

public class TestUserListAddUser {

	private UserList uList;

	@Before
	public void init() {
		SQLiteJDBC.reset();
		uList = new UserList();
	}

	@Test
	public void testNullUsername() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		assertThrows(InvalidUsernameException.class, () -> {
			uList.addUser(null, "aa", "aa");
		});
	}

	@Test
	public void testEmptyUsername() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		assertThrows(InvalidUsernameException.class, () -> {
			uList.addUser("", "aa", "aa");
		});
	}

	@Test
	public void testDuplicatedUsername()
			throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		uList.addUser("marco.polito.it", "whathever", "Administrator");
		assertTrue(uList.addUser("marco.polito.it", "aa", "Administrator") == -1);
	}

	@Test
	public void testNullPassword() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		assertThrows(InvalidPasswordException.class, () -> {
			uList.addUser("aa", null, "whathever");
		});
	}

	@Test
	public void testEmptyPassword() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		assertThrows(InvalidPasswordException.class, () -> {
			uList.addUser("aa", "", "whathever");
		});
	}

	@Test
	public void testNullRole() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		assertThrows(InvalidRoleException.class, () -> {
			uList.addUser("aa", "whathever", null);
		});
	}

	@Test
	public void testEmptyRole() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		assertThrows(InvalidRoleException.class, () -> {
			uList.addUser("aa", "whathever", "");
		});
	}

	@Test
	public void testInvalidRole() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		assertThrows(InvalidRoleException.class, () -> {
			uList.addUser("aa", "whathever", "Pizzaiolo");
		});
	}

	@Test
	public void testCreateValidUser() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		uList.addUser("luca.polito.it", "whathever", "Administrator");
	}

}
