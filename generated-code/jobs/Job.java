package Jobs;

public class Job {

	private String priority;
	private Time total_time;
	private Timestamp completion_deadline;
	private String special_instructions;
	private String status = Pending;

	/**
	 * 
	 * @param priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPriority() {
		return this.priority;
	}

	/**
	 * 
	 * @param total_time
	 */
	public void setTotal_time(Time total_time) {
		this.total_time = total_time;
	}

	public Time getTotal_time() {
		return this.total_time;
	}

	/**
	 * 
	 * @param completion_deadline
	 */
	public void setCompletion_deadline(Timestamp completion_deadline) {
		this.completion_deadline = completion_deadline;
	}

	public Timestamp getCompletion_deadline() {
		return this.completion_deadline;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param special_instructions
	 */
	public void setSpecial_instructions(String special_instructions) {
		this.special_instructions = special_instructions;
	}

	public String getSpecial_instructions() {
		return this.special_instructions;
	}

	/**
	 * 
	 * @param job_ID
	 * @param job_data
	 */
	public boolean saveJob(int job_ID, String job_data) {
		// TODO - implement Job.saveJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param job_ID
	 */
	public int assignJob_ID(int job_ID) {
		// TODO - implement Job.assignJob_ID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param task_data
	 */
	public boolean addTask(String task_data) {
		// TODO - implement Job.addTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param task_ID
	 */
	public boolean removeTask(int task_ID) {
		// TODO - implement Job.removeTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param task_data
	 */
	public boolean updateTask(String task_data) {
		// TODO - implement Job.updateTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param priority
	 * @param total_time
	 * @param completion_deadline
	 * @param status
	 */
	public Job(String priority, Time total_time, Timestamp completion_deadline, String status) {
		// TODO - implement Job.Job
		throw new UnsupportedOperationException();
	}

}