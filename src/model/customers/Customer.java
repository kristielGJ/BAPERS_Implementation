package model.customers;

import model.database.DB_Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

	private String customer_name;
	private int acc_no;
	private String address;
	private String phone;
	private DB_Connection conn;

	public Customer() {

	}

	/**
	 *
	 * @param customer_name
	 * @param acc_no
	 * @param address
	 * @param phone
	 */
	public Customer(DB_Connection conn,String customer_name, int acc_no, String address, String phone) {
		this.conn = conn;
		this.customer_name = customer_name;
		this.acc_no = acc_no;
		this.address = address;
		this.phone = phone;
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

	/**
	 *
	 * @param customer_data
	 */
	public void updateCustomerDetails(String[] customer_data) { //receive array of Strings called customerData
		conn.update("UPDATE Customer\n" +
				"SET Customer_name = '"+ customer_data[0] + "', Account_no = '" + customer_data[1] +"', Customer_address = '" + customer_data[2] +"', Customer_phone = '" + customer_data[3] +"'\n" +
				"WHERE Account_no = " + customer_data[1]);
	}

	/**
	 *
	 * @param customer_ID
	 * @param customer_data
	 */
	public void saveCustomerDetails(int customer_ID, String[] customer_data) throws SQLException {
		ResultSet rS = conn.query("SELECT * FROM Customer WHERE Account_no = " + customer_ID);
		if(!rS.next()) {
			conn.update("INSERT INTO Customer (Customer_name, Account_no, Customer_address, Customer_phone, Customer_type)\n" +
					"VALUES ('" + customer_data[0] + "', '" + customer_data[1] + "', '" + customer_data[2] + "', '" + customer_data[3] + "', 'Unvalued');");
		}
	}



}