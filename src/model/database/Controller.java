package model.database;

import model.jobs.card_payment.transaction.Card_payment_Transaction;
import model.jobs.card_payment.transaction.I_Card_payment_Transaction;
import model.jobs.existing_tasks.ExistingTasks;
import model.jobs.existing_tasks.transaction.ExistingTasks_Transaction;
import model.jobs.existing_tasks.transaction.I_ExistingTasks_Transaction;
import model.jobs.job.Job;
import model.jobs.job.transaction.I_Job_Transaction;
import model.jobs.job.transaction.Job_Transaction;
import model.jobs.payment.transaction.I_Payment_Transaction;
import model.jobs.payment.transaction.Payment_Transaction;
import model.jobs.task.Task_List;
import model.jobs.task.transaction.I_Task_Transaction;
import model.jobs.task.transaction.Task_Transaction;
import reports.individual_performance_report.IndividualPerformanceReport;
import reports.individual_performance_report.transaction.I_IndividualPerformanceReport_Transaction;
import reports.individual_performance_report.transaction.IndividualPerformanceReport_Transaction;
import reports.job_performance_report.JobPerformanceReport;
import reports.job_performance_report.transaction.I_JobPerformanceReport_Transaction;
import reports.job_performance_report.transaction.JobPerformanceReport_Transaction;
import reports.summary_performance_report.SummaryPerformanceReport;
import reports.summary_performance_report.transaction.I_SummaryPerformanceReport_Transaction;
import reports.summary_performance_report.transaction.SummaryPerformanceReport_Transaction;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Controller implements I_Bapers {

	private static final DB_Connection mainConn = new DB_Connection();
	private I_Job_Transaction job = new Job_Transaction(mainConn);
	private I_Task_Transaction task = new Task_Transaction(mainConn);
	private I_ExistingTasks_Transaction existingTask = new ExistingTasks_Transaction(mainConn);
	private I_Payment_Transaction payment = new Payment_Transaction(mainConn);
	private I_Card_payment_Transaction card_payment = new Card_payment_Transaction(mainConn);
	private I_IndividualPerformanceReport_Transaction individualPerformanceReport = new IndividualPerformanceReport_Transaction(mainConn);
	private I_JobPerformanceReport_Transaction jobPerformanceReport = new JobPerformanceReport_Transaction(mainConn);
	private I_SummaryPerformanceReport_Transaction summaryPerformanceReport = new SummaryPerformanceReport_Transaction(mainConn);

	public void main() {

	}

	/**
	 *
	 */
	public String[] identifyCustomer(int Account_no) throws SQLException {
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param customerData
	 */
	public void createNewCustomer(String[] customerData) throws SQLException {
	}

	@Override
	public void updateCustomerDetails(String name, String Acc_no, String Address, String Phone) throws SQLException {

	}

	/**
	 *
	 * @param customerData
	 */
	public void updateCustomerDetails(String[] customerData) throws SQLException {
		//cL.updateCustomer(customerData);
	}

	public void backup() {
		// TODO - implement Controller.backup
		throw new UnsupportedOperationException();
	}

	public void restore() {
		// TODO - implement Controller.restore
		throw new UnsupportedOperationException();
	}

	//creates a new job
	public void createJob(int job_ID, String job_desc, String priority, String job_status, int time, String special_instructions, int customer_account_no){
		job.addJob(job_ID, job_desc, priority, job_status, time, special_instructions, customer_account_no);
	}

	//updates the job status
	public void updateJobStatus(int job_ID, String job_status){
		job.updateJobStatus(job_ID, job_status);
	}

	//returns a list of jobs which are not completed
	public ArrayList<Job> getActiveJobs(){
		return job.getActiveJobs();
	}

	//returns a list of jobs(completed or all)
	public ArrayList<Job> getJobs(int customer_id, String type){
		return job.getJobs(customer_id, type);
	}

	//creates a new task
	public void createTask(String existing_task, int Job_ID, String technician, String task_status){
		task.addTask(existing_task, Job_ID, technician, task_status);
	}

	// returns a list of existing task names
	public String[] existingTasks(){
		return task.retrieveExistingTasks();
	}

	//returns a list of technician names
	public String[] retrieveTechnicians(){
		return task.retrieveTechnicians();
	}

	//returns a list of task associated with a job
	public ArrayList<Task_List> getAllTasks(int job_ID){
		return task.getAllTasks(job_ID);
	}

	//update the task status
	public void updateTaskStatus(int Task_ID, String Task_status){
		task.updateTaskStatus(Task_ID, Task_status);
	}

	//creates a new existing task
	public void createExistingTask(String task_description, double task_price, int task_duration, String department_name){
		existingTask.extendTaskList(task_description, task_price, task_duration, department_name);
	}

	//returns a list of existing task with details
	public ArrayList<ExistingTasks> getExistingTasks(){
		return existingTask.getExistingTasks();
	}

	//deletes a existing task
	public void deleteExistingTask(int existing_task_ID){
		existingTask.removeExistingTask(existing_task_ID);
	}

	//updates a existing task
	public void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name){
		existingTask.updateExistingTask(existing_task_ID, task_name, task_price, task_duration, department_name);
	}

	//creates a new payment
	public int createPayment(String payment_type, Double payment_amount, Date date, int job_ID){
		return payment.makePayment(payment_amount, payment_type, date, job_ID);
	}

	//generates a invoice
	public String[] generateInvoice(int payment_ID){
		return payment.generateInvoice(payment_ID);
	}

	//creates a new card payment
	public  void createCard_payment(int payment_id, String card_type, String expiry_date, String last_digits, double payment_amount, Date date, int job_ID){
		card_payment.makeCard_payment(payment_id, payment_amount, card_type, expiry_date, last_digits, date, job_ID);
	}

	//generates job sheet
	public ArrayList<JobPerformanceReport> generateJobSheet(LocalDate from_date, LocalDate to_date, int customer_id){
		return jobPerformanceReport.generateJobSheet(from_date, to_date, customer_id);
	}

	//generate individual performance report
	public ArrayList<IndividualPerformanceReport> generateIndividualPerformance_Report(LocalDate from_date, LocalDate to_date){
		return individualPerformanceReport.generateIndividualPerformance_Report(from_date, to_date);
	}

	//generated summary performance report
	public ArrayList<SummaryPerformanceReport> generateSummaryPerformanceReport(LocalDate from_date, LocalDate to_date, LocalTime from_time, LocalTime to_time){
		return summaryPerformanceReport.generateSummaryPerformanceReport(from_date, to_date, from_time, to_time);
	}

	public Controller() {
	}

}