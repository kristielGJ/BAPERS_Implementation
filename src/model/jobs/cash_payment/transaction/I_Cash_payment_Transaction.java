package model.jobs.cash_payment.transaction;

import model.Transaction;
import model.jobs.cash_payment.Cash_payment;

import java.util.ArrayList;

public interface I_Cash_payment_Transaction extends Transaction {

    void makeCash_payment(int payment_ID, double payment_amount, int customer_ID);
    public ArrayList<Cash_payment> getAll();
}
