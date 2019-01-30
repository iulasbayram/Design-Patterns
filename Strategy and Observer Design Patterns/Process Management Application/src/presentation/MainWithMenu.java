package presentation;

import domain.ProcessBatch;

public class MainWithMenu {

	public static void main(String[] args) {
		
		System.out.println("\nWelcome to Process Management Application...\n");
		
		ProcessBatch batch = new ProcessBatch();
		Menu menu = new Menu(batch);
		
		menu.init();
		
	}
	
}
