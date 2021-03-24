package model.jobs;

import model.database.DB_Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Payment {

    private int payment_ID;
	private double payment_amount;
	private String payment_type;
	private static DB_Connection conn = new DB_Connection();
	private static PreparedStatement Stm = null;

	/**
	 * constructor
     *
     * @param new_payment_ID
	 * @param new_payment_amount
	 * @param new_payment_type
	 */
	public Payment(int new_payment_ID, double new_payment_amount, String new_payment_type) {
		payment_ID = new_payment_ID;
	    payment_amount = new_payment_amount;
		payment_type = new_payment_type;
	}

    /**
     *
     * @param payment_ID
     */
    public void setPayment_ID(int payment_ID) { this.payment_ID = payment_ID; }

    public int getPayment_ID(){ return this.payment_ID; }

	/**
	 *
	 * @param payment_amount
	 */
	public void setPayment_amount(double payment_amount) {
		this.payment_amount = payment_amount;
	}

	public double getPayment_amount() {
		return this.payment_amount;
	}

	/**
	 *
	 * @param payment_type
	 */
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getPayment_type() {
		return this.payment_type;
	}

	/**
	 * creating a new Payment object
	 *
     * @param payment_ID
	 * @param payment_amount
	 * @param payment_type
	 * @param customer_ID
	 */
	public static void makePayment(int payment_ID, double payment_amount, String payment_type, int customer_ID){
		new Payment(payment_ID, payment_amount, payment_type);
		storePaymentDetails(payment_ID, payment_type, payment_amount, customer_ID);
	}

	/**
	 * store payment details in the model.database
	 *
     * @param payment_ID
	 * @param payment_type
	 * @param payment_amount
	 * @param customer_ID
	 */
	public static void storePaymentDetails(int payment_ID, String payment_type, Double payment_amount, int customer_ID) {
		try {
			Stm = conn.connect().prepareStatement("INSERT INTO `bapers`.`Payment` (`Payment_ID`,`Payment_type`, `Payment_amount`, `CustomerAccount_no`) VALUES (?,?,?,?) ");
			Stm.setInt(1, payment_ID);
			Stm.setString(2, payment_type);
			Stm.setDouble(3, payment_amount);
			Stm.setDouble(4, customer_ID);
			Stm.executeUpdate();

			// updating the payment status of job to paid
            Stm = conn.connect().prepareStatement("UPDATE `bapers`.`Job` SET Payment_status = ? WHERE PaymentPayment_ID =?;");
            Stm.setString(1, "Paid");
            Stm.setInt(2,payment_ID);
            Stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param payment_ID
	 */
	public static String[] generateInvoice(int payment_ID) {
		String[] invoice_details = new String[12];
		try {
			Stm = conn.connect().prepareStatement("select Job_ID, Completion_date_time, Subtotal_price, Total_discount, Total_price, CustomerAccount_no from Job where PaymentPayment_ID = ? ");
			Stm.setInt(1, payment_ID);
			ResultSet rs = Stm.executeQuery();
			while(rs.next()) {
				invoice_details[0] = String.valueOf(rs.getInt("Job_ID"));
				invoice_details[1] = String.valueOf(rs.getTimestamp("Completion_date_time"));
				invoice_details[2] = String.valueOf(rs.getDouble("Subtotal_price"));
				invoice_details[3] = String.valueOf(rs.getDouble("Total_discount"));
				invoice_details[4] = String.valueOf(rs.getDouble("Total_price"));
			}

			Stm = conn.connect().prepareStatement("select Account_no, Customer_name, Customer_address, Customer_phone from Customer where Account_no = ? ");
			Stm.setInt(1, rs.getInt("CustomerAccount_no"));
			ResultSet rs2 = Stm.executeQuery();
			while(rs2.next()){
				invoice_details[5] = String.valueOf(rs2.getInt("Account_no"));
				invoice_details[6] = rs2.getString("Customer_name");
				invoice_details[7] = rs2.getString("Customer_address");
				invoice_details[8] = rs2.getString("Customer_phone");
			}

			Stm = conn.connect().prepareStatement("select Task_ID, Discount_rate, Task_CatalogCatalog_ID from Task where JobJob_ID = ? ");
			Stm.setInt(1, rs.getInt("Job_ID"));
			ResultSet rs3 = Stm.executeQuery();
			while(rs3.next()){
				invoice_details[9] = String.valueOf(rs3.getInt("Task_ID"));
				invoice_details[10] = String.valueOf(rs3.getDouble("Discount_rate"));
			}

			Stm = conn.connect().prepareStatement("select Price from Task_Catalog where Catalog_ID = ? ");
			Stm.setInt(1, rs3.getInt("Task_CatalogCatalog_ID"));
			ResultSet rs4 = Stm.executeQuery();
			while(rs3.next()){
				invoice_details[11] = String.valueOf(rs4.getInt("Price"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoice_details;
	}
}

