package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

	private int number;
	private String desc;
	private Date startDate;
	private int hours;
	private int resourceId;
	
	public Task() {
	}
	
	public Task(int number, String desc, Date startDate, int hours) {
		this.number = number;
		this.desc = desc;
		this.startDate = startDate;
		this.hours = hours;
	}
	
	public Task(int number, String desc, Date startDate, int hours, int resourceId) {
		this.number = number;
		this.desc = desc;
		this.startDate = startDate;
		this.hours = hours;
		this.resourceId = resourceId;
	}

	public void assignAResource(Resource resource) {
		setResourceId(resource.getId());
	}
	
	public void unassignResource() {
		setResourceId(-1);
	}
		
	@Override
	public String toString() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return "Task [number=" + number + ", desc=" + desc + ", startDate=" + formatter.format(startDate) + ", resourceId=" + resourceId + "]";
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

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

}
