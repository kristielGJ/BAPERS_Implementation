package model.discounts.discount;

/**
 *
 * @author Manpreet and Gera
 * Discount Class
 *
 */

public class Discount {

    private int customer_acc_no;
    private String discount_type;
    private double discount_rate;

    /**
     * Constructor
     *
     * @param new_customer_acc_no
     * @param new_discount_type
     * @param new_discount_rate
     *
     */
    public Discount(int new_customer_acc_no, String new_discount_type, double new_discount_rate){
        customer_acc_no = new_customer_acc_no;
        discount_type = new_discount_type;
        discount_rate = new_discount_rate;

    }

    /**
     * Getters and Setters
     *
     */
    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public int getCustomer_acc_no() {
        return customer_acc_no;
    }

    public void setCustomer_acc_no(int customer_acc_no) {
        this.customer_acc_no = customer_acc_no;
    }

    public double getDiscount_rate() { return discount_rate; }

    public void setDiscount_rate(double discount_rate) { this.discount_rate = discount_rate;  }
}
