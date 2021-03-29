package model.discount;

import model.database.DB_Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Gera
 * This class deals with Flexible discounts for valued customers
 * It calculates the price with the discount bands applied to the subtotal price from the database.
 * ApplyDiscount is then called with this new total price and updates the database (See Discount class)
 *
 */

public class FlexibleDiscount extends Discount {

    public ArrayList<Integer> JobIDS = new ArrayList<Integer>();
    public ArrayList<Integer> DiscountID = new ArrayList<Integer>();
    public ArrayList<Double> Bands = new ArrayList<>();
    private static DB_Connection conn1 = new DB_Connection();
    private static PreparedStatement Stm = null;
    private static PreparedStatement Stm_1 = null;
    DiscountHelper GetData = new DiscountHelper();



    public void addFlexibleDiscountBeforePayment(double lowerBound,double upperBound,double discountRate,String discountType,int discountBandCustomerID,int accountNumber){
        JobIDS=GetData.GetIDs(accountNumber,discountType);//list of all jobs under a customer account
        double newPrice, subPrice=0.0;
        UpdateDiscountBandCustomer(discountBandCustomerID,lowerBound,upperBound,discountRate);//test

        for (Integer i: JobIDS) {
            subPrice= GetData.GetOriginalPrice(i,"Job Total");
            newPrice=GetData.calculatePrice(discountRate,subPrice);
            System.out.println(newPrice);
            GetData.UpdateJobDiscount(discountRate,newPrice,i);

        }
    }

    /**
     * Get all discount band details from a customer.
     *
     */
    public ArrayList<Double>GetCustomerDiscountBands(int ID) {
        int i=1;
        try {
            Stm = conn1.connect().prepareStatement("SELECT * FROM DiscountBand WHERE DiscountBandCustomer_ID=?;");
            Stm.setInt(1,ID);
            ResultSet rs = Stm.executeQuery();
            while(rs.next()){
                while (i<6){
                Bands.add(rs.getDouble(i));
                i+=1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Bands;
    }

    /**
     * Update DiscountBandCustomer to include the new DiscountBound that have been created (see Create methods)
     *
     */
    public void UpdateDiscountBandCustomerID(int DiscountBandID, int DiscountBandCustID) {

        try {
            Stm = conn1.connect().prepareStatement("UPDATE DiscountBandCustomer SET DiscountBand_ID=? WHERE DiscountBandCustomer_ID=?;");
            Stm.setDouble(1, DiscountBandID);
            Stm.setDouble(2, DiscountBandCustID);
            Stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Have Customer ID from GUI, put Customer ID into DiscountBandCustomer
     *
     */
    public void CreateDiscountBandCustomer(int AccNo) {
        try {
            Stm = conn1.connect().prepareStatement("INSERT INTO DiscountBandCustomer (Account_no) VALUES (?);");
            Stm.setInt(1, AccNo);
            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Create a DiscountBand, set your bounds, and discount rate for the respective bound (i.e. 1000-2000, 20%)
     * and relate to the DiscountBandCustomer entry you made earlier
     *
     */
    public void CreateDiscountBand(int BandID,double lowerBound,double upperBound,double discount_rate,int accountNumber) {

        DiscountID=GetData.GetIDs(accountNumber,"flexible");
        //UpdateDiscountBandCustomerID(BandID,DiscountID.get(0));
        try {
            Stm = conn1.connect().prepareStatement("INSERT INTO DiscountBand(lower, upper, discount_rate, DiscountBandCustomer_Id) VALUES (?,?,?,?);");
            Stm.setDouble(1, lowerBound);
            Stm.setDouble(2, upperBound);
            Stm.setDouble(3, discount_rate);
            Stm.setInt(4,BandID);

            Stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Update an existing DiscountBand, set your bounds, and discount rate for the respective bound (i.e. 1000-2000, 20%)
     * and relate to the DiscountBandCustomer entry you made earlier
     *
     */
    public void UpdateDiscountBandCustomer(int ID,double lowerBound,double upperBound,double discount_rate) {
        try {
            Stm = conn1.connect().prepareStatement("UPDATE DiscountBand SET lower=?, upper=?, discount_rate=? WHERE DiscountBandCustomer_ID=?");
            Stm.setDouble(1, lowerBound);
            Stm.setDouble(2, upperBound);
            Stm.setDouble(3, discount_rate);
            Stm.setInt(4, ID);
            Stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}