package model.database;

import model.admin.alert.Alert;
//import model.customers.CustomersList;
import model.jobs.card_payment.transaction.Card_payment_Transaction;
import model.jobs.card_payment.transaction.I_Card_payment_Transaction;
import model.jobs.cash_payment.transaction.Cash_payment_Transaction;
import model.jobs.cash_payment.transaction.I_Cash_payment_Transaction;
import model.jobs.existing_tasks.transaction.ExistingTasks_Transaction;
import model.jobs.existing_tasks.transaction.I_ExistingTasks_Transaction;
import model.jobs.job.transaction.I_Job_Transaction;
import model.jobs.job.transaction.Job_Transaction;
import model.jobs.payment.transaction.I_Payment_Transaction;
import model.jobs.payment.transaction.Payment_Transaction;
import model.jobs.task.transaction.I_Task_Transaction;
import model.jobs.task.transaction.Task_Transaction;
import reports.individual_performance_report.transaction.I_IndividualPerformanceReport_Transaction;
import reports.individual_performance_report.transaction.IndividualPerformanceReport_Transaction;
import reports.job_performance_report.transaction.I_JobPerformanceReport_Transaction;
import reports.job_performance_report.transaction.JobPerformanceReport_Transaction;
import reports.summary_performance_report.transaction.I_SummaryPerformanceReport_Transaction;
import reports.summary_performance_report.transaction.SummaryPerformanceReport_Transaction;

import javax.swing.text.html.CSS;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Controller implements I_Bapers {

	private static final DB_Connection mainConn = new DB_Connection();

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

	/**
	 *
	 * @param jobID
	 */
	public String verifyJob(int jobID) {
		// TODO - implement Controller.verifyJob
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param jobID
	 */
	public String retrieveJobStatus(int jobID) {
		// TODO - implement Controller.retrieveJobStatus
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param jobData
	 */
	public void addJob(String jobData) {

	}
	/**
	 *
	 * @param jobID
	 * @param jobData
	 */
	public boolean saveJob(int jobID, String jobData) {

		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param jobID
	 * @param priority
	 */
	public void updateStatus(int jobID, String priority) {
		// TODO - implement Controller.updateStatus
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean MakePayment(String paymentData) {
		return false;
	}

	/**
	 *
	 * @param paymentData
	 */
	public boolean makePayment(String paymentData) {
		// TODO - implement Controller.MakePayment
		throw new UnsupportedOperationException();
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
	 * @param task_data
	 */
	public void addTask(String task_data) {
		// TODO - implement Controller.addTask
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param taskData
	 */
	public void extendTaskList(String taskData) {
		// TODO - implement Controller.extendTaskList
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param existing_task_ID
	 */
	public void removeExistingTask(int existing_task_ID) {
		// TODO - implement Controller.removeExistingTask
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param existing_task_ID
	 */
	public void updateExistingTask(int existing_task_ID) {
		// TODO - implement Controller.updateExistingTask
		throw new UnsupportedOperationException();
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

	/**
	 *
	 * @param task_status
	 * @param location
	 */
	public void updateTaskInfo(String task_status, String location) {
		// TODO - implement Controller.updateTaskInfo
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

	I_Job_Transaction job = new Job_Transaction(mainConn);

	public void createJob(int job_ID, String job_desc, String priority, String job_status, int time, String special_instructions, int customer_account_no){
		job.addJob(job_ID, job_desc, priority, job_status, time, special_instructions, customer_account_no);
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

	public void addPaymentDeadline(int job_ID, LocalDateTime payment_deadline){
		job.addPaymentDeadline(job_ID, payment_deadline);
	}

	I_Task_Transaction task = new Task_Transaction(mainConn);

	public void createTask(int task_ID, String existing_task, int Job_ID, String technician, String task_status){
		task.addTask(task_ID, existing_task, Job_ID, technician, task_status);
	}

	public String[] retrieveTasks(int task_ID){
		return task.retrieveTasks(task_ID);
	}

	public String[] existingTasks(){
		return task.retrieveExistingTasks();
	}

	public String[] retrieveTechnicians(){
		return task.retrieveTechnicians();
	}

	public void updateTaskStatus(int Task_ID, String Task_status){
		task.updateTaskStatus(Task_ID, Task_status);
	}

	I_ExistingTasks_Transaction existingTask = new ExistingTasks_Transaction(mainConn);

	public void createExistingTask(int exg_Task_ID, String task_description, double task_price, int task_duration, String department_name){
		existingTask.extendTaskList(exg_Task_ID, task_description, task_price, task_duration, department_name);
	}

	public String[] retrieveExistingTask(int existing_task_ID){
		return existingTask.retrieveExistingTask(existing_task_ID);
	}

	public void deleteExistingTask(int existing_task_ID){
		existingTask.removeExistingTask(existing_task_ID);
	}

	public void updateExistingTask(int existing_task_ID, String task_name, Double task_price, int task_duration, String department_name){
		existingTask.updateExistingTask(existing_task_ID, task_name, task_price, task_duration, department_name);
	}

	I_Payment_Transaction payment = new Payment_Transaction(mainConn);

	public void createPayment(int payment_ID, String payment_type, Double payment_amount, int customer_ID){
		payment.makePayment(payment_ID, payment_amount, payment_type, customer_ID);
	}

	public String[] generateInvoice(int payment_ID){
		return payment.generateInvoice(payment_ID);
	}

	I_Cash_payment_Transaction cash_payment = new Cash_payment_Transaction(mainConn);

	public void createCash_payment(int payment_ID, double payment_amount, int customer_ID){
		cash_payment.makeCash_payment(payment_ID, payment_amount, customer_ID);
	}

	I_Card_payment_Transaction card_payment = new Card_payment_Transaction(mainConn);

	public  void createCard_payment(int card_ID, int payment_ID, String card_type, String expiry_date, String last_digits, double payment_amount, int customer_ID){
		card_payment.makeCard_payment(card_ID, payment_ID, payment_amount, card_type, expiry_date, last_digits, customer_ID);
	}

	I_IndividualPerformanceReport_Transaction individualPerformanceReport = new IndividualPerformanceReport_Transaction(mainConn);

	public HashMap<String, HashMap<Integer, String[][]>> generateIndividualPerformanceReport(LocalDate from_date, LocalDate to_date){
		return individualPerformanceReport.generateIndividualPerformanceReport(from_date, to_date);
	}

	I_JobPerformanceReport_Transaction jobPerformanceReport = new JobPerformanceReport_Transaction(mainConn);

	public HashMap<Integer, HashMap<Integer, String[][]>> generateJobPerformanceReport(LocalDate from_date, LocalDate to_date, int customer_acc_no){
		return jobPerformanceReport.generateJobPerformanceReport(from_date, to_date, customer_acc_no);
	}

	I_SummaryPerformanceReport_Transaction summaryPerformanceReport = new SummaryPerformanceReport_Transaction(mainConn);

	public HashMap<String, String[]> generateCopy_room_performance(LocalDate from_date, LocalDate to_date){
		return summaryPerformanceReport.copy_room_performance(from_date, to_date);
	}

	public HashMap<String, String[]> generateDevelopment_performance(LocalDate from_date, LocalDate to_date){
		return summaryPerformanceReport.development_performance(from_date, to_date);
	}

	public HashMap<String, String[]> generateFinishing_performance(LocalDate from_date, LocalDate to_date){
		return summaryPerformanceReport.finishing_performance(from_date, to_date);
	}

	public HashMap<String, String[]> generatePacking_performance(LocalDate from_date, LocalDate to_date){
		return summaryPerformanceReport.packing_performance(from_date, to_date);
	}

	public Controller() {
	}

}