package discounts;

import database.DB_Connection;
import jobs.Job;

public class FixedDiscount extends Discount {
		//private static Job job_refference = new Job();


	public FixedDiscount(double sub_price, double discount_rate) {
		super(sub_price, discount_rate, "fixed");
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param discount_rate
	 */
	public void setDiscount_rate(double discount_rate) {
		// TODO - implement FixedDiscount.setDiscount_rate
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
}