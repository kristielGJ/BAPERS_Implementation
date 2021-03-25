package model.database;

import model.admin.alert.Alert;

import javax.swing.text.html.CSS;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Controller implements I_Bapers {

	private static final DB_Connection mainConn = new DB_Connection();

	public void main() {

	}



	/**
	 *
	 * @param Account_no
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

	/**
	 * 
	 * @param name
	 * @param Acc_no
	 * @param Address
	 * @param Phone
	 */
	public void updateCustomerDetails(String name, String Acc_no, String Address, String Phone) throws SQLException {
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

	public Controller() {
	}

}