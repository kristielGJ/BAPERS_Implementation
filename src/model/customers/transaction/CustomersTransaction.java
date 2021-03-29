package model.customers.transaction;

import model.Model;
import model.Transaction;
import model.Utils;
import model.admin.userAccount.UserAccount;
import model.customers.Customer;
import model.database.DB_Connection;

import java.sql.*;
import java.util.ArrayList;

public class CustomersTransaction implements I_CustomersTransaction {

	private final Connection conn;

	public CustomersTransaction(DB_Connection conn) {
		this.conn = conn.getConn();
	}

	public Customer addCustomer(String name, String Address, String Phone){
		Customer cust = null;
		try {
			PreparedStatement pS = conn.prepareStatement("INSERT INTO Customer (Customer_name, Customer_address, Customer_phone, Customer_type) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pS.setString(1, name);
			pS.setString(2, Address);
			pS.setString(3, Phone);
			pS.setString(4,"Regular");
			pS.executeUpdate();
			int acc_No = Utils.getGeneratedKey(pS);
			cust = new Customer(name, acc_No, Address, Phone);
			pS.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return cust;
	}

	public Customer updateCustomer(String name, int Acc_no, String Address, String Phone, String valued){
		Customer cust = null;
		try{
			PreparedStatement pS = conn.prepareStatement(
					"UPDATE Customer SET Customer_name = ?, Customer_address = ?, Customer_phone = ?, Customer_type WHERE Account_no = ?");
			pS.setString(1, name);
			pS.setString(2, Address);
			pS.setString(3, Phone);
			pS.setString(4,valued);
			pS.setInt(5, Acc_no);
			pS.executeUpdate();
			cust = new Customer(name, Acc_no, Address, Phone);
			pS.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return cust;
	}

	@Override
	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = null;
		try{
			customers = new ArrayList<>();
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Customer");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Customer cust = new Customer(rs.getString("Customer_name"),rs.getInt("Account_no"),rs.getString("Customer_address"),rs.getString("Customer_phone"));
				customers.add(cust);
			}
			rs.close();
			st.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public Customer read(int Acc_no) {
		Customer cust = null;
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Customer WHERE Account_no = ?");
			st.setInt(1, Acc_no);
			ResultSet rs = st.executeQuery();
			cust = new Customer(rs.getString("Customer_name"),rs.getInt("Account_no"),rs.getString("Customer_address"),rs.getString("Customer_phone"));
			rs.close();
			st.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return cust;
	}

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
	}

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
	}
}