package model.jobs;

import model.database.DB_Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Task {

	private int task_ID;
	private String task_status;
	private LocalDateTime start_time;
	private LocalDateTime finish_time;
	private static DB_Connection conn = new DB_Connection();
	private static PreparedStatement Stm = null;

	/**
	 * constructor
	 *
	 * @param new_task_ID
	 * @param new_task_status
	 */
	public Task(int new_task_ID, String new_task_status) {
		task_ID = new_task_ID;
		task_status = new_task_status;
	}

	/**
	 *
	 * @param task_ID
	 */
	public void setTask_ID(int task_ID) { this.task_ID = task_ID; }

	public int getTask_ID(){ return this.task_ID; }

	/**
	 *
	 * @param task_status
	 */
	public void setTask_status(String task_status) { this.task_status = task_status; }

	public String getTask_status() { return this.task_status; }

	/**
	 *
	 * @param start_time
	 */
	public void setStart_time(LocalDateTime start_time){ this.start_time = start_time; }

	public LocalDateTime getStart_time(){ return this.start_time; }

	/**
	 *
	 * @param finish_time
	 */
	public void setFinish_time(LocalDateTime finish_time){ this.finish_time = finish_time; }

	public LocalDateTime getFinish_time(){ return this.finish_time; }


	/**
	 * store the starting time of the task in the model.database
	 *
	 * @param Task_ID
	 */
	/*public static void start(int Task_ID) {
		try {
			Stm = conn.connect().prepareStatement("UPDATE `bapers`.`Task` SET Task_start = ?WHERE Task_ID =?;");
			Stm.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			Stm.setInt(2,Task_ID);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * stores the finish_time of the task in the model.database
	 *
	 * @param Task_ID
	 */
	public static void finish( int Task_ID) {
		try {
			Stm = conn.connect().prepareStatement("UPDATE `bapers`.`Task` SET Task_completion = ?WHERE Task_ID =?;");
			Stm.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			Stm.setInt(2,Task_ID);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * creating a new object Task
	 *
	 * @param task_ID
	 * @param task_status
	 * @param Job_ID
	 * @param user_ID
	 * @param ExistingTask_ID
	 */
	public static void addTask(int task_ID, int ExistingTask_ID, int Job_ID, int user_ID, String task_status) {
		new Task(task_ID, task_status);
		storeTaskDetails(task_ID, ExistingTask_ID, Job_ID, user_ID, task_status);
	}

	/**
	 * store task details in the model.database
	 *
	 * @param task_ID
	 * @param ExistingTask_ID
	 * @param task_status
	 * @param Job_ID
	 */
	public static void storeTaskDetails(int task_ID, int ExistingTask_ID, int Job_ID, int user_ID, String task_status) {
		try {
			Stm = conn.connect().prepareStatement("INSERT INTO `bapers`.`Task` (`Task_ID` ,`Task_status`,`Task_start`, `User_accountUser_ID`,`Task_CatalogCatalog_ID`, `JobJob_ID`) VALUES (?,?,?,?,?,?) ");
			Stm.setInt(1, task_ID);
			Stm.setString(2, task_status);
			Stm.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			Stm.setInt(4, user_ID);
			Stm.setInt(5, ExistingTask_ID);
			Stm.setInt(6, Job_ID);
			Stm.executeUpdate();
			calculateSub_price(Job_ID, ExistingTask_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * retrieving the pre-stored task in the model.database
	 *
	 * @param task_ID
	 */
	public static String[] retrieveTasks(int task_ID) {
		String[] task_details = new String[8];
		try {
			Stm = conn.connect().prepareStatement("select * from Task where Task_ID = ? ");
			Stm.setInt(1,task_ID);
			ResultSet rs = Stm.executeQuery();
			while(rs.next()){
				task_details[0] = String.valueOf(rs.getInt(1));
				task_details[1] = rs.getString(2);
				task_details[2] = String.valueOf(rs.getDouble(3));
				task_details[3] = String.valueOf(rs.getTimestamp(4));
				task_details[4] = String.valueOf(rs.getTimestamp(5));
				task_details[5] = String.valueOf(rs.getInt(6));
				task_details[6] = String.valueOf(rs.getInt(7));
				task_details[7] = String.valueOf(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return task_details;
	}

	/**
	 * updating task status in the model.database
	 *
	 * @param Task_status
	 * @param Task_ID
	 */
	public static void updateTaskStatus(int Task_ID, String Task_status) {
		try {
			Stm = conn.connect().prepareStatement("UPDATE `bapers`.`Task` SET Task_status = ? WHERE Task_ID =?;");
			Stm.setString(1, Task_status);
			Stm.setInt(2,Task_ID);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (Task_status == "Completed"){
			finish(Task_ID);
		}
	}

	public static void calculateSub_price(int job_ID, int ExistingTask_ID){
		try {
			double task_price = 0;
			double subtotal_price = 0;

			Stm = conn.connect().prepareStatement("select Price from Task_Catalog where Catalog_ID = ? ");
			Stm.setInt(1, ExistingTask_ID);
			ResultSet rs = Stm.executeQuery();
			while(rs.next()){
				task_price = rs.getDouble("Price");
			}

			Stm = conn.connect().prepareStatement("select Subtotal_price from Job where Job_ID = ? ");
			Stm.setInt(1, job_ID);
			ResultSet rs2 = Stm.executeQuery();
			while(rs2.next()){
				subtotal_price = rs2.getDouble("Subtotal_price");
			}

			subtotal_price = subtotal_price + task_price;

			Stm = conn.connect().prepareStatement("UPDATE `bapers`.`Job` SET Subtotal_price = ? WHERE Job_ID =?;");
			Stm.setDouble(1, subtotal_price);
			Stm.setInt(2,job_ID);
			Stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}