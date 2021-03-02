package admin;

public class User_account {

	private int user_ID;
	private String name;
	private String username;
	private String password;
	private String role;

	/**
	 * 
	 * @param user_ID
	 */
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public int getUser_ID() {
		return this.user_ID;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return this.role;
	}

	/**
	 * 
	 * @param user_ID
	 */
	public void deleteUserAccount(int user_ID) {
		// TODO - implement User_account.deleteUserAccount
		throw new UnsupportedOperationException();
	}

	public boolean isAlertNeeded() {
		// TODO - implement User_account.isAlertNeeded
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user_account_details
	 */
	public boolean storeUser_account(String user_account_details) {
		// TODO - implement User_account.storeUser_account
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user_ID
	 * @param name
	 * @param username
	 * @param password
	 * @param role
	 */
	public User_account(int user_ID, String name, String username, String password, String role) {
		// TODO - implement User_account.User_account
		throw new UnsupportedOperationException();
	}

}