package model.jobs.existing_tasks;

import model.Model;

/**
 *
 * @author Manpreet
 */

public class ExistingTasks extends Model {

	private int exg_Task_ID;
	private String task_description;
	private double task_price;
	private int task_duration;
	private String department_name;

	//constructor
	public ExistingTasks(int new_exg_task_ID, String new_task_description, double new_task_price, int new_task_duration, String new_department_name) {
		exg_Task_ID = new_exg_task_ID;
		task_description = new_task_description;
		task_price = new_task_price;
		task_duration = new_task_duration;
		department_name = new_department_name;
	}

	//getters and setters
	public void setExg_Task_ID(int exg_Task_ID){ this.exg_Task_ID = exg_Task_ID; }

	public int getExg_Task_ID(){ return this.exg_Task_ID; }

	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}

	public String getTask_description() {
		return this.task_description;
	}

	public void setTask_price(double task_price) {
		this.task_price = task_price;
	}

	public double getTask_price() {
		return this.task_price;
	}

	public void setTask_duration(int task_duration) {
		this.task_duration = task_duration;
	}

	public int getTask_duration() { return this.task_duration; }

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getDepartment_name() {
		return this.department_name;
	}
}