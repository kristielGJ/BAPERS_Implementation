package model.jobs.card_payment.transaction;

import model.Transaction;
import model.jobs.card_payment.Card_payment;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Manpreet
 */

public interface I_Card_payment_Transaction extends Transaction {

    //creates a new card payment
    void makeCard_payment(int payment_id, double payment_amount, String card_type, String expiry_date, String last_digits, Date date, int job_ID);
    public ArrayList<Card_payment> getAll();

}
