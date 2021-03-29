package model.discount;

import java.sql.Connection;
import model.database.DB_Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * @author Gera
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
    DiscountHelper GetData = new DiscountHelper();


    /**
     * calls UpdateJobFixedDiscount with a calculatedPrice() as a parameter
     *
     */
    public void addFixedDiscountBeforePayment(double discountRate,String discountType,int accountNumber){
        JobIDS=GetData.GetIDs(accountNumber,discountType);//list of all jobs under a customer account
        double newPrice, subPrice=0.0;
        UpdateCustomerFixedDiscount(discountType,accountNumber);//test

        for (Integer i: JobIDS) {
            subPrice= GetData.GetOriginalPrice(i,"Job Total");
            newPrice=GetData.calculatePrice(discountRate,subPrice);
            System.out.println(newPrice);
            GetData.UpdateJobDiscount(discountRate,newPrice,i);

        }
    }

    /**
     *Updates customer table
     *
     */
    public void UpdateCustomerFixedDiscount(String discount_type, int acc_no) {
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

}



