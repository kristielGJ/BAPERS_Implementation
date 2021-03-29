package model.discounts.transaction;

import model.database.DB_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiscountTransaction {
    private PreparedStatement Stm = null;
    private Connection conn;

    //constructor
    public DiscountTransaction(DB_Connection conn){
        this.conn = conn.getConn();
    }

    //assigning a discount plan to a valued customer
    public void assignDiscountType(int acc_no, String discount_type){
        try {
            Stm = conn.prepareStatement("UPDATE `bapers`.`Customer` SET Discount_type = ? WHERE Account_no =? AND Customer_type = ?;");
            Stm.setString(1, discount_type);
            Stm.setInt(2,acc_no);
            Stm.setString(3, "Valued");
            Stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
