package model.discounts;

public class FixedDiscount extends Discount {


    public FixedDiscount(int new_customer_acc_no, String new_discount_type) {
        super(new_customer_acc_no, "Fixed");
    }
}
