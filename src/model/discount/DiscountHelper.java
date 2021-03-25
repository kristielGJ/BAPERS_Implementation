package model.discount;

import model.database.DB_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DiscountHelper {

    private Connection conn;
    private int accountNumber;

    public DiscountHelper(DB_Connection conn, int accountNumber) {
        this.conn = conn.getConn();
        this.accountNumber = accountNumber;
    }

    public void applyDiscountType(String discountType) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE Customer SET Discount_type=? WHERE Account_no=?");
            st.setString(1, discountType);
            st.setInt(2, accountNumber);
            st.executeUpdate();
            st.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void applyFixedDiscount(int fixedDiscountAmount) {
        try {
            PreparedStatement st = conn.prepareStatement(
                    "UPDATE Customer SET Fixed_discount=? WHERE Account_no=? AND Customer_type=?"
            );
            st.setInt(1, fixedDiscountAmount);
            st.setInt(2, accountNumber);
            st.setString(3, "Valued");
            st.executeUpdate();
            st.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateTotalDiscount(int jobId) {
        try {
            PreparedStatement st = conn.prepareStatement(
                    "SELECT Job_ID, Total_discount WHERE Job_ID=? AND CustomerAccount_No=?"
            );
            st.setInt(1, jobId);
            st.setInt(2, accountNumber);
            ResultSet rs = st.executeQuery();
            conn.setAutoCommit(false);
            while (rs.next()) {
                g
                int currentId = rs.getInt(1);
                st = conn.prepareStatement("UPDATE Job SET Total_discount=? WHERE Job_ID=?");
                st.setInt(1, rs.getInt(2));
                st.setInt(2, currentId);
                st.executeUpdate();
            }
            conn.commit();
            conn.setAutoCommit(true);
            rs.close();
            st.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
