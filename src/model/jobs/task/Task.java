package model.jobs.task;

import model.Model;

/**
 *
 * @author Manpreet
 */

public class Task extends Model {

	private String task_status;

	/**
	 * constructor
	 *
	 * @param new_task_status
	 */
	public Task(String new_task_status) {
		task_status = new_task_status;
	}

	/**
	 * getters and setters
	 */
	public void setTask_status(String task_status) { this.task_status = task_status; }

	public String getTask_status() { return this.task_status; }


}