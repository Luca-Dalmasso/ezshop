package it.polito.ezshop.model;

import java.util.List;
import java.util.ArrayList;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.*;

public class UserList {

    private List<User> usersList;
    private User authenticatedUser;

    public UserList(){
        this.authenticatedUser = null;
        List<User> lst = SQLiteJDBC.init("Users", User.class);
    	if(lst == null)
    		this.usersList = new ArrayList<User>();
    	else
    		this.usersList = lst;
    }

    public List<User> getAllUsers(){
    	return this.usersList;
    }
    
    private Integer generateID(List<User> userList) {
    	Integer maxID = userList.stream().mapToInt(v -> v.getId()).max().orElse(0);  
    	return maxID + 1;
    }
    			
    public Integer addUser (String username, String password, String role) throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException{
    	if (username == null || username.length() == 0)
    		throw new InvalidUsernameException();
    	if (password == null || password.length() == 0)
    		throw new InvalidPasswordException();
    	if (role == null || role.length() == 0)
    		throw new InvalidRoleException();
    	if (role.equals("Administrator") == false &&
    		role.equals("Cashier") == false	&&
    		role.equals("ShopManager") == false) {
    		throw new InvalidRoleException();
    	}
    	User u = usersList.stream().filter(us -> (us.getUsername().equals(username))).findAny().orElse(null);	
    	if (u!=null) {return -1;}
    	Integer generatedID = generateID(usersList);
    	User usr = new User(generatedID,username,password,role);
    	if(!SQLiteJDBC.insert("Users", usr.getId(), usr.getUsername(), usr.getPassword(), usr.getRole()))
    		return -1;
    	usersList.add(usr);
		return generatedID;
    }

    public boolean deleteUser(Integer id) throws InvalidUserIdException{
    	if(id == null || id <= 0)
            throw new InvalidUserIdException();
        for(int i=0; i<this.usersList.size(); i++){
            if(this.usersList.get(i).getId() == id){
                User u = this.usersList.get(i);
                if(!SQLiteJDBC.delete("Users", u.getId()))
                    return false;
                this.usersList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Integer getAuthenticatedUser(){
    	if (this.authenticatedUser==null) {
    		return -1;
    	}
        return authenticatedUser.getId();
    }
    
    public boolean setAuthenticatedUser(String username, String password) throws InvalidUsernameException, InvalidPasswordException {	
    	if(username==null || username.length() == 0) {throw new InvalidUsernameException();}
    	if(password==null || password.length() == 0) {throw new InvalidPasswordException();}
    	User u = usersList.stream().filter(us -> (us.getUsername().equals(username))).findAny().orElse(null);
        if( u == null)
            return false;
        if(!u.getPassword().equals(password))
            return false;
        this.authenticatedUser = u;
        return true;
    }
    
    public boolean emptyAuthenticatedUser() {
    	if (authenticatedUser==null)
    		return false;
    	this.authenticatedUser = null;
    	return true;
    }
    
    public boolean updateUserRights(Integer id, String role) throws InvalidUserIdException, InvalidRoleException{
        if(id == null || id <= 0)
            throw new InvalidUserIdException();
        if(role == null || (!role.equals("Administrator") && (!role.equals("Cashier")) && (!role.equals("ShopManager"))))
            throw new InvalidRoleException();
        for(int i=0; i<this.usersList.size(); i++){
            if(this.usersList.get(i).getId() == id){
            	if(!SQLiteJDBC.update("Users", usersList.get(i).getId(), usersList.get(i).getUsername(), usersList.get(i).getPassword(), role))
            		return false;
                this.usersList.get(i).setRole(role);
               return true;
            }
        }
        return false;
    }

}
