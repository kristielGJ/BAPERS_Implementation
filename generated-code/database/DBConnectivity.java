package database;
import java.sql.*;

public abstract interface DBConnectivity {
	public boolean closeConnection();

	public Connection connect(String sql);

	public ResultSet read(String sql, Connection conn);

	public int write(String sql, Connection conn);

}