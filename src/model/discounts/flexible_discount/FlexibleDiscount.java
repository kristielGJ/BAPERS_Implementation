package model.discounts.flexible_discount;

import model.discounts.discount.Discount;

/**
 *
 * @author Manpreet and Gera
 */

public class FlexibleDiscount extends Discount {

    private int lower_bound;
    private int upper_bound;

    //constructor
    public FlexibleDiscount(int new_customer_acc_no, int new_lower_bound, int new_upper_bound, double new_discount_rate){
        super(new_customer_acc_no, "Flexible", new_discount_rate);
        lower_bound = new_lower_bound;
        upper_bound = new_upper_bound;
    }

    //getters and setters
    public int getUpper_bound() {
        return upper_bound;
    }

    public void setUpper_bound(int upper_bound) {
        this.upper_bound = upper_bound;
    }

    public int getLower_bound() {
        return lower_bound;
    }

    public void setLower_bound(int lower_bound) {
        this.lower_bound = lower_bound;
    }

}
