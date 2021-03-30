package model.discounts.fixed_discount;

import model.discounts.discount.Discount;

/**
 *
 * @author Manpreet and Gera
 */

public class FixedDiscount extends Discount {

    public FixedDiscount(int new_customer_acc_no, double new_discount_rate) {
        super(new_customer_acc_no, "Fixed", new_discount_rate);
    }

}
