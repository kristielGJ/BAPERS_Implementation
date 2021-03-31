package model.database;

import model.admin.alert.Alert;
import model.admin.alert.ScheduledAlert;
import model.admin.alert.transaction.AlertTransaction;
import model.discounts.variable_discount.VariableDiscount;
import model.admin.userAccount.UserAccount;
import model.admin.userAccount.transaction.UserAccountTransaction;
import model.customers.Customer;
import model.customers.transaction.CustomersTransaction;
import model.customers.transaction.I_CustomersTransaction;
import model.discounts.discount.transaction.DiscountTransaction;
import model.discounts.discount.transaction.I_DiscountTransaction;
import model.discounts.fixed_discount.transaction.FixedDiscountTransaction;
import model.discounts.fixed_discount.transaction.I_FixedDiscountTransaction;
import model.discounts.flexible_discount.FlexibleDiscount;
import model.discounts.flexible_discount.transaction.FlexibleDiscountTransaction;
import model.discounts.flexible_discount.transaction.I_FlexibleDiscountTransaction;
import model.discounts.variable_discount.transaction.I_VariableDiscountTransaction;
import model.discounts.variable_discount.transaction.VariableDiscountTransaction;
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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Controller implements I_Bapers {

	private static final DB_Connection mainConn = new DB_Connection();
	private I_FixedDiscountTransaction fixedDiscount = new FixedDiscountTransaction(mainConn);
	private I_FlexibleDiscountTransaction flexibleDiscount = new FlexibleDiscountTransaction(mainConn);
	private I_VariableDiscountTransaction variableDiscount = new VariableDiscountTransaction(mainConn);
	private I_DiscountTransaction discount = new DiscountTransaction(mainConn, fixedDiscount, flexibleDiscount, variableDiscount);
	private I_Job_Transaction job = new Job_Transaction(mainConn);
	private I_Task_Transaction task = new Task_Transaction(mainConn, discount);
	private I_ExistingTasks_Transaction existingTask = new ExistingTasks_Transaction(mainConn);
	private I_Payment_Transaction payment = new Payment_Transaction(mainConn);
	private I_Card_payment_Transaction card_payment = new Card_payment_Transaction(mainConn);
	private I_IndividualPerformanceReport_Transaction individualPerformanceReport = new IndividualPerformanceReport_Transaction(mainConn);
	private I_JobPerformanceReport_Transaction jobPerformanceReport = new JobPerformanceReport_Transaction(mainConn);
	private I_SummaryPerformanceReport_Transaction summaryPerformanceReport = new SummaryPerformanceReport_Transaction(mainConn);
	private I_CustomersTransaction customer = new CustomersTransaction(mainConn);
	UserAccountTransaction userAccountTransaction = new UserAccountTransaction(mainConn);
	AlertTransaction alertTransaction = new AlertTransaction(mainConn);
	private UserAccount currentUser;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private ArrayList<ScheduledAlert> loadedAlerts = new ArrayList<>();

	public void main() {

	}

	/**
	 *
	 */
	public Customer identifyCustomer(int Account_no){
		return (Customer) customer.read(Account_no);
	}

	@Override
	public void createNewCustomer(String name, String address, String phone) {
		customer.addCustomer(name,address,phone);
	}

	@Override
	public void updateCustomerDetails(String name, int Acc_no, String Address, String Phone, String valued){
		customer.updateCustomer(name,Acc_no,Address,Phone, valued);
	}

	@Override
	public ArrayList<String[]> getAllCustomers() {
		return customer.getAllCust();
	}

	@Override
	public String[][] getCustomerData(ArrayList<String[]> customers) {
		return customer.getData(customers);
	}

	@Override
	public boolean removeCustomer(int Acc_no) {
		return customer.remove(Acc_no);
	}

	@Override
	public int getLastAccNo() {
		return customer.getLastAccNo();
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

	@Override
	public ArrayList<ScheduledAlert> getLoadedAlerts() {
		return loadedAlerts;
	}

	@Override
	public void removeAlert(Alert alert) {
		alertTransaction.remove(alert.getId());
	}

	@Override
	public void refreshAllAlerts(JFrame parent) {
		for (ScheduledAlert scheduledAlert : loadedAlerts) {
			scheduledAlert.cancelSchedule();
		}
		loadedAlerts.clear();
		loadAllAlerts(parent);
	}

	@Override
	public void loadAllAlerts(JFrame parent) {
		ArrayList<Alert> alerts = alertTransaction.getAll();
		if (!alerts.isEmpty()) {
			for (Alert alert : alerts) {
				ScheduledAlert scheduledAlert = new ScheduledAlert(alert, parent, this);
				if (scheduledAlert.canRunAlert()) {
					scheduledAlert.runAlert();
				}else{
					System.out.println("Failed adding alert " + alert.toString());
				}
			}
			for (ScheduledAlert scheduledAlert1 : getLoadedAlerts()) {
				System.out.println("Alert loaded: " + scheduledAlert1.getAlert().getTimeUntilExecutionInSeconds() + "s until execution.");
			}
		}
	}

	@Override
	public ScheduledExecutorService getScheduler() {
		return scheduler;
	}

	@Override
	public boolean updateStaffMember(Object[] values) {
		try {
			userAccountTransaction.update(
					(int) values[0], (String) values[1],
					(String) values[2], (String) values[3],
					(String) values[4], (String) values[5]
			);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addStaffMember(String[] values) {
		//TODO: Add verification
		try {
			userAccountTransaction.create(values[0], values[1], values[2], values[3], values[4]);
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public boolean removeStaffMember(int id) {
		try {
			userAccountTransaction.remove(id);
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public void populateStaffTable(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		ArrayList<UserAccount> userAccounts = userAccountTransaction.getAll();
		for (UserAccount act : userAccounts) {
			model.addRow(new Object[] {
					act.getId(), act.getName(),
					act.getEmail(), act.getPhone(),
					act.getRole()
			});
		}
	}
	public void addFixedDiscountRate(int customer_acc_no, int discount_rate){
		fixedDiscount.addFixedDiscountRate(customer_acc_no,discount_rate);
	}

	public void removeFlexibleDiscount(int id){
		flexibleDiscount.removeFlexibleDiscount(id);
	}

	public ArrayList<FlexibleDiscount> getAllFlexibleDiscounts(int acc_no) {
		return flexibleDiscount.getFlexibleDiscount(acc_no);
	}

	public void updateFlexibleDiscount(int acc_no,int lowerBound,int UpperBound,double DiscountRate){
		flexibleDiscount.updateFlexibleDiscount(acc_no,lowerBound,UpperBound,DiscountRate);

	}
	public void createFlexibleDiscount(int acc_no,int lowerBound,int UpperBound,double DiscountRate){
		flexibleDiscount.addFlexibleDiscount(acc_no,lowerBound,UpperBound,DiscountRate);
	}

	public ArrayList<FlexibleDiscount> getFlexibleDiscount(int customer_acc_no) {
		return flexibleDiscount.getFlexibleDiscount(customer_acc_no);
	}

	public boolean removeVariableDiscount(int id, int acc_no){
		return variableDiscount.removeVariableDiscount(id,acc_no);
	}
	public ArrayList<VariableDiscount> getAllVariableDiscounts(int acc_no) {
		return variableDiscount.getVariableDiscount(acc_no);
	}
	public void updateVariableDiscount(int acc_no, int discount_rate, int catalog_id){
		variableDiscount.updateVariableDiscount( acc_no, discount_rate, catalog_id);
	}

	public void createVariableDiscount(int acc_no, int discount_rate, int catalog_id){
		variableDiscount.addVariableDiscount(acc_no,  discount_rate, catalog_id);
	}

	@Override
	public void populateVariableTable(JTable table,int acc_no) {
		double price,newPrice,discount;
		int i=0;
		ArrayList<Integer> catalogId= new ArrayList<Integer>();
		catalogId=variableDiscount.getCatalog_IDs(acc_no);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		ArrayList<VariableDiscount> variableDiscounts =getAllVariableDiscounts(acc_no);

		for (VariableDiscount a : variableDiscounts) {
			//make sure all tasks in catalog are displayed on the table so the user can edit the rates.
			int id = catalogId.get(i);
			price= variableDiscount.GetTaskPrice(id);
			newPrice=variableDiscount.calculateNewPrice(a.getDiscount_rate(),price);
			model.addRow(new Object[] {
					id,variableDiscount.GetTaskName(i),price,a.getDiscount_rate(),newPrice
			});
			i++;
		}

		}


	@Override
	public UserAccount getCurrentUser() { return currentUser; }

	@Override
	public boolean authenticateUser(int id, String password) {
		if (userAccountTransaction.authenticate(id, password) != null) {
			currentUser = userAccountTransaction.authenticate(id, password);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void logout() {
		currentUser = null;
	}

	public Controller() {
	}

}