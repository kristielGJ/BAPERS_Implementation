package model.discounts;

public class Discount {

    private int customer_acc_no;
    private String discount_type;

    //constructor
    public Discount(int new_customer_acc_no, String new_discount_type){
        customer_acc_no = new_customer_acc_no;
        discount_type = new_discount_type;
    }

    //getters and setters
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
}
