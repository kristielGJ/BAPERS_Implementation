package model.discounts.fixed_discount;

import model.discounts.discount.Discount;

/**
 *
 * @author Manpreet and Gera
 *
 * Fixed discount extends Discount and is a
 * Discount plan that can be associated to a customer
 *
 * Fixed discounts apply a fixed discount rate to the total Job price
 *
 */

public class FixedDiscount extends Discount {
    /**
     * @param new_discount_rate
     * @param new_customer_acc_no
     *
     */

    public FixedDiscount(int new_customer_acc_no, double new_discount_rate) {
        super(new_customer_acc_no, "Fixed", new_discount_rate);
    }

}
