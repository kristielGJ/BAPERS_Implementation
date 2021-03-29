package model.database;

import model.jobs.existing_tasks.ExistingTasks;
import model.jobs.job.Job;
import model.jobs.task.Task_List;
import reports.individual_performance_report.IndividualPerformanceReport;
import reports.job_performance_report.JobPerformanceReport;
import reports.summary_performance_report.SummaryPerformanceReport;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

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
	 * @param name
	 * @param Acc_no
	 * @param Address
	 * @param Phone
	 */
	public void updateCustomerDetails(String name, String Acc_no, String Address, String Phone) throws SQLException;

	abstract void backup();

	abstract void restore();

	//creates a new job
	void createJob(int job_ID, String job_desc, String priority, String job_status, int time, String special_instructions, int customer_account_no);

	//update the job status
	void updateJobStatus(int job_ID, String job_status);

	//return active jobs which are not completed
	ArrayList<Job> getActiveJobs();

	//creates a new task
	void createTask(String existing_task, int Job_ID, String technician, String task_status);

	//returns the list of existing tasks
	String[] existingTasks();

	//returns the list of technicians
	String[] retrieveTechnicians();

	//updates the task status
	void updateTaskStatus(int Task_ID, String Task_status);

	// return the list of tasks
	ArrayList<Task_List> getAllTasks(int job_ID);

	//return the list of jobs
	ArrayList<Job> getJobs(int customer_id, String type);

	//creates a new existing task
	void createExistingTask(String task_description, double task_price, int task_duration, String department_name);

	//return the list of existing tasks
	ArrayList<ExistingTasks> getExistingTasks();

	//remove the existing task from the database
	void deleteExistingTask(int existing_task_ID);

	//updates the existing task
	void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name);

	//creates a new payment
	int createPayment(String payment_type, Double payment_amount, Date date, int job_ID);

	//generates a payment invoice
	String[] generateInvoice(int payment_ID);

	//creates a new card payment
	void createCard_payment(int payment_id, String card_type, String expiry_date, String last_digits, double payment_amount, Date date, int job_ID);

	//generates job sheet
	ArrayList<JobPerformanceReport> generateJobSheet(LocalDate from_date, LocalDate to_date, int customer_id);

	//generates individual performance report
	ArrayList<IndividualPerformanceReport> generateIndividualPerformance_Report(LocalDate from_date, LocalDate to_date);

	//generates summary performance report
	ArrayList<SummaryPerformanceReport> generateSummaryPerformanceReport(LocalDate from_date, LocalDate to_date, LocalTime from_time, LocalTime to_time);

}