package domain;

public class Employee extends Person {

	public Employee() {
		super();
	}
	
	public Employee(ProjectPortfolloManager mediator, int id, String name) {
		super(mediator, id, name);
	}

	@Override
	public void getATask(Task assignedTask) {
		super.getTasks().add(assignedTask);
		System.out.println("A task is assigned to Employee:" + getName());
	}

	@Override
	public void removeATask(Task selectedTask) {
		getTasks().remove(selectedTask);
		System.out.println("A task is removed from Consultant:" + getName());
	}
	
	@Override
	public String toString() {
		return "Employee [Id=" + getId() + ", Name=" + getName() 
						  + ", Assigned task count=" + getTasks().size() + "]";
	}

}
