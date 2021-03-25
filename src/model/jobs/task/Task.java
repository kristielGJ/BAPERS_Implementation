package model.jobs.task;

import model.Model;

import java.time.LocalDateTime;

public class Task extends Model {

	private int task_ID;
	private String task_status;

	/**
	 * constructor
	 *
	 * @param new_task_ID
	 * @param new_task_status
	 */
	public Task(int new_task_ID, String new_task_status) {
		task_ID = new_task_ID;
		task_status = new_task_status;
	}

	/**
	 *
	 * @param task_ID
	 */
	public void setTask_ID(int task_ID) { this.task_ID = task_ID; }

	public int getTask_ID(){ return this.task_ID; }

	/**
	 *
	 * @param task_status
	 */
	public void setTask_status(String task_status) { this.task_status = task_status; }

	public String getTask_status() { return this.task_status; }


}