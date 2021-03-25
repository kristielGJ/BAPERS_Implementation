package model.jobs.cash_payment;

import model.jobs.payment.Payment;

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

}
