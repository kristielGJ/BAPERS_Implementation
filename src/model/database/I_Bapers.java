package model.database;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public interface I_Bapers {

	/**
	 * 
	 * @param Account_no
	 */
	abstract String[] identifyCustomer(int Account_no) throws SQLException;

	/**
	 * 
	 * @param customerData
	 */
	abstract void createNewCustomer(String[] customerData) throws SQLException;

	/**
	 * 
	 * @param jobID
	 */
	abstract String verifyJob(int jobID);

	/**
	 * 
	 * @param jobID
	 */
	abstract String retrieveJobStatus(int jobID);

	/**
	 * 
	 * @param jobData
	 */
	abstract void addJob(String jobData);

	/**
	 * 
	 * @param jobID
	 * @param jobData
	 */
	abstract boolean saveJob(int jobID, String jobData);

	/**
	 * 
	 * @param jobID
	 * @param jobData
	 */
	abstract void updateStatus(int jobID, String jobData);

	/**
	 * 
	 * @param paymentData
	 */
	abstract boolean MakePayment(String paymentData);

	/**
	 * 
	 * @param type
	 */
	abstract void chooseReportType(String type);

	/**
	 * 
	 * @param reportData
	 */
	abstract String generateReport(String reportData);

	/**
	 * 
	 * @param task_data
	 */
	abstract void addTask(String task_data);

	/**
	 * 
	 * @param taskData
	 */
	abstract void extendTaskList(String taskData);

	/**
	 * 
	 * @param existing_task_ID
	 */
	abstract void removeExistingTask(int existing_task_ID);

	/**
	 * 
	 * @param existing_task_ID
	 */
	abstract void updateExistingTask(int existing_task_ID);

	/**
	 *
	 * @param name
	 * @param Acc_no
	 * @param Address
	 * @param Phone
	 */
	public void updateCustomerDetails(String name, String Acc_no, String Address, String Phone) throws SQLException;

	/**
	 * 
	 * @param task_status
	 * @param location
	 */
	abstract void updateTaskInfo(String task_status, String location);

	abstract void backup();

	abstract void restore();

	//jobs
	void createJob(int job_ID, String job_desc, String priority, String job_status, int time, String special_instructions, int customer_account_no);

	String[] retrieveJob(int job_ID);

	void updateJobStatus(int job_ID, String job_status);

	void updatePriority(int job_ID, String priority);

	void addPaymentDeadline(int job_ID, LocalDateTime payment_deadline);

	//task
	void createTask(int task_ID, String existing_task, int Job_ID, String technician, String task_status);

	String[] retrieveTasks(int task_ID);

	String[] existingTasks();

	String[] retrieveTechnicians();

	void updateTaskStatus(int Task_ID, String Task_status);

	//existing tasks
	void createExistingTask(int exg_Task_ID, String task_description, double task_price, int task_duration, String department_name);

	String[] retrieveExistingTask(int existing_task_ID);

	void deleteExistingTask(int existing_task_ID);

	void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name);

	//payment
	void createPayment(int payment_ID, String payment_type, Double payment_amount, int customer_ID);

	String[] generateInvoice(int payment_ID);

	void createCash_payment(int payment_ID, double payment_amount, int customer_ID);

	void createCard_payment(int card_ID, int payment_ID, String card_type, String expiry_date, String last_digits, double payment_amount, int customer_ID);

	//reports
	HashMap<String, HashMap<Integer, String[][]>> generateIndividualPerformanceReport(LocalDate from_date, LocalDate to_date);

	HashMap<Integer, HashMap<Integer, String[][]>> generateJobPerformanceReport(LocalDate from_date, LocalDate to_date, int customer_acc_no);

	HashMap<String, String[]> generateCopy_room_performance(LocalDate from_date, LocalDate to_date);

	HashMap<String, String[]> generateDevelopment_performance(LocalDate from_date, LocalDate to_date);

	HashMap<String, String[]> generateFinishing_performance(LocalDate from_date, LocalDate to_date);

	HashMap<String, String[]> generatePacking_performance(LocalDate from_date, LocalDate to_date);



}