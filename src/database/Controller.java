package database;

import admin.Alert;
import jobs.*;
import reports.IndividualPerformanceReport;
import reports.JobPerformanceReport;
import reports.Reports;
import reports.SummaryPerformanceReport;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Controller implements I_Bapers {

	private static final DB_Connection mainConn = new DB_Connection();

	public void main() {

	}

	/**
	 *
	 * @param data
	 */
	public String identifyCustomer(String data) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerData
	 */
	public int createNewCustomer(String customerData) {
		// TODO - implement Controller.createNewCustomer
		throw new UnsupportedOperationException();
	}



	Alert alert = new Alert(mainConn);
	public void createAlert(String type, String message, LocalDateTime time, int jobId) {
		alert.createAlert(type, message, time, jobId);
	}

	public ResultSet getAlert(int id) {
		return alert.retrieveAlert(id);
	}

	/**
	 * 
	 * @param type
	 */
	public void chooseReportType(String type) {
		// TODO - implement Controller.chooseReportType
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param reportData
	 */
	public String generateReport(String reportData) {
		// TODO - implement Controller.generateReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customerData
	 */
	public void updateCustomerDetails(String customerData) {
		// TODO - implement Controller.updateCustomerDetails
		throw new UnsupportedOperationException();
	}

	public void backup() {
		// TODO - implement Controller.backup
		throw new UnsupportedOperationException();
	}

	public void restore() {
		// TODO - implement Controller.restore
		throw new UnsupportedOperationException();
	}

	Job job = new Job(mainConn);

	public void createJob(int job_ID, String job_desc, String priority, LocalDateTime completion_deadline, String special_instructions, String job_status, LocalDateTime start_time, String payment_status, int customer_account_no){
		job.createJob(job_ID,job_desc, priority, completion_deadline, special_instructions, job_status, start_time, payment_status, customer_account_no);
	}

	public String[] retrieveJob(int job_ID){
		return job.retrieveJob(job_ID);
	}

	public void updateJobStatus(int job_ID, String job_status){
		job.updateJobStatus(job_ID, job_status);
	}

	public void updatePriority(int job_ID, String priority){
		job.updatePriority(job_ID, priority);
	}

	public void addCompletion_time(int job_ID){
		job.addCompletion_time(job_ID);
	}

	public void addPaymentDeadline(int job_ID, LocalDateTime payment_deadline){
		job.addPaymentDeadline(job_ID, payment_deadline);
	}

	Task task = new Task(mainConn);

	public void createTask(int task_ID, int ExistingTask_ID, int Job_ID, int user_ID, String task_status){
		task.createTask(task_ID, ExistingTask_ID, Job_ID, user_ID, task_status);
	}

	public String[] retrieveTasks(int task_ID){
		return task.retrieveTasks(task_ID);
	}

	public void updateTaskStatus(int Task_ID, String Task_status){
		task.updateTaskStatus(Task_ID, Task_status);
	}

	ExistingTasks existingTask = new ExistingTasks(mainConn);

	public void createExistingTask(int exg_Task_ID, String task_description, double task_price, int task_duration, String department_name){
		existingTask.createExistingTask(exg_Task_ID, task_description, task_price, task_duration, department_name);
	}

	public String[] retrieveExistingTask(int existing_task_ID){
		return existingTask.retrieveExistingTask(existing_task_ID);
	}

	public void removeExistingTask(int existing_task_ID){
		existingTask.removeExistingTask(existing_task_ID);
	}

	public void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name){
		existingTask.updateExistingTask(existing_task_ID, task_name, task_price, task_duration, department_name);
	}

	Payment payment = new Payment(mainConn);

	public void createPayment(int payment_ID, String payment_type, Double payment_amount, int customer_ID){
		payment.createPayment(payment_ID, payment_type, payment_amount, customer_ID);
	}

	public String[] generateInvoice(int payment_ID){
		return payment.generateInvoice(payment_ID);
	}

	Cash_payment cash_payment = new Cash_payment(mainConn);

	public void createCash_payment(int payment_ID, double payment_amount, int customer_ID){
		cash_payment.createCash_payment(payment_ID, payment_amount, customer_ID);
	}

	Card_payment card_payment = new Card_payment(mainConn);

	public  void createCard_payment(int card_ID, int payment_ID, String card_type, String expiry_date, String last_digits, double payment_amount, int customer_ID){
		card_payment.createCard_payment(card_ID, payment_ID, card_type, expiry_date, last_digits, payment_amount, customer_ID);
	}

	Reports report = new Reports(mainConn);

	IndividualPerformanceReport individualPerformanceReport = new IndividualPerformanceReport(mainConn);

	public HashMap<String, HashMap<Integer, String[][]>> generateIndividualPerformanceReport(LocalDate from_date, LocalDate to_date){
		return individualPerformanceReport.generateIndividualPerformanceReport(from_date, to_date);
	}

	JobPerformanceReport jobPerformanceReport = new JobPerformanceReport(mainConn);

	public HashMap<Integer, HashMap<Integer, String[][]>> generateJobPerformanceReport(LocalDate from_date, LocalDate to_date){
		return jobPerformanceReport.generateJobPerformanceReport(from_date, to_date);
	}

	SummaryPerformanceReport summaryPerformanceReport = new SummaryPerformanceReport(mainConn);

	public void generateSummaryPerformanceReport(LocalDate from_date, LocalDate to_date){
		summaryPerformanceReport.generateSummaryPerformanceReport(from_date, to_date);
	}

	public Controller() {
	}

}