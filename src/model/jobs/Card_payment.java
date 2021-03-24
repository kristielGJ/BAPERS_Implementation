package model.jobs;

import model.database.DB_Connection;
import java.sql.PreparedStatement;

public class Card_payment extends Payment {

    private int card_ID;
    private String card_type;
    private String expiry_date;
    private String last_digits;
    private static DB_Connection conn = new DB_Connection();
    private static PreparedStatement Stm = null;

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

    /**
     *
     * @param card_ID
     * @param payment_ID
     * @param payment_amount
     * @param card_type
     * @param expiry_date
     * @param last_digits
     * @param customer_ID
     */
    public static void makeCard_payment(int card_ID, int payment_ID, double payment_amount, String card_type, String expiry_date, String last_digits, int customer_ID){
        new Card_payment(card_ID, payment_ID, payment_amount, card_type, expiry_date, last_digits);
        Payment.makePayment(payment_ID, payment_amount, "Card Payment", customer_ID);
        storeCard_details(card_ID, payment_ID, card_type, expiry_date, last_digits);
    }

    /**
     *
     * @param payment_ID
     * @param card_ID
     * @param card_type
     * @param expiry_date
     * @param last_digits
     */
    public static void storeCard_details(int card_ID, int payment_ID, String card_type, String expiry_date, String last_digits){
        try {
            Stm = conn.connect().prepareStatement("INSERT INTO `bapers`.`Card_details` (`card_ID`,`Card_type`, `Expiry_date`, `Last_four_digits`) VALUES (?,?,?,?) ");
            Stm.setInt(1,card_ID);
            Stm.setString(2,card_type);

            if (expiry_date.matches("\\d{2}-\\d{4}")){
                Stm.setString(3,expiry_date);
            }else { System.out.println("Incorrect expiry date");}

            if (last_digits.matches("\\d{4}")){
                Stm.setString(4,last_digits);
            }else { System.out.println("Incorrect last four digits");}

            Stm.executeUpdate();

            // adding the card_ID in the payment table
            Stm = conn.connect().prepareStatement("UPDATE `bapers`.`Payment` SET Card_detailsCard_ID = ? WHERE Payment_ID =?;");
            Stm.setInt(1, card_ID);
            Stm.setInt(2,payment_ID);
            Stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
