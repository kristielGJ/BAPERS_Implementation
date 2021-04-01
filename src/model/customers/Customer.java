package model.customers;

import model.Model;

public class Customer extends Model {

	private int acc_no;
	private String customer_name, company, address, phone, valued;

	public Customer() {

	}

	/**
	 *
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
	} //constructor for class

	/**
	 *
	 * @param customer_name
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	} //set local variable customer_name to given String customer_name

	public String getCustomer_name() {
		return this.customer_name;
	} //return the value of local String customer_name

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 *
	 * @param acc_no
	 */
	public void setAccNo(int acc_no) {
		this.acc_no = acc_no;
		throw new UnsupportedOperationException();
	} //set local variable acc_no to given int acc_no

	public int getAccNo() {
		return this.acc_no;
	} //return the value of local int acc_no

	/**
	 *
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	} //set local variable address to given String address

	public String getAddress() {
		return this.address;
	} //return the value of local String address

	/**
	 *
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	} //set local variable phone to given String phone

	public String getPhone() {
		return this.phone;
	} //return the value of local String phone

	public String getValued() {
		return valued;
	}

	public void setValued(String valued) {
		this.valued = valued;
	}

}



