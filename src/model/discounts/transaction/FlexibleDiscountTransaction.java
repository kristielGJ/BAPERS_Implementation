package model.discounts.transaction;

import model.database.DB_Connection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static model.Utils.getGeneratedKey;

public class FlexibleDiscountTransaction implements I_FlexibleDiscountTransaction {

    private PreparedStatement Stm = null;
    private Connection conn;

    //constructor
    public FlexibleDiscountTransaction(DB_Connection conn){
        this.conn = conn.getConn();
    }

    public int addFlexibleDiscount(int customer_acc_no, int lower_bound, int upper_bound, double discount_rate){
        int id = -1;
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`DiscountBandCustomer` (`Account_no`) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
            Stm.setInt(1,customer_acc_no);
            Stm.executeUpdate();
            id = getGeneratedKey(Stm);

            Stm = conn.prepareStatement("INSERT INTO `bapers`.`DiscountBand` (`lower`, `upper`, `discount_rate`, `DiscountBandCustomerID`) VALUES (?,?,?,?) ");
            Stm.setInt(1,lower_bound);
            Stm.setInt(2,upper_bound);
            Stm.setDouble(3,discount_rate);
            Stm.setInt(4,id);
            Stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void calculatePrice(int job_ID){
        try {
            Stm = conn.prepareStatement("SELECT * FROM Job WHERE Job_ID = ?;");
            Stm.setInt(1,job_ID);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                Stm = conn.prepareStatement("SELECT * FROM Customer WHERE Account_no = ?;");
                Stm.setInt(1,rs.getInt(14));
                ResultSet rs1 = Stm.executeQuery();
                while (rs1.next()){
                    if (rs1.getString(2).equals("Valued")){
                        double volume = jobVolume(rs1.getInt(1));
                        Stm = conn.prepareStatement("SELECT * FROM DiscountBandCustomer WHERE Account_no = ?;");
                        Stm.setInt(1,rs1.getInt(1));
                        ResultSet rs2 = Stm.executeQuery();
                        while (rs2.next()){
                            Stm = conn.prepareStatement("SELECT * FROM DiscountBand WHERE DiscountBand_ID = ?;");
                            Stm.setInt(1,rs2.getInt(2));
                            ResultSet rs3 = Stm.executeQuery();
                            while (rs3.next()){
                            }
                        }
                        //double total_price = rs.getDouble(8) + rs.getDouble(8)*(rate/100);
                    }
                    else {
                        Stm = conn.prepareStatement("UPDATE `bapers`.`Job` SET Total_price = ?, Total_discount = ? WHERE Job_ID = ?;");
                        Stm.setDouble(1,rs.getDouble(8));
                        Stm.setDouble(2,0);
                        Stm.setInt(3, job_ID);
                        Stm.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double jobVolume(int customer_acc_no){
        double volume = 0.0;
        try {
            Stm = conn.prepareStatement("SELECT Total_price FROM Job WHERE CustomerAccount_no = ? AND Completion_date_time BETWEEN ? AND ?;");
            Stm.setInt(1,customer_acc_no);
            Stm.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now().minusMonths(1)));
            Stm.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                volume += rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return volume;
    }
}
