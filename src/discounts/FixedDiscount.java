package discounts;

import database.DB_Connection;

/**
 * Gera Jahja
 * This class deals with Fixed discounts for valued customers
 * It calculates the price with the a fixed discount rate applied to the subtotal price of a job from the database
 * ApplyDiscount is then called with this new total price and updates the database (See Discount class)
 *
 */
public class FixedDiscount extends Discount {


	public FixedDiscount(double sub_price, double discount_rate) {
		super(sub_price, discount_rate, "fixed");
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param discount_rate
	 */
	public double calculatePrice(double discount_rate,double sub_price) {
		// TODO - implement FixedDiscount.calculatePrice
		//Discount rate is a percentage
		double muliplier= 1-(discount_rate/100);
		//calculate price
		double TotalPrice= sub_price*muliplier;
		//return new price
		return TotalPrice;
	}
	public void setDiscount_rate(double discount_rate) {
		// TODO - implement FixedDiscount.setDiscount_rate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param discount_rate
	 * @param sub_price

	 */
}