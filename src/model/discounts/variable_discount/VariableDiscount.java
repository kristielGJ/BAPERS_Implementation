package model.discounts.variable_discount;

import model.discounts.discount.Discount;

/**
 *
 * @author Manpreet and Gera
 *
 * Variable discount extends Discount and is a
 * Discount plan that can be associated to a customer
 *
 * A variable discount apply's discount rates to each
 * Available task and uses the new Task prices
 * When a new job is created
 *
 *
 */

public class VariableDiscount extends Discount {

    private int catalog_ID;
    private String task_name;

    /**
     * Constructor
     *
     * @param new_customer_acc_no
     * @param new_discount_rate
     * @param new_catalog_ID
     * @param new_task_name
     *
     */
    public VariableDiscount(int new_customer_acc_no, double new_discount_rate, int new_catalog_ID, String new_task_name) {
        super(new_customer_acc_no, "Variable", new_discount_rate);
        this.catalog_ID = new_catalog_ID;
        this.task_name = new_task_name;
    }
    /**
     * Getters and Setters
     *
     * @return
     *
     */
    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public int getCatalog_ID() {
        return catalog_ID;
    }

    public void setCatalog_ID(int catalog_ID) {
        this.catalog_ID = catalog_ID;
    }
}
