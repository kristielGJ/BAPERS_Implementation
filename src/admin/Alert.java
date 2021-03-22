package admin;

import database.DB_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Alert implements I_Alert {
	private Connection conn;

	/**
	 *
	 * @param conn
	 */
	public Alert(DB_Connection conn) {
		this.conn = conn.getConn();
	}

	@Override
	public void createAlert(String name, String message, LocalDateTime time, int jobId) {
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO Alert (name, message, time, JobJob_ID) VALUES (?, ?, ?, ?)");
			st.setString(1, name);
			st.setString(2, message);
			st.setTimestamp(3, Timestamp.valueOf(time));
			st.setInt(4, jobId);
			st.executeUpdate();
			st.close();
			System.out.println("Created an alert!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(int id, String name, String message, LocalDateTime time, int jobId) {
		try {
			PreparedStatement st = conn.prepareStatement("UPDATE Alert SET name = ?, message = ?, time = ?, jobId = ? WHERE alert_id = ?");
			st.setString(1, name);
			st.setString(2, message);
			st.setTimestamp(3, Timestamp.valueOf(time));
			st.setInt(4, jobId);
			st.setInt(5, id);
			st.executeUpdate();
			st.close();
			System.out.println("Updated an alert!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet getAllAlerts() {
		ResultSet rs = null;
		try{
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Alert");
			rs = st.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet retrieveAlert(int id) {
		ResultSet rs = null;
		try {
			PreparedStatement st = conn.prepareStatement("SELECT FROM Alert WHERE id = ?");
			st.setInt(5, id);
			rs = st.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public void removeAlert(int id) {
		try {
			PreparedStatement st = conn.prepareStatement("DELETE FROM Alert WHERE id = ?");
			st.setInt(1, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}