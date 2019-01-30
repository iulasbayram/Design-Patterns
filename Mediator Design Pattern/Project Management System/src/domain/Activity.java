package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity {
	
	private List<Task> tasks;
	private int number;
	private String desc;
	private Date startDate;
	private String deliverable;
	
	public Activity() {
		
	}
	
	public Activity(int number, String desc, Date startDate, String deliverable) {
		this.number = number;
		this.desc = desc;
		this.startDate = startDate;
		this.deliverable = deliverable;
		tasks = new ArrayList<Task>();
	}

	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void removeTask(Task task) {
		tasks.remove(task);
	}
	
	public int getDuration() {
		int activityDurationByHour = 0;
		for(Task task: tasks) {
			activityDurationByHour += task.getHours();
		}
		return activityDurationByHour;
	}

	@Override
	public String toString() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return "Activity [number=" + number + ", desc=" + desc + ", startDate=" + formatter.format(startDate) + ", deliverable="
				+ deliverable + "]";
	}
	
	public Task getTaskByIndex(int index) {
		Task task = tasks.get(index);
		return task;
	}
		
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDeliverable() {
		return deliverable;
	}

	public void setDeliverable(String deliverable) {
		this.deliverable = deliverable;
	}

}
