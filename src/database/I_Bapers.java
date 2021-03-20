package database;

public interface I_Bapers {

	/**
	 * 
	 * @param data
	 */
	abstract String identifyCustomer(String data);

	/**
	 * 
	 * @param customerData
	 */
	abstract int createNewCustomer(String customerData);

	/**
	 * 
	 * @param type
	 */
	abstract void chooseReportType(String type);

	/**
	 * 
	 * @param reportData
	 */
	abstract String generateReport(String reportData);

	/**
	 * 
	 * @param customerData
	 */
	abstract void updateCustomerDetails(String customerData);

	abstract void backup();

	abstract void restore();

}