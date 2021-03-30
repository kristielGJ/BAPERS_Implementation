package model.discounts.discount.transaction;

import model.database.DB_Connection;
import model.discounts.fixed_discount.transaction.I_FixedDiscountTransaction;
import model.discounts.flexible_discount.transaction.I_FlexibleDiscountTransaction;
import model.discounts.variable_discount.transaction.I_VariableDiscountTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 *
 * @author Manpreet and Gera
 */

public class DiscountTransaction implements I_DiscountTransaction {
    private PreparedStatement Stm = null;
    private Connection conn;
    private I_FixedDiscountTransaction fixedDiscount;
    private I_FlexibleDiscountTransaction flexibleDiscount;
    private I_VariableDiscountTransaction variableDiscount;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    //constructor
    public DiscountTransaction(DB_Connection conn, I_FixedDiscountTransaction fixedDiscount, I_FlexibleDiscountTransaction flexibleDiscount, I_VariableDiscountTransaction variableDiscount){
        this.conn = conn.getConn();
        this.fixedDiscount = fixedDiscount;
        this.flexibleDiscount = flexibleDiscount;
        this.variableDiscount = variableDiscount;
    }

    //assigning a discount plan to a valued customer
    public void assignDiscountType(int acc_no, String discount_type){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Customer` SET Discount_type = ? WHERE Account_no =? AND Customer_type = ?;");
            Stm.setString(1, discount_type);
            Stm.setInt(2,acc_no);
            Stm.setString(3, "Valued");
            Stm.executeUpdate();
            Stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //calculating the total price of a job
    public void calculatePrice(int job_ID){
        double total_price = 0.0;
        double total_discount = 0.0;
        try {
            Stm = conn.prepareStatement("SELECT * FROM Job WHERE Job_ID = ?;");
            Stm.setInt(1, job_ID);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                Stm = conn.prepareStatement("SELECT * FROM Customer WHERE Account_no = ?;");
                Stm.setInt(1, rs.getInt(14));
                ResultSet rs1 = Stm.executeQuery();
                while (rs1.next()){
                    if (rs1.getString(2).equals("Valued")){
                        if(rs1.getString(6).equals("Variable")){
                            total_discount = Double.parseDouble(decimalFormat.format(variableDiscount.getTotal_Discount(job_ID, rs1.getInt(1))));
                        }
                        else if (rs1.getString(6).equals("Flexible")){
                            total_discount = Double.parseDouble(decimalFormat.format(flexibleDiscount.getTotalDiscount(job_ID, rs1.getInt(1))));
                        }
                        else{
                            total_discount = Double.parseDouble(decimalFormat.format(fixedDiscount.getTotalDiscount(job_ID, rs1.getInt(1))));
                        }
                    }
                    else {
                        total_discount = 0.0;
                    }
                }
                rs1.close();
                total_price = Double.parseDouble(decimalFormat.format(rs.getDouble(8) - total_discount));
            }
            rs.close();
            Stm.close();
            updateJobPrice(job_ID, total_price, total_discount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //updating total price and total discount in jobs table
    public void updateJobPrice(int job_ID, double total_price, double total_discount){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Total_discount = ?, Total_price = ? WHERE Job_ID = ?;");
            Stm.setDouble(1, total_discount);
            Stm.setDouble(2,total_price);
            Stm.setInt(3, job_ID);
            Stm.executeUpdate();
            Stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
