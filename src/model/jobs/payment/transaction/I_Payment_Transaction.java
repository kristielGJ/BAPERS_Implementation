package model.jobs.payment.transaction;

import model.Transaction;
import java.sql.Date;

/**
 *
 * @author Manpreet
 */

public interface I_Payment_Transaction extends Transaction {

    //creates a new payment and return the generated id
    int makePayment(double payment_amount, String payment_type, Date date, int job_ID);

    //generate a invoice
    String[] generateInvoice(int payment_ID);

}
