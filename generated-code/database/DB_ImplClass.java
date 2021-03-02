package database;
import java.sql.*;

public class DB_ImplClass implements DBConnectivity {

	public Connection connect(String sql) {
		// TODO - implement DB_ImplClass.connect
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param sql
	 * @param connect
	 */
	public ResultSet read(String sql,Connection connect) {
		// TODO - implement DB_ImplClass.read
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sql
	 * @param connect
	 */
	public int write(String sql,Connection connect) {
		// TODO - implement DB_ImplClass.write
		throw new UnsupportedOperationException();
	}

	public boolean closeConnection() {
		// TODO - implement DB_ImplClass.closeConnection
		throw new UnsupportedOperationException();
	}

	public DB_ImplClass() {
		// TODO - implement DB_ImplClass.DB_ImplClass
		throw new UnsupportedOperationException();
	}

}