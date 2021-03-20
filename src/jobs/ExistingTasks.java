package jobs;

import database.DB_Connection;
import jobs.Interface_jobs.I_ExistingTasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExistingTasks implements I_ExistingTasks {

	private Connection conn;
	private PreparedStatement Stm = null;

	/**
	 *
	 * @param conn
	 */
	public ExistingTasks(DB_Connection conn) {
		this.conn = conn.getConn();
	}

	/**
	 * create and save the task details in the database
	 *
	 * @param exg_Task_ID
	 * @param task_description
	 * @param task_price
	 * @param task_duration
	 * @param department_name
	 */
	public void createExistingTask(int exg_Task_ID, String task_description, double task_price, int task_duration, String department_name){
		try {
			Stm = conn.prepareStatement("INSERT INTO `bapers`.`Task_Catalog` (`Catalog_ID`, `Task_name`, `Price`, `Task_department`, `Task_duration`) VALUES (?,?,?,?,?) ");
			Stm.setInt(1, exg_Task_ID);
			Stm.setString(2, task_description);
			Stm.setDouble(3, task_price);
			Stm.setString(4, department_name);
			Stm.setInt(5, task_duration);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * retrieving the task details from the database
	 *
	 * @param existing_task_ID
	 */
	public String[] retrieveExistingTask(int existing_task_ID) {
		String[] existing_task_details = new String[5];
		try {
			Stm = conn.prepareStatement("select * from Task_Catalog where Catalog_ID = ? ");
			Stm.setInt(1,existing_task_ID);
			ResultSet rs = Stm.executeQuery();
			while (rs.next()){
				existing_task_details[0] = String.valueOf(rs.getInt(1));
				existing_task_details[1] = rs.getString(2);
				existing_task_details[2] = String.valueOf(rs.getDouble(3));
				existing_task_details[3] = rs.getString(4);
				existing_task_details[4] = String.valueOf(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return existing_task_details;
	}

	/**
	 * removing the task from the database
	 *
	 * @param existing_task_ID
	 */
	public void removeExistingTask(int existing_task_ID) {
		try {
			Stm = conn.prepareStatement("DELETE FROM `bapers`.`Task_Catalog` WHERE Catalog_ID =?;");
			Stm.setInt(1,existing_task_ID);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * update the task details in the database
	 *
	 * @param existing_task_ID
	 * @param task_name
	 * @param task_price
	 * @param task_duration
	 * @param department_name
	 */
	public void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name) {
		try {
			Stm = conn.prepareStatement("UPDATE `bapers`.`Task_Catalog` SET Task_name = ?, Price = ?, Task_department = ?, Task_duration = ? WHERE Catalog_ID =?;");
			Stm.setString(1, task_name);
			Stm.setDouble(2, task_price);
			Stm.setString(3, department_name);
			Stm.setInt(4, task_duration);
			Stm.setInt(5, existing_task_ID);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}