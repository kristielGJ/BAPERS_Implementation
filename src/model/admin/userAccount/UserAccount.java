package model.admin.userAccount;

import model.Model;

//TODO: Hash passwords in database
public class UserAccount extends Model {
	private String name;
	private String email;
	private String phone;
	private String password;
	private String role;

	public UserAccount(int id, String name, String email, String phone, String password, String role) {
		setId(id);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	protected void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	protected void setRole(String role) {
		this.role = role;
	}


}