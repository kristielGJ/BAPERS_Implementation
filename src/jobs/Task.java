package jobs;

import java.time.LocalDateTime;

public class Task {

	private String task_status;
	private LocalDateTime start_time;
	private LocalDateTime finish_time;

	/**
	 *
	 * @param task_status
	 */
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}

	public String getTask_status() {
		return this.task_status;
	}

	/**
	 * store the starting time of the task in the database
	 *
	 * @param start_time
	 */
	public void start(LocalDateTime start_time) {
	}

	/**
	 * stores the finish_time of the task in the database
	 *
	 * @param finish_time
	 */
	public void finish(LocalDateTime finish_time) {
	}

	/**
	 * store task details in the database
	 *
	 * @param ExistingTask_ID
	 * @param task_status
	 */
	public void storeTaskDetails(int ExistingTask_ID, String task_status) {
		// TODO - implement Payment.storePaymentDetails
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param task_status
	 */
	public void addTask(String task_status) {
		new Task(task_status);
		//storeTaskDetails();
	}

	/**
	 *
	 * @param task_ID
	 */
	public Task retrieveTasks(int task_ID) {
		// TODO - implement TasksList.retrieveTasks
		throw new UnsupportedOperationException();
	}

	/**
	 * constructor
	 *
	 * @param new_task_status
	 */
	public Task(String new_task_status) {
		task_status = new_task_status;
	}

}