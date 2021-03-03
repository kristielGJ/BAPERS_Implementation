package customers;
import java.util.Date;
public class ValuedCustomer extends Customer {
	//ggggggggggggg
	private Date paymentDeadline;
	private String discount_plan;

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
	public void setDiscount_plan(int discount_plan) {
		// TODO - implement ValuedCustomer.setDiscount_plan
		throw new UnsupportedOperationException();
	}

	public String getDiscount_plan() {
		return this.discount_plan;
	}

	public ValuedCustomer() {
		// TODO - implement ValuedCustomer.ValuedCustomer
		throw new UnsupportedOperationException();
	}

}