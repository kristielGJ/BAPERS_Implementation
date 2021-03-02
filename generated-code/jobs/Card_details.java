package jobs;
import java.util.Date;

public class Card_details {

	private int card_ID;
	private String card_type;
	private Date expiry_date;
	private int last_digits;

	/**
	 * 
	 * @param card_ID
	 */
	public void setCard_ID(int card_ID) {
		this.card_ID = card_ID;
	}

	public int getCard_ID() {
		return this.card_ID;
	}

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
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public Date getExpiry_date() {
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

	public void storeCardDetails() {
		// TODO - implement Card_details.storeCardDetails
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param card_ID
	 * @param card_type
	 * @param expiry_date
	 * @param last_digits
	 */
	public Card_details(int card_ID, String card_type, Date expiry_date, int last_digits) {
		// TODO - implement Card_details.Card_details
		throw new UnsupportedOperationException();
	}

}