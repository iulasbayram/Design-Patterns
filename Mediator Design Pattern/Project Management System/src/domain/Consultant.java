package domain;

public class Consultant extends Person{

	public Consultant() {
		super();
	}
	
	public Consultant(ProjectPortfolloManager mediator, int id, String name) {
		super(mediator, id, name);
	}

	@Override
	public void getATask(Task assignedTask) {
		getTasks().add(assignedTask);
		System.out.println("A task is assigned to Consultant:" + getName());
	}

	@Override
	public void removeATask(Task selectedTask) {
		getTasks().remove(selectedTask);
		System.out.println("A task is removed from Consultant:" + getName());

	}
	
	@Override
	public String toString() {
		return "Consultant [Id=" + getId() + ", Name=" + getName() 
						   + ", Assigned task count=" + getTasks().size() + "]";
	}
	
}
