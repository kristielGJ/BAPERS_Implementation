package model.customers;
import java.util.Date;

public class ValuedCustomer extends Customer {

	private Date paymentDeadline;
	private String discount_plan;

	/**
	 * constructor for ValuedCustomer
	 * @param customer_name
	 * @param acc_no
	 * @param address
	 * @param phone
	 * @param Valued
	 * @param discount_plan
	 * @param company
	 */
	public ValuedCustomer(String customer_name, int acc_no, String address, String phone, String Valued, String discount_plan, String company) {
		super(customer_name, acc_no, address, phone, Valued, company);
		this.discount_plan = discount_plan;
	}

	/**
	 * set paymentDeadline variable
	 * @param paymentDeadline
	 */
	public void setPaymentDeadline(Date paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}

	/**
	 * get paymentDeadline variable
	 * @return
	 */
	public Date getPaymentDeadline() {
		return this.paymentDeadline;
	}

	/**
	 * set discount_plan variable
	 * @param discount_plan
	 */
	public void setDiscount_plan(String discount_plan) {
		this.discount_plan = discount_plan;
		throw new UnsupportedOperationException();
	}

	/**
	 *  get discount_plan variable
	 * @return
	 */
	public String getDiscount_plan() {
		return this.discount_plan;
	}

}