package model.jobs.payment.transaction;

import model.Model;
import model.database.DB_Connection;
import model.jobs.payment.Payment;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public class Payment_Transaction implements I_Payment_Transaction {

    private PreparedStatement Stm = null;
    private Connection conn;

    /**
     * constructor
     *
     * @param conn
     */
    public Payment_Transaction(DB_Connection conn){
        this.conn = conn.getConn();
    }

    /**
     * creates a new Payment
     *
     * @param payment_amount
     * @param payment_type
     * @param date
     * @param job_ID
     * @return
     */
    public int makePayment(double payment_amount, String payment_type, Date date, int job_ID){
        new Payment(payment_amount, payment_type, date);
        int id = storePaymentDetails(payment_type, payment_amount, date, job_ID);
        return id;
    }

    /**
     * store payment details in the database and returns the generated id
     *
     * @param payment_type
     * @param payment_amount
     * @param date
     * @param job_ID
     * @return
     */
    public int storePaymentDetails(String payment_type, Double payment_amount, Date date, int job_ID) {
        int id = -1;
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`Payment` (`Payment_type`, `Payment_amount`, `Payment_date`,`JobJob_ID`) VALUES (?,?,?,?) ");
            Stm.setString(1, payment_type);
            Stm.setDouble(2, payment_amount);
            Stm.setDate(3,date);
            Stm.setDouble(4, job_ID);
            Stm.executeUpdate();
            Stm.close();

            // updating the payment status of job to paid
            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Payment_status = ? WHERE Job_ID =?;");
            Stm.setString(1, "Paid");
            Stm.setInt(2,job_ID);
            Stm.executeUpdate();
            Stm.close();

            //generated id
            Stm = conn.prepareStatement("SELECT * FROM Payment WHERE JobJob_ID = ?;");
            Stm.setInt(1,job_ID);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * generates a invoice
     *
     * @param payment_ID
     * @return invoice_details
     */
    public String[] generateInvoice(int payment_ID) {
        String[] invoice_details = new String[9];
        try {
            Stm = conn.prepareStatement("select * from Payment where Payment_ID = ? ");
            Stm.setInt(1, payment_ID);
            ResultSet rs = Stm.executeQuery();
            while(rs.next()) {
                Stm = conn.prepareStatement("select * from Job where Job_ID = ? ");
                Stm.setInt(1, rs.getInt(5));
                ResultSet rs1 = Stm.executeQuery();
                while (rs1.next()){
                    invoice_details[0] = String.valueOf(rs1.getInt(1));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String date = rs1.getTimestamp(12).toLocalDateTime().format(formatter);
                    invoice_details[1] = date;
                    invoice_details[2] = String.valueOf(rs1.getDouble(8));
                    invoice_details[3] = String.valueOf(rs1.getDouble(9));
                    invoice_details[4] = String.valueOf(rs1.getDouble(10));
                    Stm = conn.prepareStatement("select * from Customer where Account_no = ? ");
                    Stm.setInt(1, rs1.getInt(14));
                    ResultSet rs2 = Stm.executeQuery();
                    while (rs2.next()){
                        invoice_details[5] = rs2.getString(3);
                        invoice_details[6] = String.valueOf(rs2.getInt(1));
                        invoice_details[7] = rs2.getString(4);
                        invoice_details[8] = rs2.getString(5);
                    }
                    rs2.close();
                }
                rs1.close();
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoice_details;
    }

    /**
     *
     * @param id
     * @return payment
     */
    @Override
    public Payment read(int id) {
        Payment payment = null;
        try {
            Stm = conn.prepareStatement("SELECT * FROM Payment WHERE Payment_ID = ?");
            Stm.setInt(1, id);
            ResultSet rs = Stm.executeQuery();
            payment = new Payment(
                    rs.getDouble(3),
                    rs.getString(2),
                    rs.getDate(4)
            );
            rs.close();
            Stm.close();
            System.out.println("Queried " + payment.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    /**
     *
     * @return payment_details
     */
    @Override
    public ArrayList<Payment> getAll() {
        ArrayList<Payment> payment_details = null;
        try {
            payment_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Payment");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getDouble(3),
                        rs.getString(2),
                        rs.getDate(4)
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

    /**
     *
     * @param id
     * @return removed
     */
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

