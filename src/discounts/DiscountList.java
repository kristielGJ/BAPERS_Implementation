package discounts;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class DiscountList {
	public ArrayList<String> discount_list = new ArrayList<String>();
	public ArrayList<String> discount_ref_list = new ArrayList<String>();
	private VariableDiscount variableDiscount;
	//private Vector<Discount> discountVector = new Vector<Discount>();
	/**
	 * 
	 * @param TaskDis_Ref
	 * @param discount_rate
	 */
	public void addTaskDiscount(int TaskDis_Ref, double discount_rate) {
		// TODO - test if elements are stored correctly

		//added the discount to a list office manager can later select from
		discount_list.add(String.valueOf(discount_rate));
		//added a ref number in the same position, in another array (so we can locate later)
		discount_ref_list.add(String.valueOf(TaskDis_Ref));

		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TaskDis_Ref
	 */
	public double getTaskDiscount(int TaskDis_Ref) {
		// TODO test if ref number is able to locate the corresponding discount
		//convert TaskDis_Ref to a string so we can search the array list
		String Ref_String = String.valueOf(TaskDis_Ref);
		//Store the index of the TaskDis_Ref in discount_ref_list
		int index=discount_ref_list.indexOf(Ref_String);
		//find the corresponding value in discount_list at the same index
		String Ref_Discount = discount_list.get(index);
		//covert to double so we can return the value
		Double Ref_Discount_Double = Double.parseDouble(Ref_Discount);
		//return discount.
		return Ref_Discount_Double;

	}

	public DiscountList( VariableDiscount var_discount) {
		// TODO - implement DiscountList.DiscountList
		variableDiscount=var_discount;
		throw new UnsupportedOperationException();
	}


}