package model.database;
import java.sql.*;

/**
 * Mushfikur Rahman
 * This class is the abstract interface for model.database connectivity.
 * All interaction with model.database must inherit this structure at it's base.
 */

public interface DB_Connectivity {
	boolean closeConnection();

	Connection connect();

	ResultSet query(String sql);

	int update(String sql);

}