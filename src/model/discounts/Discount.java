package model.discounts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Gera Jahja
 * This class deals deals with the three types of discounts valued customers can have
 * ApplyDiscount is then called with this new total price and updates the database (See Discount class)
 *
 */

public class Discount {
	private static ValuedCustomer customer;
	private static Job job;
	private double sub_price;
	private double discount_rate;
	private String discount_type;
	private FixedDiscount fixed_discount;
	private FlexibleDiscount flexible_discount;
	private VariableDiscount variableDiscount;
	private static DB_Connection conn = new DB_Connection();
	private static PreparedStatement Stm = null;


	public Discount(double d_sub_price, double d_discount_rate,String d_discount_type) {
		// TODO - implement Discount.Discount
		this.sub_price=d_sub_price;
		this.discount_rate=d_discount_rate;
		this.discount_type=d_discount_type;

	}
	public static void addDiscount(double discount_rate,String discount_type,int Acc_no){

		//Discount apply_discount = new Discount(sub_price,discount_rate,discount_type);
		double sub_price_tot =GetSubPrice(Acc_no);
		if (discount_type =="fixed"){
			FixedDiscount fixed_discount_1= new FixedDiscount(discount_rate,sub_price_tot);
			UpdateCustomerDiscount(discount_type,"n/a",Acc_no);
			ApplyDiscount(discount_rate,fixed_discount_1.calculatePrice(discount_rate,sub_price_tot),Acc_no);
		}
		else if (discount_type =="flexible"){
			FlexibleDiscount flexible_discount_1= new FlexibleDiscount(sub_price_tot,discount_rate,"discount bands?");
			String info;
			info= flexible_discount_1.getDiscount_bands() +" ,"+String.valueOf(discount_rate);
			UpdateCustomerDiscount(discount_type,info,Acc_no);
			ApplyDiscount(discount_rate, flexible_discount_1.calculatePrice(discount_rate,sub_price_tot),Acc_no);
		}
		else if (discount_type =="variable") {
			VariableDiscount variable_discount_1 = new VariableDiscount(sub_price_tot,discount_rate);
			UpdateCustomerDiscount(discount_type,"n/a",Acc_no);
			ApplyDiscount(discount_rate, variable_discount_1.calculatePrice(discount_rate,job.getJob_ID()),Acc_no);
		}
		//SELECT Subtotal_price
		//	FROM Job
		//	WHERE Job_ID=1
		//


		else{
			ApplyDiscount(0.0, 0.0,Acc_no);
		}
	}
	public static double GetSubPrice(int acc_no) {
		double original_price=0.0;
		try {
			Stm = conn.connect().prepareStatement("SELECT Subtotal_price FROM Job WHERE CustomerAccount_no=? Values(?)");
			Stm.setDouble(1,acc_no);
			ResultSet rs = Stm.executeQuery();
			original_price=rs.getDouble(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return original_price;
	}
	public static int GetJobID(int acc_no) {
		int ID=-1;
		try {
			Stm = conn.connect().prepareStatement("SELECT Job_ID FROM Job WHERE CustomerAccount_no=? Values(?)");
			Stm.setInt(1,acc_no);
			ResultSet rs = Stm.executeQuery();
			ID=rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ID;
	}


	public static void ApplyDiscount(double discount_rate,double total_price, int acc_no) {
		try {
			Stm = conn.connect().prepareStatement("UPDATE Job SET Total_discount=?, Total_price=? WHERE Job_ID=? VALUES(?,?,?)");
			Stm.setDouble(1,discount_rate);
			Stm.setDouble(2, total_price);
			Stm.setInt(3,GetJobID(acc_no));
			Stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void UpdateCustomerDiscount(String discount_type,String flexible_discount_info,int acc_no) {
		customer.setDiscount_plan(discount_type);
		try {
			Stm = conn.connect().prepareStatement("UPDATE Customer SET Discount_type=?, Flexible_discount=? WHERE Account_no=? Values(?,?,?)");
			Stm.setString(1,discount_type);
			//string of format (lower_bound, higher_bound, discount_rate)
			Stm.setString(2, flexible_discount_info);
			Stm.setInt(3, acc_no);
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