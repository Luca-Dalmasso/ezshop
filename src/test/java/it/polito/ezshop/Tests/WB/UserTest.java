package it.polito.ezshop.Tests.WB;

import org.junit.Test;

import it.polito.ezshop.model.User;

public class UserTest {
	
	@Test
	public void testConstructorSetGet() {
		/*Integer id, String username, String password, String role*/
		User u = new User(1,"piero.angela.dot","123455678","Cashier");
		u.setId(u.getId());
		u.setUsername(u.getUsername());
		u.setPassword(u.getPassword());
		u.setRole(u.getRole());
	}

}
