package jobs;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import database.DB_Connection;
import jobs.Interface_jobs.I_Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Job implements I_Job {

	private int job_ID;
	private String job_desc;
	private String priority;
	private LocalDateTime start_time;
	private LocalDateTime completion_time;
	private LocalDateTime completion_deadline;
	private String special_instructions;
	private String job_status;
	private double sub_price;
	private double price;
	private String payment_status;
	private LocalDateTime payment_deadline;
	private DB_Connection db = new DB_Connection();
	private Connection conn = db.connect();
	private PreparedStatement Stm = null;

	/**
	 * constructor
	 *
	 * @param new_job_ID
	 * @param new_job_desc
	 * @param new_priority
	 * @param new_completion_deadline
	 * @param new_special_instructions
	 * @param new_job_status
	 * @param new_payment_status
	 */
	public Job(int new_job_ID, String new_job_desc, String new_priority, LocalDateTime new_completion_deadline, String new_special_instructions ,String new_job_status, String new_payment_status) {
		job_ID = new_job_ID;
		job_desc = new_job_desc;
		priority = new_priority;
		start_time = LocalDateTime.now();
		completion_deadline = new_completion_deadline;
		special_instructions = new_special_instructions;
		job_status = new_job_status;
		payment_status = new_payment_status;
	}

	/**
	 *
	 * @param job_ID
	 */
	public void setJob_ID(int job_ID){ this.job_ID = job_ID; }

	public int getJob_ID(){ return this.job_ID; }

	/**
	 *
	 * @param job_desc
	 */
	public void setJob_desc(String job_desc){ this.job_desc = job_desc; }

	public String getJob_desc(){ return this.job_desc; }

	/**
	 *
	 * @param priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPriority() {
		return this.priority;
	}

	/**
	 *
	 * @param start_time
	 */
	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	public LocalDateTime getStart_time() { return this.start_time; }

	/**
	 *
	 * @param completion_deadline
	 */
	public void setCompletion_deadline(LocalDateTime completion_deadline) { this.completion_deadline = completion_deadline;	}

	public LocalDateTime getCompletion_deadline() { return this.completion_deadline; }

	/**
	 *
	 * @param job_status
	 */
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getJob_status() {
		return this.job_status;
	}

	/**
	 *
	 * @param special_instructions
	 */
	public void setSpecial_instructions(String special_instructions) {
		this.special_instructions = special_instructions;
	}

	public String getSpecial_instructions() {
		return this.special_instructions;
	}

	/**
	 *
	 *  @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

	/**
	 *
	 * @param payment_status
	 */
	public void setPaymentStatus(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getPaymentStatus() {
		return this.payment_status;
	}

	/**
	 *
	 * @param completion_time
	 */
	public void setCompletion_time(LocalDateTime completion_time){ this.completion_time = completion_time; }

	public LocalDateTime getCompletion_time(){ return this.completion_time; }

	/**
	 *
	 * @param sub_price
	 */
	public void setSub_price(double sub_price){ this.sub_price = sub_price; }

	public double getSub_price(){ return this.sub_price; }

	/**
	 *
	 * @param payment_deadline
	 */
	public void setPayment_deadline(LocalDateTime payment_deadline){ this.payment_deadline = payment_deadline; }

	public LocalDateTime getPayment_deadline(){ return this.payment_deadline; }

	/**
	 * creating a new job object
	 *
	 * @param job_desc
	 * @param priority
	 * @param completion_deadline
	 * @param special_instructions
	 * @param job_status
	 * @param payment_status
	 * @param customer_account_no
	 */
	public void addJob(int job_ID, String job_desc, String priority, LocalDateTime completion_deadline, String special_instructions, String job_status, String payment_status, int customer_account_no){
		Job job1 = new Job(job_ID, job_desc, priority, completion_deadline, special_instructions, job_status, payment_status);
		saveJob(job_ID, job_desc, priority, completion_deadline, special_instructions, job_status, job1.getStart_time(), payment_status, customer_account_no);
	}

	/**
	 * save the job details in the database
	 *
	 * @param job_desc
	 * @param priority
	 * @param completion_deadline
	 * @param special_instructions
	 * @param job_status
	 * @param payment_status
	 */
	public void saveJob(int job_ID, String job_desc, String priority, LocalDateTime completion_deadline, String special_instructions, String job_status, LocalDateTime start_time, String payment_status, int customer_account_no) {
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