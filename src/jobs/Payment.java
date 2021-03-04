package jobs;
public class Payment {

	private double payment_amount;
	private String payment_type;

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

	/**
	 *
	 * @param payment_amount
	 * @param payment_type
	 */
	public void storePaymentDetails(double payment_amount, String payment_type) {
		// TODO - implement Payment.storePaymentDetails
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param new_payment_amount
	 * @param new_payment_type
	 */
	public Payment(double new_payment_amount, String new_payment_type) {
		payment_amount = new_payment_amount;
		payment_type = new_payment_type;
	}

}