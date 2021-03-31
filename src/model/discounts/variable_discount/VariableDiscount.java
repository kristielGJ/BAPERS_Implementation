package model.discounts.variable_discount;

import model.discounts.discount.Discount;

/**
 *
 * @author Manpreet and Gera
 */

public class VariableDiscount extends Discount {

    private int catalog_ID;
    private String task_name;

    //constructor
    public VariableDiscount(int new_customer_acc_no, double new_discount_rate, int new_catalog_ID, String new_task_name) {
        super(new_customer_acc_no, "Variable", new_discount_rate);
        this.catalog_ID = new_catalog_ID;
        this.task_name = new_task_name;
    }

    //getters and setters
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
