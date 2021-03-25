package model.jobs;

public class Cash_payment extends Payment {

    /**
     * constructor
     *
     * @param new_payment_ID
     * @param new_payment_amount
     */
    public Cash_payment(int new_payment_ID, double new_payment_amount) {
        super(new_payment_ID, new_payment_amount, "Cash Payment");
    }

    /**
     *
     * @param payment_ID
     * @param payment_amount
     * @param customer_ID
     */
    public static void makeCash_payment(int payment_ID, double payment_amount, int customer_ID){
        new Cash_payment(payment_ID,payment_amount);
        Payment.makePayment(payment_ID,payment_amount, "Cash Payment", customer_ID);
    }


}
