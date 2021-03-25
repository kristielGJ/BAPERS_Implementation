package model.jobs.card_payment;

public class Card_payment extends model.jobs.payment.Payment {

    private int card_ID;
    private String card_type;
    private String expiry_date;
    private String last_digits;


    /**
     * constructor
     *
     * @param new_card_ID
     * @param new_payment_amount
     * @param new_card_type
     * @param new_expiry_date
     * @param new_last_digits
     */
    public Card_payment(int new_card_ID, int new_payment_ID, double new_payment_amount, String new_card_type, String new_expiry_date, String new_last_digits) {
        super(new_payment_ID, new_payment_amount, "Card Payment");
        card_ID = new_card_ID;
        card_type = new_card_type;
        expiry_date = new_expiry_date;
        last_digits = new_last_digits;
    }

    /**
     *
     * @param card_ID
     */
    public void setCard_ID(int card_ID){ this.card_ID = card_ID; }

    public int getCard_ID(){ return this.card_ID; }

    /**
     *
     * @param card_type
     */
    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_type() {
        return this.card_type;
    }

    /**
     *
     * @param expiry_date
     */
    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getExpiry_date() {
        return this.expiry_date;
    }

    /**
     *
     * @param last_digits
     */
    public void setLast_digits(String last_digits) {
        this.last_digits = last_digits;
    }

    public String getLast_digits() {
        return this.last_digits;
    }

}
