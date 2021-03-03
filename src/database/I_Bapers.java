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
	 * @param jobID
	 */
	abstract String verifyJob(int jobID);

	/**
	 * 
	 * @param jobID
	 */
	abstract String retrieveJobStatus(int jobID);

	/**
	 * 
	 * @param jobData
	 */
	abstract void addJob(String jobData);

	/**
	 * 
	 * @param jobID
	 * @param jobData
	 */
	abstract boolean saveJob(int jobID, String jobData);

	/**
	 * 
	 * @param jobID
	 * @param jobData
	 */
	abstract void updateStatus(int jobID, String jobData);

	/**
	 * 
	 * @param paymentData
	 */
	abstract boolean MakePayment(String paymentData);

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
	 * @param task_data
	 */
	abstract void addTask(String task_data);

	/**
	 * 
	 * @param taskData
	 */
	abstract void extendTaskList(String taskData);

	/**
	 * 
	 * @param existing_task_ID
	 */
	abstract void removeExistingTask(int existing_task_ID);

	/**
	 * 
	 * @param existing_task_ID
	 */
	abstract void updateExistingTask(int existing_task_ID);

	/**
	 * 
	 * @param customerData
	 */
	abstract void updateCustomerDetails(String customerData);

	/**
	 * 
	 * @param task_status
	 * @param location
	 */
	abstract void updateTaskInfo(String task_status, String location);

	abstract void backup();

	abstract void restore();

}