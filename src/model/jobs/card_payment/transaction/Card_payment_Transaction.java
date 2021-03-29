package model.jobs.card_payment.transaction;

import model.Model;
import model.database.DB_Connection;
import model.jobs.card_payment.Card_payment;
import model.jobs.payment.transaction.I_Payment_Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Card_payment_Transaction implements I_Card_payment_Transaction {

    private DB_Connection db = new DB_Connection();
    private Connection conn = db.connect();
    private PreparedStatement Stm = null;
    private I_Payment_Transaction payment;

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
    public void makeCard_payment(int card_ID, int payment_ID, double payment_amount, String card_type, String expiry_date, String last_digits, int customer_ID){
        new Card_payment(card_ID, payment_ID, payment_amount, card_type, expiry_date, last_digits);
        payment.makePayment(payment_ID, payment_amount, "Card Payment", customer_ID);
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
    public void storeCard_details(int card_ID, int payment_ID, String card_type, String expiry_date, String last_digits){
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

    @Override
    public Card_payment read(int id) {
        Card_payment card_payment = null;
        try {
            Stm = conn.prepareStatement("SELECT Card_detailsCard_ID FROM Payment WHERE Payment_ID = ?");
            Stm.setInt(1, id);
            ResultSet rs1 = Stm.executeQuery();
            Stm = conn.prepareStatement("SELECT * FROM Card_detials WHERE Card_ID = ?");
            Stm.setInt(1, rs1.getInt("Card_detailsCard_ID"));
            ResultSet rs = Stm.executeQuery();
            card_payment = new Card_payment(
                    rs.getInt(1),
                    id,
                    rs.getDouble(3),
                    rs.getString(2),
                    rs.getString(4),
                    rs.getString(5)
            );
            rs.close();
            Stm.close();
            System.out.println("Queried " + card_payment.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return card_payment;
    }

    @Override
    public ArrayList<Card_payment> getAll() {
        ArrayList<Card_payment> card_details = null;
        try {
            card_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Card_details");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Stm = conn.prepareStatement("SELECT Payment_ID FROM Payment WHERE Card_detailsCard_ID = ? ");
                Stm.setInt(1, rs.getInt(1));
                ResultSet rs1 = Stm.executeQuery();
                Card_payment card_payment = new Card_payment(
                        rs.getInt(1),
                        rs1.getInt("Payment_ID"),
                        rs.getDouble(3),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getString(5)
                );
                card_details.add(card_payment);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return card_details;
    }

    @Override
    public boolean remove(int id) {
        boolean removed = false;
        try {
            Stm = conn.prepareStatement("DELETE FROM Card_details WHERE Card_ID=?");
            Stm.setInt(1, id);
            int update = Stm.executeUpdate();
            Stm.close();
            System.out.println("Removed " + id);
            removed = update == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return removed;
    }

}
