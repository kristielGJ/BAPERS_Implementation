package model.discounts.variable_discount.transaction;

import model.discounts.variable_discount.VariableDiscount;
import java.util.ArrayList;

/**
 *
 * @author Manpreet and Gera
 */

public interface I_VariableDiscountTransaction {

    //adding variable discount
    void addVariableDiscount(int customer_acc_no, int discount_rate, int catalog_id);

    //returns total discount for a job
    double getTotal_Discount(int job_ID, int customer_acc_no);

    //returns discount rate for each task
    double getDiscountRate(int catalog_id, int customer_acc_no);

    //return the list of catalog ids (existing tasks)
    int[] getCatalog_IDs();

    //returns a list of fixed discount which are associated with the customer
    ArrayList<VariableDiscount> getVariableDiscount(int customer_acc_no);
}
