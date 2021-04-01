package model.discounts.variable_discount.transaction;

import model.discounts.variable_discount.VariableDiscount;
import java.util.ArrayList;

/**
 *
 * @author Manpreet and Gera
 * Interface that interacts with I_Bapers
 *
 */

public interface I_VariableDiscountTransaction {

    /**
     * Adding variable discount
     *
     * @param customer_acc_no
     * @param discount_rate
     * @param catalog_name
     *
     */
    void addVariableDiscount(int customer_acc_no, Double discount_rate, String catalog_name);

    /**
     * Returns total discount for a job
     *
     * @param customer_acc_no
     * @param job_ID
     *
     */
    double getTotal_Discount(int job_ID, int customer_acc_no);

    /**
     * Returns discount rate for each task
     *
     * @param customer_acc_no
     * @param catalog_id
     *
     */
    double getDiscountRate(int catalog_id, int customer_acc_no);

    /**
     * Return the list of catalog ids (existing tasks)
     *
     * @param acc_no
     *
     */
    ArrayList<Integer>getCatalog_IDs(int acc_no);

    /**
     * Returns a list of fixed discount which are associated with the customer
     *
     * @param customer_acc_no
     *
     */
    ArrayList<VariableDiscount> getVariableDiscount(int customer_acc_no);

    /**
     * Gets a task's price from the database
     *
     * @param Catalog_ID
     *
     */
    double GetTaskPrice(int Catalog_ID);

    /**
     * Gets a task's name from the database
     *
     * @param Catalog_ID
     *
     */
    String GetTaskName(int Catalog_ID);

    /**
     * Calculates a task's discounted price
     *
     * @param discount_rate
     * @param price
     *
     */
    double calculateNewPrice(double discount_rate, double price);
    /**
     * Deletes a task discount from the database
     *
     * @param acc_no
     * @param id
     *
     */
    boolean removeVariableDiscount(int id,int acc_no);
    /**
     * Updates a task discount
     *
     * @param acc_no
     * @param discount_rate
     * @param catalog_name
     *
     */
    void updateVariableDiscount(int acc_no, Double discount_rate, String catalog_name);

    }
