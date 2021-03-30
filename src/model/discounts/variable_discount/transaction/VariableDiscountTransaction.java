package model.discounts.variable_discount.transaction;

import model.database.DB_Connection;
import model.discounts.variable_discount.VariableDiscount;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Manpreet and Gera
 */

public class VariableDiscountTransaction implements I_VariableDiscountTransaction {

    private PreparedStatement Stm = null;
    private Connection conn;

    //constructor
    public VariableDiscountTransaction(DB_Connection conn){
        this.conn = conn.getConn();
    }

    //adding variable discount
    public void addVariableDiscount(int customer_acc_no, int discount_rate, int catalog_id){
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`CustomerTaskCatalog` (`Account_no`, `discount_rate`, `Catalog_ID`) VALUES (?,?,?)");
            Stm.setInt(1,customer_acc_no);
            Stm.setDouble(2, discount_rate);
            Stm.setInt(3, catalog_id);
            Stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //returns total discount for a job
    public double getTotal_Discount(int job_ID, int customer_acc_no){
        double total_discount = 0.0;
        double discount_rate = 0.0;
        try{
            //getting task_catalog_IDs associated with the job
            Stm = conn.prepareStatement("SELECT * FROM Task WHERE JobJob_ID = ?;");
            Stm.setInt(1,job_ID);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                discount_rate = getDiscountRate(rs.getInt(7), customer_acc_no);
                Stm = conn.prepareStatement("SELECT * FROM Task_Catalog WHERE Catalog_ID = ?;");
                Stm.setInt(1,rs.getInt(7));
                ResultSet rs1 = Stm.executeQuery();
                while (rs1.next()){
                    total_discount += rs1.getDouble(3)*(discount_rate/100);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return total_discount;
    }

    //returns discount rate for each task (discount in percentage)
    public double getDiscountRate(int catalog_id, int customer_acc_no){
        double discount_rate = 0.0;
        try{
            Stm = conn.prepareStatement("SELECT * FROM CustomerTaskCatalog WHERE Account_no = ? AND Catalog_ID = ?;");
            Stm.setInt(1,customer_acc_no);
            Stm.setInt(2,catalog_id);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                discount_rate += rs.getDouble(2);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return discount_rate;
    }

    //return the list of catalog ids (existing tasks)
    public int[] getCatalog_IDs(){
        int i = 1;
        int[] catalog_ids = new int[i];
        try{
            Stm = conn.prepareStatement("SELECT * FROM Task_catalog;");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                catalog_ids[i-1] = rs.getInt(1);
                i++;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return catalog_ids;
    }

    //returns a list of fixed discount which are associated with the customer
    public ArrayList<VariableDiscount> getVariableDiscount(int customer_acc_no) {
        ArrayList<VariableDiscount> discount_details = null;
        try {
            discount_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM CustomerTaskCatalog WHERE Account_no = ?");
            Stm.setInt(1, customer_acc_no);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                 VariableDiscount discount = new VariableDiscount(
                        customer_acc_no,
                        rs.getDouble(2),
                        rs.getInt(3)
                );
                discount_details.add(discount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discount_details;
    }

}
