package model.jobs.cash_payment.transaction;

import model.Model;
import model.database.DB_Connection;
import model.jobs.cash_payment.Cash_payment;
import model.jobs.payment.transaction.I_Payment_Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Cash_payment_Transaction implements I_Cash_payment_Transaction {

    private DB_Connection db = new DB_Connection();
    private Connection conn = db.connect();
    private PreparedStatement Stm = null;
    private I_Payment_Transaction payment;

    /**
     *
     * @param payment_ID
     * @param payment_amount
     * @param customer_ID
     */
    public void makeCash_payment(int payment_ID, double payment_amount, int customer_ID){
        new Cash_payment(payment_ID,payment_amount);
        payment.makePayment(payment_ID,payment_amount, "Cash Payment", customer_ID);
    }

    @Override
    public Cash_payment read(int id) {
        Cash_payment cash_payment = null;
        try {
            Stm = conn.prepareStatement("SELECT * FROM Payment WHERE Payment_ID = ?");
            Stm.setInt(1, id);
            ResultSet rs = Stm.executeQuery();
            cash_payment = new Cash_payment(
                    rs.getInt(1),
                    rs.getDouble(3)
            );
            rs.close();
            Stm.close();
            System.out.println("Queried " + cash_payment.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cash_payment;
    }

    @Override
    public ArrayList<Cash_payment> getAll() {
        ArrayList<Cash_payment> cash_details = null;
        try {
            cash_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM Payment");
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Cash_payment cash_payment = new Cash_payment(
                        rs.getInt(1),
                        rs.getDouble(3)
                );
                cash_details.add(cash_payment);
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cash_details;
    }

    @Override
    public boolean remove(int id) {
        boolean removed = false;
        try {
            Stm = conn.prepareStatement("DELETE FROM Payment WHERE Payment_ID =?");
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
