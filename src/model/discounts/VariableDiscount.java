package model.discounts;

import model.database.DB_Connection;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Gera Jahja
 * This class deals with Variable discounts for valued customers
 * It calculates the price with the discounts applied to separate task prices in a job
 * ApplyDiscount is then called with this new total price and updates the database (See Discount class)
 *
 */
public class VariableDiscount extends Discount {
	private DiscountList discount_list;
	public ArrayList<ResultSet> price_list = new ArrayList<ResultSet>();
	private DB_Connection db = new DB_Connection();
	private Connection conn = db.connect();
	private static DB_Connection conn1 = new DB_Connection();
	private static PreparedStatement Stm = null;
	double Total_Price;


	public double calculatePrice(double discount_rate,int Job_ID) {
		setDiscount_rate(Job_ID ,discount_rate);
		//set Discount populates the price_list, updates and selects from the database
		//SELECT Price FROM Task_Catalog WHERE Task_CatalogCatalog_ID=1
		for (ResultSet i: price_list) {
			System.out.println(i);//test

			Double DoublePrice = Double.parseDouble(String.valueOf(i));

			// TODO test if ref number is able to locate the corresponding discount
			//convert TaskDis_Ref to a string so we can search the array list
			String Ref_String = String.valueOf(Job_ID);
			//Store the index of the TaskDis_Ref in discount_ref_list
			int index=(discount_list.getDiscount_ref_list()).indexOf(Ref_String);
			//find the corresponding value in discount_list at the same index
			String Ref_Discount = (discount_list.getDiscount_list()).get(index);
			//covert to double so we can calculate
			Double Ref_Discount_Double = Double.parseDouble(Ref_Discount);
			Total_Price+= (DoublePrice*Ref_Discount_Double);
		}
		return Total_Price;
		//throw new UnsupportedOperationException();
	}

	public VariableDiscount(double sub_price, double discount_rate) {
		super(sub_price, discount_rate, "variable");
		throw new UnsupportedOperationException();
	}

	public void setDiscount_rate(int Job_ID ,double discount_rate) {
		try {
			Stm = conn.prepareStatement("SELECT Task_ID FROM Task WHERE JobJob_ID=? VALUES(?)");
			Stm.setString(1, String.valueOf(Job_ID));
			ResultSet rs = Stm.executeQuery();//get the Job_ID

			while (rs.next()) {
				Stm = conn.prepareStatement("SELECT Task_CatalogCatalog_ID FROM Task WHERE Task_ID=? VALUES(?)");
				Stm.setString(1, String.valueOf(rs));
				ResultSet rs2 = Stm.executeQuery();//get prices using these task catalog Id's

				//UPDATE Task SET Discount_rate=0 WHERE Task_ID=1
				Stm = conn1.connect().prepareStatement("UPDATE Task SET Discount_rate=? WHERE Task_ID=1=? VALUES(?,?)");
				Stm.setDouble(1,discount_rate);
				Stm.setString(2,String.valueOf(rs));
				Stm.executeUpdate();// update discounts in task table

				while (rs2.next()) {
					Stm = conn.prepareStatement("SELECT Price FROM Task_Catalog WHERE Catalog_ID=? VALUES(?)");
					Stm.setString(1, String.valueOf(rs2));
					ResultSet rs3 = Stm.executeQuery();//get prices and update discount in task!
					while (rs3.next()) {
						//Make a price list for calculate price to use with discount list
						price_list.add(rs3);
						//addTaskDiscount, store all discounts in a list!
						discount_list.addTaskDiscount(Job_ID,discount_rate);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new UnsupportedOperationException();
	}

	public DiscountList getDiscount_list() {
		return discount_list;
	}

	public void setDiscount_list(DiscountList discount_list) {
		this.discount_list = discount_list;
	}

}