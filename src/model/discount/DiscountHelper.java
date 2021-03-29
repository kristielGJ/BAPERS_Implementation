package model.discount;

import model.database.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Gera
 * This class aids with the application of three types of discount plans valued customers can have
 * Generalised functions that more than one discount can apply are in this class
 */

public class DiscountHelper {

    private Connection conn;
    private int accountNumber;
    private static PreparedStatement Stm_1 = null;
    private static PreparedStatement Stm = null;
    private static DB_Connection conn1 = new DB_Connection();
    public ArrayList<Integer> IDS = new ArrayList<Integer>();



    public DiscountHelper(){
    }
    /**
     * calculates the price with all task discount rates applied
     *
     */
    public double calculatePrice(double discount_rate, double price) {
        double TotalPrice=0,multiplier;
        System.out.println(price);
        //Discount rate is a percentage
        multiplier= 1-(discount_rate/100);
        //calculate price
        TotalPrice = price*multiplier;
        //return new price
        return TotalPrice;
    }
    /**
     * Retrieves all the Id's associated to a customer's account , from the database
     * for variable discounts - task ID's (task catalog id's)
     * for fixed discounts - job ID's
     * for flexible discounts - DiscountBandCustomer_ID's
     *
     */
    public  ArrayList<Integer> GetIDs(int accNo,String discountType) {
        int ID=0;
        try {
            if (discountType=="fixed") {
                Stm_1 = conn1.connect().prepareStatement("SELECT Job_ID FROM Job WHERE CustomerAccount_no=?");
            }
            else if(discountType=="variable"){
                Stm_1 = conn1.connect().prepareStatement("SELECT Catalog_ID FROM CustomerTaskCatalog WHERE Account_no=?");
            }
            else if(discountType=="flexible"){
                Stm_1 = conn1.connect().prepareStatement("SELECT DiscountBandCustomer_ID FROM DiscountBandCustomer WHERE Account_no =?");
            }
            Stm_1.setInt(1,accNo);
            ResultSet rs3 = Stm_1.executeQuery();
            while(rs3.next()){
                ID=rs3.getInt(1);
                IDS.add(ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IDS;
    }
    /**
     * Retrieves the Prices associated to a Job or Task ,from the database
     * Task price- for variable discounts
     * Subtotal Price - for fixed discounts
     *
     */
    public double GetOriginalPrice(int ID,String priceWanted) {
        double original_price = 0;
        try {
            if (priceWanted=="Job Total") {
                Stm = conn1.connect().prepareStatement("SELECT Subtotal_price FROM Job WHERE Job_ID=? AND Payment_status=?");
                Stm.setString (2,"pending");
            }
            else if(priceWanted=="Task Catalog"){
                Stm = conn1.connect().prepareStatement("SELECT Price FROM Task_Catalog WHERE Catalog_ID=?");
            }
            Stm.setInt(1,ID);
            ResultSet rs = Stm.executeQuery();
            while(rs.next()){
                original_price=rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return original_price;
    }
    /**
     * Updates the customer table in the database
     * For valued and flexible discount types
     *
     */
    public void UpdateCustomerDiscountType(String discount_type,int acc_no) {
        try {
            Stm = conn1.connect().prepareStatement("UPDATE Customer SET Discount_Type= ? WHERE Account_no= ? AND Customer_type=?");
            Stm.setString(1,discount_type);
            Stm.setInt(2, acc_no);
            Stm.setString(3, "Valued");
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Updates the Total Discount rate for a Job in the database
     * Updates the Total Price rate for a Job in the database
     * Does this if the payment is Pending
     * All discount Types
     *
     */
    public void UpdateJobDiscount(double discountRate,double totalPrice, int jobId) {

        try {
            Stm = conn1.connect().prepareStatement("UPDATE Job SET Total_discount=?, Total_price=? WHERE Job_ID=? and Payment_status=?");
            Stm.setDouble(1, discountRate);
            Stm.setDouble(2, totalPrice);
            Stm.setInt(3, jobId);
            Stm.setString(4, "pending");
            Stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
