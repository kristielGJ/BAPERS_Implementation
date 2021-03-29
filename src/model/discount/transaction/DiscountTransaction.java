package model.discount.transaction;

import model.Model;
import model.Transaction;
import model.database.DB_Connection;
import model.discount.Discount;
import model.discount.FixedDiscount;
import model.discount.FlexibleDiscount;
import model.discount.VariableDiscount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DiscountTransaction implements Transaction {

    Connection conn;

    public DiscountTransaction(DB_Connection conn) {
        this.conn = conn.getConn();
    }

    public Discount applyDiscountType(String discountType, int accountId) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE Customer SET discount_type=? WHERE Account_no=?");
            st.setString(1, discountType);
            st.setInt(2, accountId);
            st.executeUpdate();
            st.close();
            if (discountType == "fixed") {
                return new FixedDiscount();
            }else if (discountType == "variable"){
                return new VariableDiscount();
            }else if (discountType == "flexible"){
                return new FlexibleDiscount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Model read(int id) {
        return null;
    }

    protected void getJobId() {

    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}