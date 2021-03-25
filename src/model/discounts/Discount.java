package model.discounts;

import model.customers.ValuedCustomer;
import model.database.DB_Connection;
import model.jobs.job.Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	private static PreparedStatement Stm_1 = null;
	public static ArrayList<Integer> Job_IDS = new ArrayList<Integer>();
	public static ArrayList<Double> prices_list = new ArrayList<Double>();



	public Discount(double d_discount_rate,String d_discount_type) {
		// TODO - implement Discount.Discount
		this.discount_rate=d_discount_rate;
		this.discount_type=d_discount_type;

	}
	public static void addDiscount(double discount_rate,String discount_type,int Acc_no){

		//Discount apply_discount = new Discount(sub_price,discount_rate,discount_type);
		GetJobID(Acc_no);
		double sub_price=0.0;

		for (Integer i: Job_IDS) {
			sub_price= GetSubPrice(i);
			//for (double sub_price: prices_list) {

			if (discount_type =="fixed"){
				FixedDiscount fixed_discount_1= new FixedDiscount(discount_rate);
				//UpdateCustomerDiscount(discount_type,"n/a",Acc_no);
				ApplyDiscount(discount_rate,fixed_discount_1.calculatePrice(discount_rate,sub_price),i);
			}
			else if (discount_type =="flexible"){
				FlexibleDiscount flexible_discount_1= new FlexibleDiscount(discount_rate,"discount bands?");
				String info;
				info= flexible_discount_1.getDiscount_bands() +" ,"+String.valueOf(discount_rate)+String.valueOf(sub_price);
				//UpdateCustomerDiscount(discount_type,info,Acc_no);
				ApplyDiscount(discount_rate, flexible_discount_1.calculatePrice(discount_rate,sub_price),i);
			}
			else if (discount_type =="variable") {
				VariableDiscount variable_discount_1 = new VariableDiscount(discount_rate);
				//UpdateCustomerDiscount(discount_type,"n/a",Acc_no);
				ApplyDiscount(discount_rate, variable_discount_1.calculatePrice(discount_rate,i),i);
			}
			//SELECT Subtotal_price
			//	FROM Job
			//	WHERE Job_ID=1
			else{
				ApplyDiscount(0.0, 0.0,Acc_no);
			}
		}
	}
	public static double GetSubPrice(int Job_ID) {
		double original_price = 0;
		try {
			Stm = conn.connect().prepareStatement("SELECT Subtotal_price FROM Job WHERE Job_ID=?");
			Stm.setInt(1,Job_ID);
			ResultSet rs = Stm.executeQuery();
			while(rs.next()){
				original_price=rs.getDouble(1);
				//prices_list.add(original_price);
				//DB_Connection.printQuery(rs);
				//System.out.println(rs.getDouble(1));
				//System.out.println(original_price);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(original_price);
		return original_price;
	}
	public static ArrayList<Integer> GetJobID(int acc_no) {
		int ID=0;
		try {
			Stm_1 = conn.connect().prepareStatement("SELECT Job_ID FROM Job WHERE CustomerAccount_no=?");
			Stm_1.setInt(1,acc_no);
			ResultSet rs3 = Stm_1.executeQuery();
			while(rs3.next()){
				ID=rs3.getInt(1);
				Job_IDS.add(ID);
				//your code sucks fix it ... there are several job ID's
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(ID);
		return Job_IDS;
	}


	public static void ApplyDiscount(double discount_rate,double total_price, int job_no) {
		//System.out.println(total_price);

		try {
			//GetJobID(acc_no);
			//for (Integer i: Job_IDS) {
			Stm = conn.connect().prepareStatement("UPDATE Job SET Total_discount=?, Total_price=? WHERE Job_ID=?");
			Stm.setDouble(1, discount_rate);
			Stm.setDouble(2, total_price);
			Stm.setInt(3, job_no);
			Stm.executeUpdate();
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void UpdateCustomerDiscount(String discount_type,String flexible_discount_info,int acc_no) {
		//customer.setDiscount_plan(discount_type);
		try {
			Stm = conn.connect().prepareStatement("UPDATE Customer SET Discount_type=?, Flexible_discount=? WHERE Account_no=?");
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