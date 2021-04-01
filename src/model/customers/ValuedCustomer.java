package model.customers;
import java.util.Date;

public class ValuedCustomer extends Customer {

	private Date paymentDeadline;
	private String discount_plan;

	public ValuedCustomer(String customer_name, int acc_no, String address, String phone, String Valued, String discount_plan, String company) {
		super(customer_name, acc_no, address, phone, Valued, company);
		this.discount_plan = discount_plan;
	} //constructor for ValuedCustomer

	/**
	 * 
	 * @param paymentDeadline
	 */
	public void setPaymentDeadline(Date paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	} // set paymentDeadline variable

	public Date getPaymentDeadline() {
		return this.paymentDeadline;
	} // get paymentDeadline variable

	/**
	 * 
	 * @param discount_plan
	 */
	public void setDiscount_plan(String discount_plan) {
		this.discount_plan = discount_plan;
		throw new UnsupportedOperationException();
	} // set discount_plan variable

	public String getDiscount_plan() {
		return this.discount_plan;
	} // get discount_plan variable

}