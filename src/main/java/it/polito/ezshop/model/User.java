package it.polito.ezshop.model;
public class User implements it.polito.ezshop.data.User{

    private Integer id;
    private String username;
    private String password;
    private String role;

	public User(Integer id, String username, String password, String role){
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
    
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getRole() {
		return this.role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
	}

}
