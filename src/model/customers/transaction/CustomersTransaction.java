package model.customers.transaction;

import model.Utils;
import model.customers.Customer;
import model.customers.ValuedCustomer;
import model.database.DB_Connection;
import java.sql.*;
import java.util.ArrayList;


public class CustomersTransaction implements I_CustomersTransaction {

	private final Connection conn; //private conn variable

	public CustomersTransaction(DB_Connection conn) {
		this.conn = conn.getConn();
	} // constructor for class

	public Customer addCustomer(String name, String Company, String Address, String Phone){
		Customer cust = null;
		try {
			PreparedStatement pS = conn.prepareStatement("INSERT INTO Customer (Customer_name, Company, Customer_address, Customer_phone, Customer_type) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pS.setString(1, name);
			pS.setString(2, Company);
			pS.setString(3, Address);
			pS.setString(4, Phone);
			pS.setString(5,"Regular");
			pS.executeUpdate();
			int acc_No = Utils.getGeneratedKey(pS);
			cust = new Customer(name, acc_No, Address, Phone, "Regular",Company);
			pS.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return cust;
	} // adds customer entry to database

	public Customer updateCustomer(String name, String Company, int Acc_no, String Address, String Phone, String valued){
		Customer cust = null;
		try{
			PreparedStatement pS = conn.prepareStatement(
					"UPDATE Customer SET Customer_name = ?, Company = ?,Customer_address = ?, Customer_phone = ?, Customer_type = ? WHERE Account_no = ?");
			pS.setString(1, name);
			pS.setString(2,Company);
			pS.setString(3, Address);
			pS.setString(4, Phone);
			pS.setString(5,valued);
			pS.setInt(6, Acc_no);
			pS.executeUpdate();
			cust = new Customer(name, Acc_no, Address, Phone, valued, Company);
			pS.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return cust;
	} // updates current customer entry in database

	public ArrayList<String[]> getAllCust() {
		ArrayList<String[]> customers = null;
		try{
			customers = new ArrayList<>();
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Customer");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String[] data = new String[7];
				data[0] = rs.getString("Customer_name");
				data[1] = Integer.toString(rs.getInt("Account_no"));
				data[2] = rs.getString("Customer_address");
				data[3] = rs.getString("Customer_phone");
				data[4] = rs.getString("Customer_type");
				data[5] = rs.getString("Discount_type");
				data[6] = rs.getString("Company");
				customers.add(data);
			}
			rs.close();
			st.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return customers;
	} //get a list of arrays of strings of all customers in the database

	public String[][] getData(ArrayList<String[]> customers){
		String[][] array = new String[customers.size()][];
		for (int i = 0; i < customers.size(); i++) {
			String[] row = customers.get(i);
			array[i] = row;
		}
		return array;
	} //convert the array list of Sting arrays into an array of string arrays

	private Customer getCustomer(ResultSet rs) throws SQLException {
		Customer cust;
		rs.next();
		if (rs.getString("Customer_type").equals("Valued")) {
			cust = new ValuedCustomer(rs.getString("Customer_name"), rs.getInt("Account_no"), rs.getString("Customer_address"), rs.getString("Customer_phone"), rs.getString("Customer_type"), rs.getString("Discount_type"),rs.getString("Company"));
		} else {
			cust = new Customer(rs.getString("Customer_name"),rs.getInt("Account_no"), rs.getString("Customer_address"), rs.getString("Customer_phone"), rs.getString("Customer_type"),rs.getString("Company"));
		}

		return cust;
	} // return either a valuedCustomer or a Customer

	@Override
	public ArrayList<Customer> getAll() {
		return null;
	} //get all customers from database, didnt need to be implemented

	@Override
	public Customer read(int Acc_no) {
		Customer cust = null;
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Customer WHERE Account_no = ?");
			st.setInt(1, Acc_no);
			ResultSet rs = st.executeQuery();
			cust = getCustomer(rs);
			rs.close();
			st.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return cust;
	} // return a specific customer from the database

	@Override
	public boolean remove(int Acc_no) {
		boolean removed = false;
		try {
			PreparedStatement st = conn.prepareStatement("DELETE FROM Customer WHERE Account_no=?");
			st.setInt(1, Acc_no);
			int update = st.executeUpdate();
			st.close();
			removed = update == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return removed;
	} // remove a customer entry from the database

	public int getLastAccNo(){
		int lastAccNo = 0 ;
		try {
			PreparedStatement pS = conn.prepareStatement("SELECT * FROM Customer WHERE Account_no=(SELECT max(Account_no) FROM Customer)");
			ResultSet rs = pS.executeQuery();
			while(rs.next()){
				lastAccNo = rs.getInt(1);
			}
			rs.close();
			pS.close();
		} catch (Exception e) {

		}
		return lastAccNo;
	} //get the last used account number in the database

	@Override
	public String getDiscountType(int accNo) {
		String discountType = null;
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Customer WHERE Account_no = ?");
			st.setInt(1, accNo);
			ResultSet rs = st.executeQuery();
			rs.next();
			discountType = rs.getString("Discount_type");
			rs.close();
			st.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return discountType;
	}

}