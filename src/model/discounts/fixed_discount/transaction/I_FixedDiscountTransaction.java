package model.discounts.fixed_discount.transaction;

import model.discounts.fixed_discount.FixedDiscount;
import java.util.ArrayList;

/**
 *
 * @author Manpreet and Gera
 * Interface that interacts with I_Bapers
 */

public interface I_FixedDiscountTransaction {

    /**
     * Adding the fixed discount rate
     *
     * @param discount_rate
     * @param customer_acc_no
     *
     */
    void addFixedDiscountRate(int customer_acc_no, double discount_rate);
    /**
     * Returns the total discount for a job
     *
     * @param customer_acc_no
     * @param job_ID
     *
     */
    double getTotalDiscount(int job_ID, int customer_acc_no);
    /**
     * Returns the discount rate
     *
     * @param customer_acc_no
     *
     */
    double getDiscountRate(int customer_acc_no);
    /**
     * Returns a list of a fixed discount's details from the Database
     * which are associated with the customer
     *
     * @param customer_acc_no
     *
     */
    ArrayList<FixedDiscount> getFixedDiscount(int customer_acc_no);

}
