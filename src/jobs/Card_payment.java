package jobs;

import database.DB_Connection;
import jobs.Interface_jobs.I_Card_payment;
import jobs.Interface_jobs.I_Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Card_payment extends Payment implements I_Card_payment {

    private Connection conn;
    private PreparedStatement Stm = null;
    private I_Payment payment;

    /**
     * @param conn
     */
    public Card_payment(DB_Connection conn) {
        super(conn);
    }

    /**
     *
     * @param payment_ID
     * @param card_ID
     * @param card_type
     * @param expiry_date
     * @param last_digits
     * @param payment_amount
     * @param customer_ID
     */
    public void createCard_payment(int card_ID, int payment_ID, String card_type, String expiry_date, String last_digits, double payment_amount, int customer_ID){
        payment.createPayment(payment_ID, "Card Payment", payment_amount, customer_ID);
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`Card_details` (`card_ID`,`Card_type`, `Expiry_date`, `Last_four_digits`) VALUES (?,?,?,?) ");
            Stm.setInt(1,card_ID);
            Stm.setString(2,card_type);

            if (expiry_date.matches("\\d{2}-\\d{4}")){
                Stm.setString(3,expiry_date);
            }else { System.out.println("Incorrect format");}

            if (last_digits.matches("\\d{4}")){
                Stm.setString(4,last_digits);
            }else { System.out.println("Incorrect format");}

            Stm.executeUpdate();

            // adding the card_ID in the payment table
            Stm = conn.prepareStatement("UPDATE `bapers`.`Payment` SET Card_detailsCard_ID = ? WHERE Payment_ID =?;");
            Stm.setInt(1, card_ID);
            Stm.setInt(2,payment_ID);
            Stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
