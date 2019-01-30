package domain;

abstract class Person extends Resource{
		
	public Person() {
		super();
	}

	public Person(ProjectPortfolloManager mediator, int id, String name) {
		super(mediator, id, name);
	}
	
	public abstract void getATask(Task selectedTask);
	
	public abstract void removeATask(Task selectedTask);

}
