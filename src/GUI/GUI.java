package GUI;
import javax.swing.*;

public class GUI extends JFrame {

	private JPanel lastPanel= null, currentPanel = null;

	public void login() {
		// TODO - implement GUI.login
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement GUI.logout
		throw new UnsupportedOperationException();
	}

	public String findCustomer(String data) {
		// TODO - implement GUI.findCustomer
		throw new UnsupportedOperationException();
	}

	public void createNewCustomer() {
		add(new CreateCustomerAccount(getWidth(),getHeight(), this));
	}

	public void regularCustomer(String[] customerData){
		add(new RegularCustomer(getWidth(),getHeight(),customerData,this));
	}

	public void valuedCustomer(String[] customerData){
		add(new ValuedCustomer(getWidth(),getHeight(),customerData,this));
	}

	public void updateCustomer(String[] customerData){
		add(new UpdateCustomerDetails(getWidth(),getHeight(),customerData,this));
	}

	public void retrieveCustomer(){
		add(new RetrieveCustomer(getWidth(),getHeight(),this));
	}

	public String retrieveJob(int jobID) {
		// TODO - implement GUI.retrieveJob
		throw new UnsupportedOperationException();
	}

	public void addJob(int customer_acc_no) {
		add(new CreateJob(getWidth(), getHeight(), customer_acc_no, this));
	}

	public boolean saveJob(int jobID, String jobData) {
		// TODO - implement GUI.saveJob
		throw new UnsupportedOperationException();
	}

	public String updateSatus(int jobID) {
		// TODO - implement GUI.updateSatus
		throw new UnsupportedOperationException();
	}

	public boolean makePayment(String paymentData) {
		// TODO - implement GUI.makePayment
		throw new UnsupportedOperationException();
	}

	public void chooseReportType(String type) {
		// TODO - implement GUI.chooseReportType
		throw new UnsupportedOperationException();
	}

	public String generateReport(String reportData) {
		// TODO - implement GUI.generateReport
		throw new UnsupportedOperationException();
	}

	public void addTask(int Job_ID) {
		add(new AddTask(getWidth(), getHeight(), Job_ID, this));
	}

	public void extendTaskList(String taskData) {
		// TODO - implement GUI.extendTaskList
		throw new UnsupportedOperationException();
	}

	public void removeExistingTask(int existing_task_ID) {
		// TODO - implement GUI.removeExistingTask
		throw new UnsupportedOperationException();
	}

	public void updateExistingTask(int existing_task_ID) {
		// TODO - implement GUI.updateExistingTask
		throw new UnsupportedOperationException();
	}

	public void updateTaskInfo(String task_status, String location) {
		// TODO - implement GUI.updateTaskInfo
		throw new UnsupportedOperationException();
	}

	public void updateCustomerDetails(String customerData) {
		// TODO - implement GUI.updateCustomerDetails
		throw new UnsupportedOperationException();
	}

	public void setLastPanel(JPanel panel){
		this.lastPanel = panel;
	}

	public void setCurrentPanel(JPanel panel){
		this.currentPanel = panel;
	}

	public JPanel getCurrentPanel(){
		return currentPanel;
	}

	public JPanel getLastPanel(){
		return lastPanel;
	}

	public GUI(int width, int height) {
		this.setSize(width,height);
		this.setTitle("BAPERS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

}