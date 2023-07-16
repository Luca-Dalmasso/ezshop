package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import it.polito.ezshop.Tests.BB.UserList.TestUpdateUserRights;
import it.polito.ezshop.Tests.BB.UserList.TestUserListAddUser;
import it.polito.ezshop.Tests.BB.UserList.TestUserListDeleteUser;
import it.polito.ezshop.Tests.BB.UserList.TestUserListGetAllUsers;
import it.polito.ezshop.Tests.BB.UserList.TestUserListSetAuthenticatedUser;
@RunWith(Suite.class)
@Suite.SuiteClasses({TestUpdateUserRights.class, 
					 TestUserListAddUser.class, 
					 TestUserListDeleteUser.class, 
					 TestUserListGetAllUsers.class, 
					 TestUserListSetAuthenticatedUser.class, 
					})
public class TestSuiteUserList {

}
