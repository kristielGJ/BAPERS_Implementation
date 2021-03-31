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
            Stm.close();
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
            rs.close();
            Stm.close();
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
            rs.close();
            Stm.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return discount_rate;
    }

    //return the list of catalog ids (existing tasks)
    public ArrayList<Integer> getCatalog_IDs(int accNo){
        ArrayList<Integer> IDS = new ArrayList<Integer>();
        int ID=0;
        try {
            Stm = conn.prepareStatement("SELECT Catalog_ID FROM CustomerTaskCatalog WHERE Account_no=?");
            Stm.setInt(1,accNo);
            ResultSet rs = Stm.executeQuery();
            while(rs.next()){
                ID=rs.getInt(1);
                IDS.add(ID);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IDS;
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
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discount_details;
    }
    /**
     * Retrieves the Prices associated to a Task ,from the database
     * Task price- for variable discounts
     */
    public double GetTaskPrice(int Catalog_ID) {
        double original_price = 0;
        try {

            Stm = conn.prepareStatement("SELECT Price FROM Task_Catalog WHERE Catalog_ID=?");
            Stm.setInt(1,Catalog_ID);
            ResultSet rs = Stm.executeQuery();
            while(rs.next()){
                original_price=rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return original_price;

    }
    public String GetTaskName(int Catalog_ID) {
        String name = " ";
        try {

            Stm = conn.prepareStatement("SELECT Task_name FROM Task_Catalog WHERE Catalog_ID=?");
            Stm.setInt(1,Catalog_ID);
            ResultSet rs = Stm.executeQuery();
            while(rs.next()){
                name=rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;

    }
    /**
     * calculates a discounted Task Price
     *
     * */
    public double calculateNewPrice(double discount_rate, double price) {
        double TotalPrice=0,multiplier;
        System.out.println(price);
        //Discount rate is a percentage
        multiplier= 1-(discount_rate/100);
        //calculate price
        TotalPrice = price*multiplier;
        //return new price
        return TotalPrice;
    }

    public boolean removeVariableDiscount(int id, int acc_no){
        boolean removed = false;
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM CustomerTaskCatalog WHERE Account_no=? AND CustomerTaskCatalog_ID=?");
            st.setInt(1, acc_no);
            st.setInt(2, id);
            int update = st.executeUpdate();
            st.close();
            System.out.println("Removed " + id);
            removed = update == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return removed;
    }

    public void updateVariableDiscount(int acc_no, int discount_rate, int catalog_id){
        try {
            Stm = conn.prepareStatement("UPDATE CustomerTaskCatalog SET discount_rate=? WHERE Account_no=? AND Catalog_ID=?");
            Stm.setDouble(1, discount_rate);
            Stm.setDouble(2, acc_no);
            Stm.setInt(3, catalog_id);
            Stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }


