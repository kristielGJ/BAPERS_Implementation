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

	// store task details int the database
	public void storeTaskDetails() {
		// TODO - implement Payment.storePaymentDetails
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param task_data
	 */
	public boolean addTask(String task_data) {
		// TODO - implement TasksList.addTask
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param index
	 */
	public Task retrieveTasks(int index) {
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