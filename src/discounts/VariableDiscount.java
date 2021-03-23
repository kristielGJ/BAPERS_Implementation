package discounts;

public class VariableDiscount extends Discount {
	private DiscountList discount_list;
	private double discount_rate;

	public double calculatePrice(double sub_price) {
		// TODO - implement VariableDiscount.calculatePrice
		int ReferenceNumber=1033; //example... may have to view this differently
		double task_discount = discount_list.getTaskDiscount(ReferenceNumber);
		//get task price from job

		//Total_Price=
		//return Total_Price;
		throw new UnsupportedOperationException();
	}

	public VariableDiscount(double sub_price, double discount_rate) {
		super(sub_price, discount_rate, "variable");
		throw new UnsupportedOperationException();
	}

	public void setDiscount_rate(double discount_rate) {
		//SELECT Task_CatalogCatalog_ID FROM Task WHERE Task_ID="?" VALUES(?)

		throw new UnsupportedOperationException();
	}

	public DiscountList getDiscount_list() {
		return discount_list;
	}

	public void setDiscount_list(DiscountList discount_list) {
		this.discount_list = discount_list;
	}

}