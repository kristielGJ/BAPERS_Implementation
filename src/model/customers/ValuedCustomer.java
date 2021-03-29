package model.customers;
import java.util.Date;
public class ValuedCustomer extends Customer {

	private Date paymentDeadline;
	private String discount_plan;

	public ValuedCustomer(String customer_name, int acc_no, String address, String phone, String Valued, String discount_plan) {
		super(customer_name, acc_no, address, phone, Valued);
		this.discount_plan = discount_plan;
	}

	/**
	 * 
	 * @param paymentDeadline
	 */
	public void setPaymentDeadline(Date paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}

	public Date getPaymentDeadline() {
		return this.paymentDeadline;
	}

	/**
	 * 
	 * @param discount_plan
	 */
	public void setDiscount_plan(String discount_plan) {
		this.discount_plan = discount_plan;
		throw new UnsupportedOperationException();
	}

	public String getDiscount_plan() {
		return this.discount_plan;
	}

}