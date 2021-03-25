package model.jobs.payment;

import model.Model;

public class Payment extends Model {

	private int payment_ID;
	private double payment_amount;
	private String payment_type;

	/**
	 * constructor
	 *
	 * @param new_payment_ID
	 * @param new_payment_amount
	 * @param new_payment_type
	 */
	public Payment(int new_payment_ID, double new_payment_amount, String new_payment_type) {
		payment_ID = new_payment_ID;
		payment_amount = new_payment_amount;
		payment_type = new_payment_type;
	}

	/**
	 *
	 * @param payment_ID
	 */
	public void setPayment_ID(int payment_ID) { this.payment_ID = payment_ID; }

	public int getPayment_ID(){ return this.payment_ID; }

	/**
	 *
	 * @param payment_amount
	 */
	public void setPayment_amount(double payment_amount) {
		this.payment_amount = payment_amount;
	}

	public double getPayment_amount() {
		return this.payment_amount;
	}

	/**
	 *
	 * @param payment_type
	 */
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getPayment_type() {
		return this.payment_type;
	}

}

