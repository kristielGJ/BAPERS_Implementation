package model.discount;

import java.sql.Connection;

import GUI.UpdateCustomerDetails;
import model.database.DB_Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.discount.DiscountHelper;


/**
 * Gera Jahja
 * This class deals with Variable discount plans for valued customers
 * It calculates the price with the discounts applied to separate task prices in a job
 *
 */
public class VariableDiscount extends Discount {

    public ArrayList<Integer> JobIDS = new ArrayList<Integer>();
    public ArrayList<Integer> TaskIDS = new ArrayList<Integer>();
    private static DB_Connection conn1 = new DB_Connection();
    public DiscountHelper GetData;
    private static PreparedStatement Stm = null;
    private static PreparedStatement Stm_1 = null;

    /**
     * calculates the price with all task discount rates applied
     *
     */
    public double calculatePrice(double discount_rate,int TaskId,String discountType, double price) {
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
     * calculates and returns all of the Id's that have an associated discount
     *
     */
    public void addVariableDiscountBeforePayment(double discountRate,String discountType,int accountNumber,int TaskId){
        DiscountHelper GetData = new DiscountHelper(conn1,accountNumber);
        TaskIDS= GetData.GetIDs(accountNumber,discountType );//list of all tasks under a customer account
        JobIDS=GetData.GetIDs(accountNumber,discountType);//list of all jobs under a customer account
        double newPrice=0.0;
        GetData.UpdateCustomerDiscountType(discountType,accountNumber);//test

        for (Integer i: TaskIDS) {
            if (TaskId==i) {
                UpdateCustomerTaskCatalog(discountRate, accountNumber, TaskId);
            }else{
                UpdateCustomerTaskCatalog(0, accountNumber, i);
            }
             newPrice=calculatePrice(discountRate,i,discountType,GetData.GetOriginalPrice(TaskId,discountType));
             System.out.println(newPrice);
        }
       // for (Integer i: JobIDS) {
          //  subPrice= GetData.GetOriginalPrice(i,discountType);
          //  GetData.UpdateJobDiscount(discountRate,calculatePrice(),i);

        //}
    }

    public void UpdateCustomerTaskCatalog(double discountRate,double AccNo, int TaskId) {

        try {
            Stm = conn1.connect().prepareStatement("UPDATE CustomerTaskCatalog SET discount_rate=? WHERE Account_no=? AND Catalog_ID=?");
            Stm.setDouble(1, discountRate);
            Stm.setDouble(2, AccNo);
            Stm.setInt(3, TaskId);
            Stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void CreateCustomerTaskCatalog(double discountRate,int AccNo, int TaskId) {

        try {
            Stm = conn1.connect().prepareStatement("INSERT INTO CustomerTaskCatalog (Account_no, discount_rate, Catalog_ID) VALUES (?, ?, ?)");
            Stm.setInt(1, AccNo);
            Stm.setDouble(2, discountRate);
            Stm.setInt(3, TaskId);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addVariableDiscountBeforePayment(discountRate,"variable",AccNo,TaskId);
    }

}
