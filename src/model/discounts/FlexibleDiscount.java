package model.discounts;

import database.DB_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Gera Jahja
 * This class deals with Flexible discounts for valued customers
 * It calculates the price with the discount bands applied to the subtotal price from the database.
 * ApplyDiscount is then called with this new total price and updates the database (See Discount class)
 *
 */

public class FlexibleDiscount extends Discount {

	private String discount_bands;
	private DB_Connection db = new DB_Connection();
	private Connection conn = db.connect();
	private static PreparedStatement Stm = null;


	public FlexibleDiscount(double sub_price, double discount_rate, String discount_bands) {
		super(sub_price, discount_rate, "flexible");
		this.discount_bands = discount_bands;
	}

	/**
	 * New Total price calculated
	 * @param discount_rate
	 * @param sub_price
	 */
	public float calculatePrice(double discount_rate,double sub_price) {
		// TODO - implement FlexibleDiscount.calculatePrice
		throw new UnsupportedOperationException();
	}

	/**Update database before calculating price
	 * @param discount_bands
	 * @param customer_acc_no
	 */
	// format of discount_rate: lower_bound, higher_bound, discount_rate; lower_bound1

	public void setDiscount_rate(String discount_bands, int customer_acc_no) {
		try {
			Stm = conn.prepareStatement("UPDATE `bapers`.`Customer` SET Flexible_discount = ? WHERE Account_no =?;");
			Stm.setString(1, discount_bands);
			Stm.setInt(2,customer_acc_no);
			Stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param discount_bands
	 */
	public void setDiscount_bands(String discount_bands) {
		this.discount_bands = discount_bands;
	}

	public String getDiscount_bands() {
		return discount_bands;
	}
}