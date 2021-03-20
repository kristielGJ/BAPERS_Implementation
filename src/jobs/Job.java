package jobs;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import database.DB_Connection;
import jobs.Interface_jobs.I_Job;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Job implements I_Job {

	private Connection conn;
	private PreparedStatement Stm = null;

	/**
	 *
	 * @param conn
	 */
	public Job(DB_Connection conn) {
		this.conn = conn.getConn();
	}

	/**
	 * create and save the job details in the database
	 *
	 * @param job_desc
	 * @param priority
	 * @param completion_deadline
	 * @param special_instructions
	 * @param job_status
	 * @param payment_status
	 */
	public void createJob(int job_ID, String job_desc, String priority, LocalDateTime completion_deadline, String special_instructions, String job_status, LocalDateTime start_time, String payment_status, int customer_account_no) {
		try {
			Stm = conn.prepareStatement("INSERT INTO `bapers`.`Job` (`Job_ID`, `Job_desc`, `Urgency_level`, `Completion_deadline`, `Special_instruction`, `Status`, `Start`, `Payment_status`, `CustomerAccount_no`) VALUES (?,?,?,?,?,?,?,?,?) ");
			Stm.setInt(1,job_ID);
			Stm.setString(2, job_desc);
			Stm.setString(3, priority);
			Stm.setTimestamp(4, Timestamp.valueOf(completion_deadline));
			Stm.setString(5, special_instructions);
			Stm.setString(6, job_status);
			Stm.setTimestamp(7, Timestamp.valueOf(start_time));
			Stm.setString(8, payment_status);
			Stm.setInt(9, customer_account_no);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * retrieving the job details from the database
	 *
	 * @param job_ID
	 */
	public String[] retrieveJob(int job_ID){
		String[] job_details = new String[15];
		try {
			Stm = conn.prepareStatement("select * from Job where Job_ID = ? ");
			Stm.setInt(1,job_ID);
			ResultSet rs = Stm.executeQuery();
			while(rs.next()) {
				job_details[0] = String.valueOf(rs.getInt(1));
				job_details[1] = rs.getString(2);
				job_details[2] = rs.getString(3);
				job_details[3] = rs.getString(4);
				job_details[4] = String.valueOf(rs.getTimestamp(5));
				job_details[5] = rs.getString(6);
				job_details[6] = String.valueOf(rs.getTimestamp(7));
				job_details[7] = String.valueOf(rs.getDouble(8));
				job_details[8] = String.valueOf(rs.getDouble(9));
				job_details[9] = String.valueOf( rs.getDouble(10));
				job_details[10] = String.valueOf(rs.getTimestamp(11));
				job_details[11] = String.valueOf(rs.getTimestamp(12));
				job_details[12] = rs.getString(13);
				job_details[13] = String.valueOf(rs.getInt(14));
				job_details[14] = String.valueOf(rs.getInt(15));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return job_details;
	}

	/**
	 * updating the job status in the database
	 *
	 * @param job_ID
	 * @param job_status
	 */
	public void updateJobStatus(int job_ID, String job_status){
		try {
			Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Status = ? WHERE Job_ID =?;");
			Stm.setString(1, job_status);
			Stm.setInt(2,job_ID);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (job_status == "Completed"){
			addCompletion_time(job_ID);
		}
	}

	/**
	 * updating the priority level in the database
	 *
	 * @param job_ID
	 * @param priority
	 */
	public void updatePriority(int job_ID, String priority){
		try {
			Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Urgency_level = ? WHERE Job_ID =?;");
			Stm.setString(1, priority);
			Stm.setInt(2,job_ID);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * adding the completion_time of the job in the database
	 *
	 * @param job_ID
	 */
	public void addCompletion_time(int job_ID){
		try {
			Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Completion_date_time = ? WHERE Job_ID =?;");
			Stm.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			Stm.setInt(2,job_ID);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * adding the payment deadline in the database for valued customers
	 *
	 * @param job_ID
	 * @param payment_deadline
	 */
	public void addPaymentDeadline(int job_ID, LocalDateTime payment_deadline){
		try {
			Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Payment_deadline = ? WHERE Job_ID =?;");
			Stm.setTimestamp(1, Timestamp.valueOf(payment_deadline));
			Stm.setInt(2,job_ID);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}