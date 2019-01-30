package presentation;

import domain.ProjectPortfolloManager;
import fileAccess.DataAccessLayer;

public class Main {

	public static void main(String[] args) {
		
		ProjectPortfolloManager mediator = new ProjectPortfolloManager();
								
		mediator.start();
		
	}

}
