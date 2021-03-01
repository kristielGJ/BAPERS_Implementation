package Database;

public abstract interface DBConnectivity {

	/**
	 * 
	 * @param String
	 */
	abstract resultSet read(SQL String);

	/**
	 * 
	 * @param String
	 */
	abstract int write(SQL String);

	abstract connection connect();

	/**
	 * 
	 * @param connect
	 */
	abstract ResultSet closeConnection(connection connect);

}