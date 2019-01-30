package presentation;

import java.util.Scanner;

public class SearcherConsoleIO implements IConsoleIO{

	String targetCharacters;
	
	//Note: Another components' console IOs can be added to system later easily without any change.
	public SearcherConsoleIO() {
	}

	@Override
	public Object getInput() {
				
		boolean invalidInputFlag = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter characters to search in given text:");

		while (invalidInputFlag) {
			targetCharacters = scan.nextLine();
			if (!targetCharacters.matches(".*[\\p{Punct}].*") && targetCharacters.matches("[\\S].*")) {
				invalidInputFlag = false;
			} else {
				System.out.println("Invalid input, try again...");
			}
		}
		
		return targetCharacters;
		
	}
	
}
