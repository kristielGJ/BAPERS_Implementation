package jobs;

public class Card_details {

	private String card_type;
	private String expiry_date;
	private int last_digits;


	/**
	 *
	 * @param card_type
	 */
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getCard_type() {
		return this.card_type;
	}

	/**
	 *
	 * @param expiry_date
	 */
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getExpiry_date() {
		return this.expiry_date;
	}

	/**
	 *
	 * @param last_digits
	 */
	public void setLast_digits(int last_digits) {
		this.last_digits = last_digits;
	}

	public int getLast_digits() {
		return this.last_digits;
	}

	/**
	 *
	 * @param card_type
	 * @param expiry_date
	 * @param last_digits
	 */
	public void storeCard_details(String card_type, String expiry_date, int last_digits) {
		// TODO - implement Job.saveJob
		throw new UnsupportedOperationException();
	}

	public void makeCardPayment(String card_type, String expiry_date, int last_digits){
		new Card_details(card_type, expiry_date, last_digits);
		storeCard_details(card_type, expiry_date, last_digits);
		// set the payment status to paid
	}

	/**
	 *
	 * @param new_card_type
	 * @param new_expiry_date
	 * @param new_last_digits
	 */
	public Card_details(String new_card_type, String new_expiry_date, int new_last_digits) {
		card_type = new_card_type;
		expiry_date = new_expiry_date;
		last_digits = new_last_digits;
	}

}