package discounts;

import database.DB_Connection;
import java.sql.PreparedStatement;


public class Discount {

	private double sub_price;
	private double discount_rate;
	private String discount_type;
	private FixedDiscount fixed_discount;
	private FlexibleDiscount flexible_discount;
	private TaskDiscount task_discount;
	private VariableDiscount variableDiscount;
	private static DB_Connection conn = new DB_Connection();
	private static PreparedStatement Stm = null;


	public Discount(double d_sub_price, double d_discount_rate,String d_discount_type) {
		// TODO - implement Discount.Discount
		this.sub_price=d_sub_price;
		this.discount_rate=d_discount_rate;
		this.discount_type=d_discount_type;

	}
	public static void addDiscount(double discount_rate,double sub_price,String discount_type){
		Discount apply_discount = new Discount(sub_price,discount_rate,discount_type);
		if (discount_type =="fixed"){
			FixedDiscount fixed_discount_1= new FixedDiscount(discount_rate,sub_price);

			ApplyDiscount(fixed_discount_1.getDiscount_rate(),fixed_discount_1.calculatePrice(discount_rate));
		}
		else if (discount_type =="flexible"){
			FlexibleDiscount flexible_discount_1= new FlexibleDiscount(sub_price,discount_rate,"discount bands?");

			ApplyDiscount(flexible_discount_1.getDiscount_rate(), flexible_discount_1.calculatePrice(discount_rate));
		}
		else if (discount_type =="variable") {
			VariableDiscount variable_discount_1 = new VariableDiscount(sub_price,discount_rate);

			ApplyDiscount(variable_discount_1.getDiscount_rate(), variable_discount_1.calculatePrice());
		}
		else{
			ApplyDiscount(0.0, 0.0);
		}
	}

	public static void ApplyDiscount(double discount_rate,double total_price) {
		try {
			Stm = conn.connect().prepareStatement("INSERT INTO `bapers`.`Job` (`Total_discount`,`Total_price`) VALUES (?,?) ");
			Stm.setDouble(1,discount_rate);
			Stm.setDouble(2, total_price);
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
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