package model.discounts.flexible_discount.transaction;

import model.discounts.flexible_discount.FlexibleDiscount;
import java.util.ArrayList;

/**
 *
 * @author Manpreet and Gera
 */

public interface I_FlexibleDiscountTransaction {

    //adding the flexible discount to the valued customer
    void addFlexibleDiscount(int customer_acc_no, int lower_bound, int upper_bound, double discount_rate);

    //returns total discount for a job
    double getTotalDiscount(int job_ID, int customer_acc_no);

    //returns the volume of jobs (for past 1 month)
    double getJobVolume(int customer_acc_no);

    //returns the discount rate
    double getDiscountRate(int job_ID, int customer_acc_no);

    //returns a list of flexible discount which are associated with the customer
    ArrayList<FlexibleDiscount> getFlexibleDiscount(int customer_acc_no);

}
