package Jobs;

public class Payment {

	private int payment_ID;
	private double payment_amount;
	private string payment_type;

	/**
	 * 
	 * @param payment_ID
	 */
	public void setPayment_ID(int payment_ID) {
		this.payment_ID = payment_ID;
	}

	public int getPayment_ID() {
		return this.payment_ID;
	}

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
	public void setPayment_type(string payment_type) {
		this.payment_type = payment_type;
	}

	public string getPayment_type() {
		return this.payment_type;
	}

	/**
	 * 
	 * @param paymentDetails
	 */
	public void storePaymentDetails(String paymentDetails) {
		// TODO - implement Payment.storePaymentDetails
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param payment_ID
	 * @param payment_amount
	 * @param payment_type
	 */
	public Payment(int payment_ID, double payment_amount, string payment_type) {
		// TODO - implement Payment.Payment
		throw new UnsupportedOperationException();
	}

}