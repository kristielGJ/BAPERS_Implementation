package model.jobs.payment.transaction;

import model.Transaction;
import model.jobs.payment.Payment;

import java.util.ArrayList;

public interface I_Payment_Transaction extends Transaction {

    void makePayment(int payment_ID, double payment_amount, String payment_type, int customer_ID);

    String[] generateInvoice(int payment_ID);

    public ArrayList<Payment> getAll();
}
