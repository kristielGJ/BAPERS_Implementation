package model.jobs.card_payment.transaction;

import model.Transaction;

public interface I_Card_payment_Transaction extends Transaction {

    void makeCard_payment(int card_ID, int payment_ID, double payment_amount, String card_type, String expiry_date, String last_digits, int customer_ID);

}
