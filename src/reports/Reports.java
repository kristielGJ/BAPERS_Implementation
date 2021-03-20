package reports;

import database.DB_Connection;

import java.sql.Connection;

public class Reports {

	protected Connection conn;

	/**
	 *
	 * @param conn
	 */
	public Reports(DB_Connection conn) {
		this.conn = conn.getConn();
	}

}