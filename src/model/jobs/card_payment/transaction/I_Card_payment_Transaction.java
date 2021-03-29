package model.jobs.card_payment.transaction;

import model.Transaction;
import java.sql.Date;

/**
 *
 * @author Manpreet
 */

public interface I_Card_payment_Transaction extends Transaction {

    //creates a new card payment
    void makeCard_payment(int payment_id, double payment_amount, String card_type, String expiry_date, String last_digits, Date date, int job_ID);

}
