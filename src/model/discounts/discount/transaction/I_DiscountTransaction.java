package model.discounts.discount.transaction;

/**
 *
 * @author Manpreet and Gera
 * Interface that interacts with I_Bapers
 *
 */

public interface I_DiscountTransaction {

    /**
     * Assigns a discount plan to a valued customer
     *
     * @param acc_no
     * @param discount_type
     *
     */
    void assignDiscountType(int acc_no, String discount_type);

    /**
     * Calculates the total price of a job
     *
     * @param job_ID
     *
     */
    void calculatePrice(int job_ID);

}
