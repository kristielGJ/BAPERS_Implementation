package model.discounts.flexible_discount.transaction;

import model.discounts.flexible_discount.FlexibleDiscount;
import java.util.ArrayList;

/**
 *
 * @author Manpreet and Gera
 * Interface that interacts with I_Bapers
 *
 */

public interface I_FlexibleDiscountTransaction {

    /**
     * Adding the flexible discount to the valued customer
     *
     * @param discount_rate
     * @param customer_acc_no
     * @param upper_bound
     * @param lower_bound
     *
     */
    void addFlexibleDiscount(int customer_acc_no, int lower_bound, int upper_bound, double discount_rate);

    /**
     * Returns total discount for a job
     *
     * @param job_ID
     * @param customer_acc_no
     *
     */
    double getTotalDiscount(int job_ID, int customer_acc_no);

    /**
     * Returns the volume of jobs (for past 1 month)
     *
     * @param customer_acc_no
     *
     */
    double getJobVolume(int customer_acc_no);

    /**
     * Returns the discount rate
     *
     * @param customer_acc_no
     * @param job_ID
     *
     */
    double getDiscountRate(int job_ID, int customer_acc_no);

    /**
     * Returns a list of a flexible discount's info
     * which are associated with a customer
     *
     * @param customer_acc_no
     *
     */
    ArrayList<FlexibleDiscount> getFlexibleDiscount(int customer_acc_no);

    /**
     * Deletes a discount
     *
     * @param id
     *
     */
    void removeFlexibleDiscount(int id);

    /**
     * Updates an existing discount
     *
     * @param upperBound
     * @param discount_rate
     * @param lowerBound
     * @param ID
     *
     */
    void updateFlexibleDiscount(int ID,double lowerBound,double upperBound,double discount_rate);

    }
