package model.jobs.payment.transaction;

import model.Model;
import model.database.DB_Connection;
import model.jobs.payment.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Payment_Transaction implements I_Payment_Transaction {

    private DB_Connection db = new DB_Connection();
    private Connection conn = db.connect();
    private PreparedStatement Stm = null;

    /**
     * creating a new Payment object
     *
     * @param payment_ID
     * @param payment_amount
     * @param payment_type
     * @param customer_ID
     */
    public void makePayment(int payment_ID, double payment_amount, String payment_type, int customer_ID){
        new Payment(payment_ID, payment_amount, payment_type);
        storePaymentDetails(payment_ID, payment_type, payment_amount, customer_ID);
    }

    /**
     * store payment details in the database
     *
     * @param payment_ID
     * @param payment_type
     * @param payment_amount
     * @param customer_ID
     */
    public void storePaymentDetails(int payment_ID, String payment_type, Double payment_amount, int customer_ID) {
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`Payment` (`Payment_ID`,`Payment_type`, `Payment_amount`, `CustomerAccount_no`) VALUES (?,?,?,?) ");
            Stm.setInt(1, payment_ID);
            Stm.setString(2, payment_type);
            Stm.setDouble(3, payment_amount);
            Stm.setDouble(4, customer_ID);
            Stm.executeUpdate();

            // updating the payment status of job to paid
            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Payment_status = ? WHERE PaymentPayment_ID =?;");
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
    public String[] generateInvoice(int payment_ID) {
        String[] invoice_details = new String[12];
        try {
            Stm = conn.prepareStatement("select Job_ID, Completion_date_time, Subtotal_price, Total_discount, Total_price, CustomerAccount_no from Job where PaymentPayment_ID = ? ");
            Stm.setInt(1, payment_ID);
            ResultSet rs = Stm.executeQuery();
            while(rs.next()) {
                invoice_details[0] = String.valueOf(rs.getInt("Job_ID"));
                invoice_details[1] = String.valueOf(rs.getTimestamp("Completion_date_time"));
                invoice_details[2] = String.valueOf(rs.getDouble("Subtotal_price"));
                invoice_details[3] = String.valueOf(rs.getDouble("Total_discount"));
                invoice_details[4] = String.valueOf(rs.getDouble("Total_price"));
            }

            Stm = conn.prepareStatement("select Account_no, Customer_name, Customer_address, Customer_phone from Customer where Account_no = ? ");
            Stm.setInt(1, rs.getInt("CustomerAccount_no"));
            ResultSet rs2 = Stm.executeQuery();
            while(rs2.next()){
                invoice_details[5] = String.valueOf(rs2.getInt("Account_no"));
                invoice_details[6] = rs2.getString("Customer_name");
                invoice_details[7] = rs2.getString("Customer_address");
                invoice_details[8] = rs2.getString("Customer_phone");
            }

            Stm = conn.prepareStatement("select Task_ID, Discount_rate, Task_CatalogCatalog_ID from Task where JobJob_ID = ? ");
            Stm.setInt(1, rs.getInt("Job_ID"));
            ResultSet rs3 = Stm.executeQuery();
            while(rs3.next()){
                invoice_details[9] = String.valueOf(rs3.getInt("Task_ID"));
                invoice_details[10] = String.valueOf(rs3.getDouble("Discount_rate"));
            }

            Stm = conn.prepareStatement("select Price from Task_Catalog where Catalog_ID = ? ");
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

    @Override
    public Payment read(int id) {
        Payment payment = null;
        try {
            Stm = conn.prepareStatement("SELECT * FROM Payment WHERE Payment_ID = ?");
            Stm.setInt(1, id);
            ResultSet rs = Stm.executeQuery();
            payment = new Payment(
                    rs.getInt(1),
                    rs.getDouble(3),
                    rs.getString(2)
            );
            rs.close();
            Stm.close();
            System.out.println("Queried " + payment.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public ArrayList<Model> getAll() {
        ArrayList<Model> payment_details = null;
        try {
            payment_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Payment");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt(1),
                        rs.getDouble(3),
                        rs.getString(2)
                );
                payment_details.add(payment);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment_details;
    }

    @Override
    public boolean remove(int id) {
        boolean removed = false;
        try {
            Stm = conn.prepareStatement("DELETE FROM Payment WHERE Payment_ID =?");
            Stm.setInt(1, id);
            int update = Stm.executeUpdate();
            Stm.close();
            System.out.println("Removed " + id);
            removed = update == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return removed;
    }
}

