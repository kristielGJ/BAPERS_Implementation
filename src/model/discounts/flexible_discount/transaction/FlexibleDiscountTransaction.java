package model.discounts.flexible_discount.transaction;

import model.database.DB_Connection;
import model.discounts.discount.Discount;
import model.discounts.flexible_discount.FlexibleDiscount;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Manpreet and Gera
 */

import static model.Utils.getGeneratedKey;

public class FlexibleDiscountTransaction implements I_FlexibleDiscountTransaction {

    private PreparedStatement Stm = null;
    private Connection conn;

    //constructor
    public FlexibleDiscountTransaction(DB_Connection conn){
        this.conn = conn.getConn();
    }

    //adding the flexible discount to the valued customer
    public void addFlexibleDiscount(int customer_acc_no, int lower_bound, int upper_bound, double discount_rate){
        int id = -1;
        try {
            Stm = conn.prepareStatement("INSERT INTO `bapers`.`DiscountBandCustomer` (`Account_no`) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
            Stm.setInt(1,customer_acc_no);
            Stm.executeUpdate();
            id = getGeneratedKey(Stm);
            Stm.close();

            Stm = conn.prepareStatement("INSERT INTO `bapers`.`DiscountBand` (`lower`, `upper`, `discount_rate`, `DiscountBandCustomer_ID`) VALUES (?,?,?,?) ");
            Stm.setInt(1,lower_bound);
            Stm.setInt(2,upper_bound);
            Stm.setDouble(3,discount_rate);
            Stm.setInt(4,id);
            Stm.executeUpdate();
            Stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //returns total discount for a job
    public double getTotalDiscount(int job_ID, int customer_acc_no){
        double total_discount = 0.0;
        double discount_rate = 0.0;
        try{
            //getting task_catalog_IDs associated with the job
            Stm = conn.prepareStatement("SELECT * FROM Job WHERE Job_ID = ?;");
            Stm.setInt(1,job_ID);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                discount_rate = getDiscountRate(job_ID, customer_acc_no);
                total_discount += rs.getDouble(8)*(discount_rate/100);
            }
            rs.close();
            Stm.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return total_discount;
    }

    //returns the discount rate (in percentage)
    public double getDiscountRate(int job_ID, int customer_acc_no){
        double volume = 0.0;
        double discount_rate = 0.0;
        try {
            Stm = conn.prepareStatement("SELECT * FROM Job WHERE Job_ID = ?;");
            Stm.setInt(1,job_ID);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                volume = getJobVolume(customer_acc_no);
                Stm = conn.prepareStatement("SELECT * FROM DiscountBandCustomer WHERE Account_no = ?;");
                Stm.setInt(1,customer_acc_no);
                ResultSet rs2 = Stm.executeQuery();
                while (rs2.next()){
                    Stm = conn.prepareStatement("SELECT * FROM DiscountBand WHERE DiscountBandCustomer_ID = ?;");
                    Stm.setInt(1,rs2.getInt(1));
                    ResultSet rs3 = Stm.executeQuery();
                    while (rs3.next()){
                        if(rs3.getInt(1) == 0 && rs3.getInt(2) != 0){
                            if(volume < rs3.getInt(2)){
                                discount_rate = rs3.getDouble(3);
                            }
                        }
                        else if (rs3.getInt(1) != 0 && rs3.getInt(2) == 0){
                            if(volume > rs3.getInt(1)){
                                discount_rate = rs3.getDouble(3);
                            }
                        }
                        else {
                            if(volume > rs3.getInt(1) && volume < rs3.getInt(2)){
                                discount_rate = rs3.getDouble(3);
                            }
                        }
                    }
                    rs3.close();
                }
                rs2.close();
            }
            rs.close();
            Stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount_rate;
    }

    //returns the volume of jobs (for past 1 month)
    public double getJobVolume(int customer_acc_no){
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
            rs.close();
            Stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return volume;
    }

    //returns a list of flexible discount which are associated with the customer
    public ArrayList<FlexibleDiscount> getFlexibleDiscount(int customer_acc_no) {
        ArrayList<FlexibleDiscount> discount_details = null;
        try {
            discount_details = new ArrayList<>();
            Stm = conn.prepareStatement("SELECT * FROM DiscountBandCustomer WHERE Account_no = ?");
            Stm.setInt(1, customer_acc_no);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()) {
                Stm = conn.prepareStatement("SELECT * FROM DiscountBand WHERE DiscountBandCustomer_ID = ?");
                Stm.setInt(1, rs.getInt(1));
                ResultSet rs1 = Stm.executeQuery();
                while (rs1.next()){
                    FlexibleDiscount discount = new FlexibleDiscount(
                            customer_acc_no,
                            rs1.getInt(1),
                            rs1.getInt(2),
                            rs1.getDouble(3),
                            rs1.getInt(4)
                    );
                    discount_details.add(discount);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discount_details;
    }

    public void removeFlexibleDiscount(int id) {
        try {
            Stm = conn.prepareStatement("SELECT * FROM DiscountBand WHERE discount_band_id = ?");
            Stm.setInt(1, id);
            ResultSet rs = Stm.executeQuery();
            while (rs.next()){
                PreparedStatement stm = conn.prepareStatement("DELETE FROM DiscountBand WHERE discount_band_id = ?");
                stm.setInt(1, id);
                stm.executeUpdate();
                stm.close();

                PreparedStatement st = conn.prepareStatement("DELETE FROM DiscountBandCustomer WHERE DiscountBandCustomer_ID=?");
                st.setInt(1, rs.getInt(5));
                st.executeUpdate();
                st.close();
            }
            rs.close();
            Stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Update an existing DiscountBand, set your bounds, and discount rate for the respective bound (i.e. 1000-2000, 20%)
     * and relate to the DiscountBandCustomer entry you made earlier
     *
     */
    public void updateFlexibleDiscount(int ID,double lowerBound,double upperBound,double discount_rate) {
        try {
            Stm = conn.prepareStatement("UPDATE DiscountBand SET lower=?, upper=?, discount_rate=? WHERE DiscountBandCustomer_ID=?");
            Stm.setDouble(1, lowerBound);
            Stm.setDouble(2, upperBound);
            Stm.setDouble(3, discount_rate);
            Stm.setInt(4,ID );
            Stm.executeUpdate();
            Stm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

