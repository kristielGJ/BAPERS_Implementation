package GUI;

import model.database.Controller;
import model.database.I_Bapers;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class GUI extends JFrame {

	private JPanel lastPanel= null, currentPanel = null;
	private I_Bapers bapers = new Controller();

	public void login() {
		add(new LoginPanel(getWidth(), getHeight(), this, bapers));
	}

	public void staffManagement() {
		add(new StaffManagementPanel(getWidth(), getHeight(), this, bapers));
	}

	public void logout() {
		// TODO - implement GUI.logout
		throw new UnsupportedOperationException();
	}

	public String findCustomer(String data) {
		// TODO - implement GUI.findCustomer
		throw new UnsupportedOperationException();
	}

	public void home(){
		if (bapers.getCurrentUser().getRole().equals("Shift Manager") ||
			bapers.getCurrentUser().getRole().equals("Office Manager")) {
			bapers.loadAllAlerts(this);
		}
		add(new Home(getWidth(),getHeight(), this, bapers));
	}

	public void createNewCustomer() {
		add(new CreateCustomerAccount(getWidth(),getHeight(), this, bapers));
	}

	public void regularCustomer(String[] customerData){
		add(new RegularCustomer(getWidth(),getHeight(),customerData,this));
	}

	public void valuedCustomer(String[] customerData){
		add(new ValuedCustomer(getWidth(),getHeight(),customerData,this));
	}

	public void updateCustomer(String[] customerData){
		add(new UpdateCustomerDetails(getWidth(),getHeight(),customerData,this, bapers));
	}

	public void retrieveCustomer(){
		add(new RetrieveCustomer(getWidth(),getHeight(),this, bapers));
	}

	public void viewJobs(){
		add(new ViewJobs(getWidth(),getHeight(),  bapers,this));
	}

	public void addJob(int customer_acc_no) {
		add(new CreateJob(getWidth(), getHeight(), customer_acc_no, bapers, this));
	}

	public void addTask(int Job_ID) {
		add(new AddTask(getWidth(), getHeight(), Job_ID, bapers, this));
	}

	public void viewTasks(int Job_ID) {
		add(new Task(getWidth(), getHeight(), Job_ID, bapers, this));
	}

	public void viewExistingTasks() {
		add(new ExistingTaskList(getWidth(), getHeight(), bapers, this));
	}

	public void extendTaskList() {
		add(new ExtendTaskList(getWidth(), getHeight(), bapers, this));
	}

	public void updateExistingTask(String[] data) {
		add(new UpdateExistingTasks(getWidth(), getHeight(), data, bapers, this));
	}

	public void retrieveJobs(int customer_id){
		add(new RetrieveJobs(getWidth(), getHeight(), customer_id, bapers, this));
	}

	public void makePayment(int job_ID, double amount){
		add(new GetPayment(getWidth(), getHeight(), job_ID, amount, bapers, this));
	}

	public void cardPayment(double amount, Date date, int job_ID){
		add(new CardPayment(getWidth(), getHeight(), amount, date, job_ID, bapers, this));
	}

	public void generateInvoice(int payment_ID){
		add(new Invoice(getWidth(), getHeight(), payment_ID, bapers, this));
	}

	public void generateReport(){
		add(new GenerateReport(getWidth(), getHeight(), bapers, this));
	}

	public void generateJobSheet(LocalDate from_date, LocalDate to_date, int customer_ID){
		add(new JobSheet(getWidth(), getHeight(), from_date, to_date, customer_ID, bapers, this));
	}

	public void generateIndividualPerformanceReport(LocalDate from_date, LocalDate to_date){
		add(new IndividualPerformanceReport(getWidth(), getHeight(), from_date, to_date, bapers,this));
	}

	public void generateSummaryPerformanceReport(LocalDate from_date, LocalDate to_date){
		add(new SummaryPerformanceReport(getWidth(), getHeight(), from_date, to_date, bapers, this));
	}

	public void addTaskDiscount(int acc_no,int catalog_id){
		add(new AddTaskDiscount(getWidth(), getHeight(), bapers, this,acc_no,catalog_id));
	}
	public Component createDiscountPlan(int acc_no){
		return add(new CreateDiscountPlan(bapers, this,acc_no));
	}

	public void fixedDiscount(int acc_no){
		add(new FixedDiscount(getWidth(), getHeight(), bapers, this,acc_no));
	}

	public void flexibleDiscount(int acc_no){
		add(new FlexibleDiscount(getWidth(), getHeight(), bapers, this, acc_no));
	}

	public void variableDiscount(int acc_no){
		add(new VariableDiscount(getWidth(), getHeight(), bapers, this,acc_no));
	}

	//converts long to string (time hh:mm)
	public String convertToString(long dur){
		return String.format("%02d:%02d", TimeUnit.SECONDS.toHours(dur), TimeUnit.SECONDS.toMinutes(dur) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(dur)));
	}

	//prints the JPanel
	public void printPanel(JPanel panel, String name) {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setJobName(name);
		printerJob.setPrintable(new Printable() {
			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				if(pageIndex > 0){
					return Printable.NO_SUCH_PAGE;
				}
				Graphics2D graphics2D = (Graphics2D)graphics;
				graphics2D.translate(pageFormat.getImageableX()*2, pageFormat.getImageableY()*2);
				graphics2D.scale(0.8,0.8);
				panel.paint(graphics2D);
				return Printable.PAGE_EXISTS;
			}
		});
		boolean returningResult = printerJob.printDialog();
		if(returningResult){
			try {
				printerJob.print();
			}catch (PrinterException printerException){
				JOptionPane.showMessageDialog(this, "Print Error: " + printerException.getMessage());
			}
		}
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
	}


}