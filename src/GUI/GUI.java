package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

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

	public void updateCustomer(String[] customerData){
		add(new UpdateCustomerDetails(getWidth(),getHeight(),customerData,this));
	}

	public void addJob(int customer_acc_no) {
		add(new CreateJob(getWidth(), getHeight(), customer_acc_no, this));
	}

	public void addTask(int Job_ID) {
		add(new AddTask(getWidth(), getHeight(), Job_ID, this));
	}

	public void viewTasks(int Job_ID) {
		add(new Task(getWidth(), getHeight(), Job_ID, this));
	}

	public void viewExistingTasks() {
		add(new ExistingTaskList(getWidth(), getHeight(),this));
	}

	public void extendTaskList() {
		add(new ExtendTaskList(getWidth(), getHeight(), this));
	}

	public void updateExistingTask(String[] data) {
		add(new UpdateExistingTasks(getWidth(), getHeight(), data, this));
	}

	public void retrieveJobs(int customer_id){
        add(new RetrieveJobs(getWidth(), getHeight(), customer_id, this));
    }

    public void makePayment(int job_ID, double amount){
        add(new GetPayment(getWidth(), getHeight(), job_ID, amount, this));
    }

    public void cardPayment(double amount, Date date, int job_ID){
		add(new CardPayment(getWidth(), getHeight(), amount, date, job_ID,this));
	}

    public void generateInvoice(int payment_ID){
		add(new Invoice(getWidth(), getHeight(), payment_ID, this));
	}

	public void generateReport(){
		add(new GenerateReport(getWidth(), getHeight(), this));
	}

	public void generateJobSheet(LocalDate from_date, LocalDate to_date, int customer_ID){
		add(new JobSheet(getWidth(), getHeight(), from_date, to_date, customer_ID, this));
	}

	public void generateIndividualPerformanceReport(LocalDate from_date, LocalDate to_date){
		add(new IndividualPerformanceReport(getWidth(), getHeight(), from_date, to_date,this));
	}

	public void generateSummaryPerformanceReport(LocalDate from_date, LocalDate to_date){
		add(new SummaryPerformanceReport(getWidth(), getHeight(), from_date, to_date, this));
	}

	public void updateCustomerDetails(String customerData) {
		// TODO - implement GUI.updateCustomerDetails
		throw new UnsupportedOperationException();
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