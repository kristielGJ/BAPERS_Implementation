package model.jobs.payment.transaction;

import model.Transaction;
import model.jobs.payment.Payment;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public interface I_Payment_Transaction extends Transaction {

    //creates a new payment and return the generated id
    int makePayment(double payment_amount, String payment_type, Date date, int job_ID);

    //generate a invoice
    String[] generateInvoice(int payment_ID);

    public ArrayList<Payment> getAll();

}
