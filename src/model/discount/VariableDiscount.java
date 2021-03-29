package model.discount;

import model.database.DB_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;


/**
 * @author Gera
 * This class deals with Variable discount plans for valued customers
 * It calculates the price with the discounts applied to separate task prices in a job
 *
 */
public class VariableDiscount extends Discount {

    public ArrayList<Integer> JobIDS = new ArrayList<Integer>();
    public ArrayList<Integer> TaskIDS = new ArrayList<Integer>();
    private static DB_Connection conn1 = new DB_Connection();
    private static PreparedStatement Stm = null;
    private static PreparedStatement Stm_1 = null;
    DiscountHelper GetData = new DiscountHelper();
    Connection conn;

    public VariableDiscount(DB_Connection conn){
        this.conn = conn.getConn();
    }


    /**
     * calculates and returns all of the Id's that have an associated discount
     * calculates a price too, possibly Manpreet can return this and use it for Payment
     */
    public void addVariableDiscountBeforePayment(double discountRate,String discountType,int accountNumber,int TaskId){
        TaskIDS= GetData.GetIDs(accountNumber,discountType );//list of all tasks under a customer account
        JobIDS=GetData.GetIDs(accountNumber,discountType);//list of all jobs under a customer account
        double newPrice,subPrice;
        GetData.UpdateCustomerDiscountType(discountType,accountNumber);//test

        for (Integer i: TaskIDS) {
            if (TaskId==i) {
                UpdateCustomerTaskCatalog(discountRate, accountNumber, TaskId);
            }

             newPrice=GetData.calculatePrice(discountRate,GetData.GetOriginalPrice(TaskId,"Task Catalog"));
            //total_price
            System.out.println(newPrice);
        }
        double PriceDiff;
        /*for (Integer i: JobIDS) {
            subPrice= GetData.GetOriginalPrice(i,"Job Total");
            newPrice=GetData.calculatePrice(discountRate,subPrice);
            System.out.println(newPrice);
            GetData.UpdateJobDiscount(discountRate,newPrice,i);

        }*/

    }
    /**
     * Updates CustomerTaskCatalog table
     * This table stores all of the variable discounts for customers
     * identifiable by task catalog Id and Account number
     *
     */
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
    /**
     * Inserts new variable discounts to the CustomerTaskCatalog table
     * This table stores all of the variable discounts for customers
     * This should happen if the customer has never had a variable discount plan set before
     */
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
