package model.jobs.payment;

import model.Model;

import java.sql.Date;

/**
 *
 * @author Manpreet
 */

public class Payment extends Model {

	private double payment_amount;
	private String payment_type;
	private Date date;

	//constructor
	public Payment(double new_payment_amount, String new_payment_type, Date new_date) {
		payment_amount = new_payment_amount;
		payment_type = new_payment_type;
		date = new_date;
	}

	//setters and getters
	public void setPayment_amount(double payment_amount) {
		this.payment_amount = payment_amount;
	}

	public double getPayment_amount() {
		return this.payment_amount;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getPayment_type() {
		return this.payment_type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}

