package model.discount;

import java.sql.Connection;
import model.database.DB_Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.discount.DiscountHelper;


/**
 * Gera Jahja
 * This class deals with Fixed discount plans for valued customers
 * It calculates the price with the a fixed discount rate applied to the subtotal price of a job from the database
 *
 */
public class FixedDiscount extends Discount {

    public ArrayList<Integer> JobIDS = new ArrayList<Integer>();
    private static DB_Connection conn1 = new DB_Connection();
    private Connection conn;
    private int accountNumber;
    private static PreparedStatement Stm = null;
    private static PreparedStatement Stm_1 = null;




    /**
     * calculates the price with a fixed discount rate applied
     *
     */
    public double calculatePrice(double discount_rate,double sub_price) {
        //Discount rate is a percentage
        double multiplier= 1-(discount_rate/100);
        //calculate price
        double TotalPrice= sub_price*multiplier;
        //return new price
        return TotalPrice;
    }
    /**
     * calls UpdateJobFixedDiscount with a calculatedPrice() as a parameter
     *
     */
    public void addFixedDiscountBeforePayment(double discountRate,String discountType,int accountNumber){
        DiscountHelper GetData = new DiscountHelper(conn1,accountNumber);
        JobIDS=GetData.GetIDs(accountNumber,discountType);//list of all jobs under a customer account
        double subPrice=0.0;
        UpdateCustomerFixedDiscount(discountType,discountRate,accountNumber);//test

        for (Integer i: JobIDS) {
            subPrice= GetData.GetOriginalPrice(i,discountType);
            GetData.UpdateJobDiscount(discountRate,calculatePrice(discountRate,subPrice),i);

        }
    }

    /**
     *Updates customer table
     *
     */
    public void UpdateCustomerFixedDiscount(String discount_type,double discount_rate,int acc_no) {
        try {
            Stm = conn1.connect().prepareStatement("UPDATE Customer SET Fixed_discount=? , Discount_Type= ? WHERE Account_no= ? AND Customer_type=?");
            Stm.setString(2,discount_type);
            Stm.setDouble(1, discount_rate);
            Stm.setInt(3, acc_no);
            Stm.setString(4, "Valued");
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



