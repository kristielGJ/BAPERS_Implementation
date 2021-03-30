package model.discounts.variable_discount;

import model.discounts.discount.Discount;

/**
 *
 * @author Manpreet and Gera
 */

public class VariableDiscount extends Discount {

    private int catalog_ID;

    public VariableDiscount(int new_customer_acc_no, double new_discount_rate, int new_catalog_ID) {
        super(new_customer_acc_no, "Variable", new_discount_rate);
        this.catalog_ID = new_catalog_ID;
    }

}
