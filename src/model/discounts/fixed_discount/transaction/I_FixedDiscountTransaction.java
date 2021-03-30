package model.discounts.fixed_discount.transaction;

import model.discounts.fixed_discount.FixedDiscount;
import java.util.ArrayList;

/**
 *
 * @author Manpreet and Gera
 */

public interface I_FixedDiscountTransaction {

    //adding the fixed discount rate
    void addFixedDiscountRate(int customer_acc_no, int discount_rate);

    //returns the total discount for a job
    double getTotalDiscount(int job_ID, int customer_acc_no);

    //returns the discount rate
    double getDiscountRate(int customer_acc_no);

    //returns a list of fixed discount which are associated with the customer
    ArrayList<FixedDiscount> getFixedDiscount(int customer_acc_no);

}
