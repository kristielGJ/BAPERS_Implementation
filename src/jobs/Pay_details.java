package jobs;

public class Pay_details {

	private int pay_ID;
	private double price;
	private String payment_status = "Unpaid";

	/**
	 * 
	 * @param pay_ID
	 */
	public void setPay_ID(int pay_ID) {
		this.pay_ID = pay_ID;
	}

	public int getPay_ID() {
		return this.pay_ID;
	}

	/**
	 * 
	 * @param price
	 */
	public void calculatePrice(double price) {
		// TODO - implement Pay_details.calculatePrice
		throw new UnsupportedOperationException();
	}

	public double getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @param payment_status
	 */
	public void setPaymentStatus(String payment_status) {
		// TODO - implement Pay_details.setPaymentStatus
		throw new UnsupportedOperationException();
	}

	public String getPaymentStatus() {
		// TODO - implement Pay_details.getPaymentStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pay_ID
	 * @param price
	 * @param payment_status
	 */
	public Pay_details(int pay_ID, double price, String payment_status) {
		// TODO - implement Pay_details.Pay_details
		throw new UnsupportedOperationException();
	}

}