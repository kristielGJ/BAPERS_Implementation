package model.discounts.discount.transaction;

/**
 *
 * @author Manpreet and Gera
 */

public interface I_DiscountTransaction {

    //assigning a discount plan to a valued customer
    void assignDiscountType(int acc_no, String discount_type);

    //calculating the total price of a job
    void calculatePrice(int job_ID);

}
