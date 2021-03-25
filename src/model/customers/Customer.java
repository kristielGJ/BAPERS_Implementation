package model.customers;

import model.Model;
import model.database.DB_Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends Model {

	private String customer_name;
	private int acc_no;
	private String address;
	private String phone;

	public Customer() {

	}

	public Customer(String customer_name, int acc_no, String address, String phone) {
		this.customer_name = customer_name;
		this.acc_no = acc_no;
		this.address = address;
		this.phone = phone;
	} //constructor for class

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	} //set local variable customer_name to given String customer_name

	public String getCustomer_name() {
		return this.customer_name;
	} //return the value of local String customer_name

	public void setAccNo(int acc_no) {
		this.acc_no = acc_no;
		throw new UnsupportedOperationException();
	} //set local variable acc_no to given int acc_no

	public int getAccNo() {
		return this.acc_no;
	} //return the value of local int acc_no

	public void setAddress(String address) {
		this.address = address;
	} //set local variable address to given String address

	public String getAddress() {
		return this.address;
	} //return the value of local String address

	public void setPhone(String phone) {
		this.phone = phone;
	} //set local variable phone to given String phone

	public String getPhone() {
		return this.phone;
	} //return the value of local String phone

}