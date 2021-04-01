package model.jobs.card_payment;

import java.sql.Date;

/**
 *
 * @author Manpreet
 */

public class Card_payment extends model.jobs.payment.Payment {

    private String card_type;
    private String expiry_date;
    private String last_digits;
    private String security_code;


   //constructor
    public Card_payment(double new_payment_amount, String new_card_type, String new_expiry_date, String new_last_digits, Date date, String new_security_code) {
        super(new_payment_amount, "Card Payment", date);
        card_type = new_card_type;
        expiry_date = new_expiry_date;
        last_digits = new_last_digits;
        security_code = new_security_code;
    }

    //getters and setters

    public String getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(String security_code) {
        this.security_code = security_code;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_type() {
        return this.card_type;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getExpiry_date() {
        return this.expiry_date;
    }

    public void setLast_digits(String last_digits) {
        this.last_digits = last_digits;
    }

    public String getLast_digits() {
        return this.last_digits;
    }

}
