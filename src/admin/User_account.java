package admin;

import database.DB_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class User_account {

	private Connection conn;
	private int user_ID;
	private String name;
	private String username;
	private String password;
	private String role;

	public User_account(DB_Connection conn) {
		this.conn = conn.getConn();
	}

	public void createUserAccount(String name, String email, int phone, String role) {
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO User_account (name, email, phone, role) VALUES (?, ?, ?, ?)");
			st.setString(1, name);
			st.setString(2, email);
			st.setInt(3, phone);
			st.setString(4, role);
			st.executeUpdate();
			st.close();
			System.out.println("Created a user account!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeUserAccount(int id) {
		try {
			PreparedStatement st = conn.prepareStatement("DELETE FROM User_account WHERE id = ?");
			st.setInt(1, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateRole(String role, int id) {
		try{
			PreparedStatement st = conn.prepareStatement("UPDATE User_account SET role = ? WHERE user_account_id = ?");
			st.setString(1, role);
			st.setInt(2, id);
			st.executeUpdate();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}