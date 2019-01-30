package domain;

import java.util.ArrayList;
import java.util.List;

public class Resource{

	private int id;
	private String name;
	private List<Task> tasks;
	private ProjectPortfolloManager mediator;
	
	public Resource() {
		
	}
	
	public Resource(ProjectPortfolloManager mediator, int id, String name) {
		this.id = id;
		this.name = name;	
		this.tasks = new ArrayList<Task>();
		this.mediator = mediator;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
