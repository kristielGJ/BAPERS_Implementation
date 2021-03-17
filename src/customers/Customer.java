package customers;

public class Customer {

	private String customer_name;
	private int acc_no;
	private String address;
	private String phone;

    public Customer() {

    }

	/**
	 *
	 * @param customer_name
	 * @param acc_no
	 * @param address
	 * @param phone
	 */
	public Customer(String customer_name, int acc_no, String address, String phone) {
		this.customer_name = customer_name;
		this.acc_no = acc_no;
		this.address = address;
		this.phone = phone;
		throw new UnsupportedOperationException();
	} //constructor for class

	/**
	 * 
	 * @param customer_name
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	} //set local variable customer_name to given String customer_name

	public String getCustomer_name() {
		return this.customer_name;
	} //return the value of local String customer_name

	/**
	 * 
	 * @param acc_no
	 */
	public void setAccNo(int acc_no) {
		this.acc_no = acc_no;
		throw new UnsupportedOperationException();
	} //set local variable acc_no to given int acc_no

	public int getAccNo() {
		return this.acc_no;
	} //return the value of local int acc_no

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	} //set local variable address to given String address

	public String getAddress() {
		return this.address;
	} //return the value of local String address

	/**
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	} //set local variable phone to given String phone

	public String getPhone() {
		return this.phone;
	} //return the value of local String phone

	/**
	 * 
	 * @param customerData
	 */
	public void updateCustomerDetails(String[] customerData) { //receive array of Strings called customerData
		if(!customerData[0].isEmpty() && !(customerData[0] == null)){ //test if String in array is empty or null
			this.customer_name = customerData[0]; //first string should be customer_name set that to local customer_name
		}
		if(!customerData[1].isEmpty() && !(customerData[1] == null)) { //test if String in array is empty or null
			this.acc_no = Integer.parseInt(customerData[1]);  //first string should be acc_no set that to local acc_no after obtaining integer value from string
		}
		if(!customerData[2].isEmpty() && !(customerData[2] == null)) { //test if String in array is empty or null
			this.address = customerData[2]; //first string should be customer_name set that to local customer_name
		}
		if(!customerData[3].isEmpty() && !(customerData[3] == null)) { //test if String in array is empty or null
			this.phone = customerData[3]; //first string should be customer_name set that to local customer_name
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customer_ID
	 * @param customer_data
	 */
	//database saving, still needs to be done
	public boolean saveCustomerDetails(int customer_ID, String customer_data) {
		// TODO - implement Customer.saveCustomerDetails (database)
		throw new UnsupportedOperationException();
	}

}