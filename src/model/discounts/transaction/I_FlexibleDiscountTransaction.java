package model.discounts.transaction;

public interface I_FlexibleDiscountTransaction {

    int addFlexibleDiscount(int customer_acc_no, int lower_bound, int upper_bound, double discount_rate);

    double jobVolume(int customer_acc_no);

}
