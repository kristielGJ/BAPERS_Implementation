package discounts;

public class Discount {

	private double sub_price;
	private double discount_rate;
	private String discount_type;
	private FixedDiscount fixed_discount;
	private FlexibleDiscount flexible_discount;
	private DiscountList discount_list;
	private TaskDiscount task_discount;
	private VariableDiscount variableDiscount;

	public Discount(double sub_price, double discount_rate,String discount_type) {
		// TODO - implement Discount.Discount
		this.sub_price=sub_price;
		this.discount_rate=discount_rate;
		this.discount_type=discount_type;

	}

	public double getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(double discount_rate) {
		this.discount_rate = discount_rate;
	}

	public FixedDiscount getFixed_discount() {
		return fixed_discount;
	}

	public void setFixed_discount(FixedDiscount fixed_discount) {
		this.fixed_discount = fixed_discount;
	}

	public FlexibleDiscount getFlexible_discount() {
		return flexible_discount;
	}

	public void setFlexible_discount(FlexibleDiscount flexible_discount) {
		this.flexible_discount = flexible_discount;
	}

	public DiscountList getDiscount_list() {
		return discount_list;
	}

	public void setDiscount_list(DiscountList discount_list) {
		this.discount_list = discount_list;
	}

	public TaskDiscount getTask_discount() {
		return task_discount;
	}

	public void setTask_discount(TaskDiscount task_discount) {
		this.task_discount = task_discount;
	}

	public VariableDiscount getVariableDiscount() {
		return variableDiscount;
	}

	public void setVariableDiscount(VariableDiscount variableDiscount) {
		this.variableDiscount = variableDiscount;
	}


	public double getSub_price() {
		return sub_price;
	}

	public void setSub_price(double sub_price) {
		this.sub_price = sub_price;
	}

}