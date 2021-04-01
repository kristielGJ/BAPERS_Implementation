package model.discounts.flexible_discount;

import model.discounts.discount.Discount;

/**
 *
 * @author Manpreet and Gera
 *
 * Flexible discount extends Discount and is a
 * Discount plan that can be associated to a customer
 *
 * Flexible discounts include Bounds that apply a discount
 * Based on the volume of how much a customer spends the previous month
 *
 */

public class FlexibleDiscount extends Discount {

    private int lower_bound;
    private int upper_bound;
    private int discount_band_id;

    /**
     * Constructor
     *
     * @param new_customer_acc_no
     * @param new_discount_rate
     * @param new_discount_band_id
     * @param new_upper_bound
     * @param new_lower_bound
     *
     */
    public FlexibleDiscount(int new_customer_acc_no, int new_lower_bound, int new_upper_bound, double new_discount_rate, int new_discount_band_id){
        super(new_customer_acc_no, "Flexible", new_discount_rate);
        lower_bound = new_lower_bound;
        upper_bound = new_upper_bound;
        discount_band_id = new_discount_band_id;
    }

    /**
     * Getters and setters
     */
    public int getDiscount_band_id() {
        return discount_band_id;
    }

    public void setDiscount_band_id(int discount_band_id) {
        this.discount_band_id = discount_band_id;
    }

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
