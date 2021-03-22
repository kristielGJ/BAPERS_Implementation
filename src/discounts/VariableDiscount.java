package discounts;

public class VariableDiscount extends Discount {
	private DiscountList discount_list;
	private double discount_rate;

	public double calculatePrice() {
		// TODO - implement VariableDiscount.calculatePrice
		int ReferenceNumber=1033; //example... may have to view this differently
		double task_discount = discount_list.getTaskDiscount(ReferenceNumber);
		//get task price from job
		//calculate and return new price, maybe save in a new list called calculated_variable_discount??
		throw new UnsupportedOperationException();
	}

	public VariableDiscount(double sub_price, double discount_rate) {
		super(sub_price, discount_rate, "variable");

		// TODO - implement VariableDiscount.VariableDiscount
		throw new UnsupportedOperationException();
	}
	public DiscountList getDiscount_list() {
		return discount_list;
	}

	public void setDiscount_list(DiscountList discount_list) {
		this.discount_list = discount_list;
	}

}