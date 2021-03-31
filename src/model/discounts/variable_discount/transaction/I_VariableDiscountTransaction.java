package model.discounts.variable_discount.transaction;

import model.discounts.variable_discount.VariableDiscount;
import java.util.ArrayList;

/**
 *
 * @author Manpreet and Gera
 */

public interface I_VariableDiscountTransaction {

    //adding variable discount
    void addVariableDiscount(int customer_acc_no, Double discount_rate, String catalog_name);

    //returns total discount for a job
    double getTotal_Discount(int job_ID, int customer_acc_no);

    //returns discount rate for each task
    double getDiscountRate(int catalog_id, int customer_acc_no);

    //return the list of catalog ids (existing tasks)
    ArrayList<Integer>getCatalog_IDs(int acc_no);

    //returns a list of fixed discount which are associated with the customer
    ArrayList<VariableDiscount> getVariableDiscount(int customer_acc_no);

    //Gets a task's price from the database
    double GetTaskPrice(int Catalog_ID);

    //Gets a task's name from the database
    String GetTaskName(int Catalog_ID);

    //calculates a task's discounted price
    double calculateNewPrice(double discount_rate, double price);

    boolean removeVariableDiscount(int id,int acc_no);

    void updateVariableDiscount(int acc_no, Double discount_rate, String catalog_name);

    }
