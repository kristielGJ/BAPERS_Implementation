package model.customers;

import model.Model;

public class Customer extends Model {

	private int acc_no;
	private String customer_name, company, address, phone, valued;

	public Customer() {

	}

	/**
	 * constructor for class
	 * @param customer_name
	 * @param company
	 * @param acc_no
	 * @param address
	 * @param phone
	 */
	public Customer(String customer_name, int acc_no, String address, String phone, String Valued, String company) {
		this.customer_name = customer_name;
		this.company = company;
		this.acc_no = acc_no;
		this.address = address;
		this.phone = phone;
		this.valued = Valued;
	}

	/**
	 * set local variable customer_name to given String customer_name
	 * @param customer_name
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	/**
	 * return the value of local String customer_name
	 * @return
	 */
	public String getCustomer_name() {
		return this.customer_name;
	}

	/**
	 * return the value of local string company
	 * @return
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * set local variable company to given String company
	 * @param company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * set local variable acc_no to given int acc_no
	 * @param acc_no
	 */
	public void setAccNo(int acc_no) {
		this.acc_no = acc_no;
		throw new UnsupportedOperationException();
	}

	/**
	 * return the value of local int acc_no
	 * @return
	 */
	public int getAccNo() {
		return this.acc_no;
	}

	/**
	 * set local variable address to given String address
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * return the value of local String address
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * set local variable phone to given String phone
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * return the value of local String phone
	 * @return
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * return the value of local String valued
	 * @return
	 */
	public String getValued() {
		return valued;
	}

	/**
	 * set local variable valued to given String valued
	 * @param valued
	 */
	public void setValued(String valued) {
		this.valued = valued;
	}

}



