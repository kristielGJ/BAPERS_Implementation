package model.discounts.fixed_discount.transaction;

import model.database.DB_Connection;
import model.discounts.fixed_discount.FixedDiscount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Manpreet and Gera
 *
 */

public class FixedDiscountTransaction implements I_FixedDiscountTransaction {

    private PreparedStatement Stm = null;
    private Connection conn;
    /**
     * Constructor
     *
     * @param conn
     *
     */
    public FixedDiscountTransaction(DB_Connection conn){
        this.conn = conn.getConn();
    }
    /**
     * Set's the fixed discount rate
     * Updates the database
     *
     * @param customer_acc_no
     * @param discount_rate
     *
     */
    public void addFixedDiscountRate(int customer_acc_no, double discount_rate){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Customer` SET Fixed_discount = ? WHERE Account_no =?;");
            Stm.setDouble(1, discount_rate);
            Stm.setInt(2,customer_acc_no);
            Stm.executeUpdate();
            Stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns the total discount for a job
     *
     * @param job_ID
     * @param customer_acc_no
     *
     */
    public double getTotalDiscount(int job_ID, int customer_acc_no){
        double total_discount = 0.0;
        double discount_rate = 0.0;
        try{
            Stm = conn.prepareStatement("SELECT * FROM Job WHERE Job_ID = ?;");
            Stm.setInt(1,job_ID);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                discount_rate = getDiscountRate(customer_acc_no);
                total_discount += rs.getDouble(8)*(discount_rate/100);
            }
            rs.close();
            Stm.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return total_discount;
    }
    /**
     * Returns the discount rate (as a percentage)
     *
     * @param customer_acc_no
     *
     */
    public double getDiscountRate(int customer_acc_no){
        double discount_rate = 0.0;
        try{
            Stm = conn.prepareStatement("SELECT * FROM Customer WHERE Account_no = ?;");
            Stm.setInt(1,customer_acc_no);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                discount_rate += rs.getDouble(7);
            }
            rs.close();
            Stm.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return discount_rate;
    }
    /**
     * Returns a list of a fixed discount's details
     * which are associated with the customer
     *
     * @param customer_acc_no
     *
     */
    public ArrayList<FixedDiscount> getFixedDiscount(int customer_acc_no) {
        ArrayList<FixedDiscount> discount_details = null;
        try {
            discount_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Customer WHERE Account_no = ?");
            Stm.setInt(1, customer_acc_no);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                FixedDiscount discount = new FixedDiscount(
                        customer_acc_no,
                        rs.getDouble(7)
                );
                discount_details.add(discount);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discount_details;
    }

}
