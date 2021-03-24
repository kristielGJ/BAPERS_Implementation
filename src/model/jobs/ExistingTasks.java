package model.jobs;

import model.database.DB_Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExistingTasks {

	private int exg_Task_ID;
	private String task_description;
	private double task_price;
	private int task_duration;
	private String department_name;
	private static DB_Connection conn = new DB_Connection();
	private static PreparedStatement Stm = null;

	/**
	 * constructor
	 *
	 * @param new_exg_task_ID
	 * @param new_task_description
	 * @param new_task_price
	 * @param new_task_duration
	 * @param new_department_name
	 */
	public ExistingTasks(int new_exg_task_ID, String new_task_description, double new_task_price, int new_task_duration, String new_department_name) {
		exg_Task_ID = new_exg_task_ID;
		task_description = new_task_description;
		task_price = new_task_price;
		task_duration = new_task_duration;
		department_name = new_department_name;
	}

	/**
	 *
	 * @param exg_Task_ID
	 */
	public void setExg_Task_ID(int exg_Task_ID){ this.exg_Task_ID = exg_Task_ID; }

	public int getExg_Task_ID(){ return this.exg_Task_ID; }

	/**
	 *
	 * @param task_description
	 */
	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}

	public String getTask_description() {
		return this.task_description;
	}

	/**
	 *
	 * @param task_price
	 */
	public void setTask_price(double task_price) {
		this.task_price = task_price;
	}

	public double getTask_price() {
		return this.task_price;
	}

	/**
	 *
	 * @param task_duration
	 */
	public void setTask_duration(int task_duration) {
		this.task_duration = task_duration;
	}

	public int getTask_duration() { return this.task_duration; }

	/**
	 *
	 * @param department_name
	 */
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getDepartment_name() {
		return this.department_name;
	}

	/**
	 * creating a new ExistingTasks object
	 *
	 * @param exg_Task_ID
	 * @param task_description
	 * @param task_price
	 * @param task_duration
	 * @param department_name
	 */
	public static void extendTaskList(int exg_Task_ID, String task_description, double task_price, int task_duration, String department_name){
		new ExistingTasks(exg_Task_ID, task_description, task_price, task_duration, department_name);
		saveTask(exg_Task_ID, task_description, task_price, task_duration, department_name);
	}

	/**
	 * save the task details in the model.database
	 *
	 * @param exg_Task_ID
	 * @param task_description
	 * @param task_price
	 * @param task_duration
	 * @param department_name
	 */
	public static void saveTask(int exg_Task_ID, String task_description, double task_price, int task_duration, String department_name){
		try {
			Stm = conn.connect().prepareStatement("INSERT INTO `bapers`.`Task_Catalog` (`Catalog_ID`, `Task_name`, `Price`, `Task_department`, `Task_duration`) VALUES (?,?,?,?,?) ");
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
	 * retrieving the task details from the model.database
	 *
	 * @param existing_task_ID
	 */
	public static String[] retrieveExistingTask(int existing_task_ID) {
		String[] existing_task_details = new String[5];
		try {
			Stm = conn.connect().prepareStatement("select * from Task_Catalog where Catalog_ID = ? ");
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
	 * removing the task from the model.database
	 *
	 * @param existing_task_ID
	 */
	public static void removeExistingTask(int existing_task_ID) {
		try {
			Stm = conn.connect().prepareStatement("DELETE FROM `bapers`.`Task_Catalog` WHERE Catalog_ID =?;");
			Stm.setInt(1,existing_task_ID);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * update the task details in the model.database
	 *
	 * @param existing_task_ID
	 * @param task_name
	 * @param task_price
	 * @param task_duration
	 * @param department_name
	 */
	public static void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name) {
		try {
			Stm = conn.connect().prepareStatement("UPDATE `bapers`.`Task_Catalog` SET Task_name = ?, Price = ?, Task_department = ?, Task_duration = ? WHERE Catalog_ID =?;");
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