package model.discounts;
import java.util.*;

public class DiscountList {

	public ArrayList<String> discount_list = new ArrayList<String>();
	public ArrayList<String> discount_ref_list = new ArrayList<String>();
	private VariableDiscount variableDiscount;

	public DiscountList(ArrayList<String> discount_list,ArrayList<String> discount_ref_list ) {
		this.discount_list = discount_list;
		this.discount_ref_list = discount_ref_list;

	}
	//private Vector<Discount> discountVector = new Vector<Discount>();
	/**
	 *
	 * @param Job_Ref
	 * @param discount_rate
	 */
	public void addTaskDiscount(int Job_Ref, double discount_rate) {
		// TODO - test if elements are stored correctly
		//added the discount to a list office manager can later select from
		discount_list.add(String.valueOf(discount_rate));
		//added a ref number in the same position, in another array (so we can locate later)
		discount_ref_list.add(String.valueOf(Job_Ref));

		///throw new UnsupportedOperationException();
	}

	public void setVariableDiscount(VariableDiscount variableDiscount) {
		this.variableDiscount = variableDiscount;
	}

	public ArrayList<String> getDiscount_ref_list() {
		return discount_ref_list;
	}

	public ArrayList<String> getDiscount_list() {
		return discount_list;
	}
}


