package database;
import java.sql.*;

/**
 * This class is the abstract interface for database connectivity.
 * All interaction with database must inherit this structure at it's base.
 */

public interface DB_Connectivity {
	boolean closeConnection();

	Connection connect();

	ResultSet query(String sql);

	int update(String sql);

}