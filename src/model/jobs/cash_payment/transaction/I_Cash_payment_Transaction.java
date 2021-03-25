package model.jobs.cash_payment.transaction;

import model.Transaction;

public interface I_Cash_payment_Transaction extends Transaction {

    void makeCash_payment(int payment_ID, double payment_amount, int customer_ID);

}
