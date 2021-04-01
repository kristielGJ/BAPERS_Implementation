package model.admin.userAccount;

import model.Model;

/**
 * A UserAccount class which models a Staff Account.
 * Holds the data retrieved from the database through @UserAccountTransaction.
 */
public class UserAccount extends Model {
	private String name;
	private String email;
	private String phone;
	private String password;
	private String role;
	private String department;

	public UserAccount(int id, String name, String email, String phone, String password, String role) {
		setId(id);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
	}

	// This constructor is overloaded the handle the edge case of a Technician having a department.
	public UserAccount(int id, String name, String email, String phone, String password, String role, String department) {
		setId(id);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	protected void setDepartment(String department) {
		this.department = department;
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