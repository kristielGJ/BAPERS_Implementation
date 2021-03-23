package discounts;

public class FlexibleDiscount extends Discount {

	private String discount_bands;

	public FlexibleDiscount(double sub_price, double discount_rate, String discount_bands) {
		super(sub_price, discount_rate, "flexible");
		this.discount_bands = discount_bands;
	}

	/**
	 * 
	 * @param discount_rate
	 */
	public float calculatePrice(double discount_rate,double sub_price) {
		// TODO - implement FlexibleDiscount.calculatePrice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param discount_bands
	 * @param discount_rate
	 */
	public void setDiscount_rate(String discount_bands, double discount_rate) {
		// TODO - implement FlexibleDiscount.setDiscount_rate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param discount_bands
	 */
	public void setDiscount_bands(String discount_bands) {
		this.discount_bands = discount_bands;
	}

	public double getDiscount_bands() {
		// TODO - implement FlexibleDiscount.getDiscount_bands
		throw new UnsupportedOperationException();
	}
}