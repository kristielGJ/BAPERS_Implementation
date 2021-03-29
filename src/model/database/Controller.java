package model.database;

import model.Model;
import model.admin.alert.Alert;
import model.admin.userAccount.UserAccount;
import model.admin.userAccount.transaction.UserAccountTransaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.CSS;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {

	private static final DB_Connection mainConn = new DB_Connection();
	UserAccountTransaction userAccountTransaction = new UserAccountTransaction(mainConn);
	private UserAccount currentUser;

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

	public void updateCustomerDetails(String name, String Acc_no, String Address, String Phone) throws SQLException {

	}

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

	public boolean addStaffMember(String[] values) {
		//TODO: Add verification
		try {
			userAccountTransaction.create(values[0], values[1], values[2], values[3], values[4]);
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	public boolean removeStaffMember(int id) {
		try {
			userAccountTransaction.remove(id);
			return true;
		} catch (Exception e) { e.printStackTrace(); }
		return false;
	}

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

	public UserAccount getCurrentUser() { return currentUser; }

	public boolean authenticateUser(int id, String password) {
		if (userAccountTransaction.authenticate(id, password) != null) {
			currentUser = userAccountTransaction.authenticate(id, password);
			return true;
		}else{
			return false;
		}
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

	public Controller() {
	}

}