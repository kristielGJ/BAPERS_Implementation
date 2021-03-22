package customers;

import database.DB_Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersList {

	private int numOfCustomers;

	/**
	 * 
	 * @param customerData
	 */
	public boolean addCustomer(String[] customerData) {
		Customer cM = new Customer(customerData[0], Integer.parseInt(customerData[1]), customerData[2], customerData[3]);
		cM.saveCustomerDetails(Integer.parseInt(customerData[1]), customerData);
		throw new UnsupportedOperationException();
	}

	public String[] retrieveCustomer(int Account_no) throws SQLException {
		String[] customerData = new String[4];
		DB_Connection db = new DB_Connection();
		ResultSet rs = db.query("SELECT * FROM Customers WHERE Account_no='"+ Account_no + "'");
		customerData[0] = rs.getString("Customer_name");
		customerData[1] = rs.getString("Account_no");
		customerData[2] = rs.getString("Customer_address");
		customerData[3] = rs.getString("Customer_phone");
		return customerData;
	}

	public CustomersList() {
		// TODO - implement CustomersList.CustomersList
		throw new UnsupportedOperationException();
	}

}