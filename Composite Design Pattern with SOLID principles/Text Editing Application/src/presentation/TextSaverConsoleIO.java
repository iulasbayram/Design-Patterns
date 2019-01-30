package presentation;

import java.util.Scanner;
import fileAccess.IDataAccessLayer;

public class TextSaverConsoleIO implements IConsoleIO{

	IDataAccessLayer dal;
	String text;
	String sourceFileName;
	String destinationFileName;
	
	public TextSaverConsoleIO() {
	}
	
	public TextSaverConsoleIO(IDataAccessLayer dal, String sourceFileName, String destinationFileName) {
		this.dal = dal;
		this.sourceFileName = sourceFileName;
		this.destinationFileName = destinationFileName;
		text = "";
	}

	@Override
	public Object getInput() {
		
		TakingInputType type = getTakingInputType();
		
		if(type.equals(TakingInputType.FILE)) {
			//Method readInputFromFile(): reads input from input.txt file and save its content to texts.txt file.
			text = dal.readInputFromFile(sourceFileName, destinationFileName);
			
		} else {//CONSOLE choice.
			Scanner scan = new Scanner(System.in);

			System.out.println("Enter paragraph count of text:");
			int paragraphCount = getNumber();
						
			for(int i=0; i<paragraphCount; i++) {
				System.out.println("Enter line count of the paragraph " + (i + 1));
				int lineCount = getNumber();
				
				for(int j=0; j<lineCount; j++) {
					System.out.println("Please enter the text of line " + (j + 1));
					
					String textTemp = "";
					boolean invalidInputFlag = true;
					
					while (invalidInputFlag) {
						textTemp = scan.nextLine();
						
						if (!textTemp.equals("") && !textTemp.equals(" ")) {
							invalidInputFlag = false;
							text += textTemp;
							
						} else {
							System.out.println("Please give a valid text...");
						}
						
					}
					
					dal.saveText(textTemp, destinationFileName);
					text += "\n";
				}
				text += "\n";
			}	
		}
		
		return text;
	}

	public TakingInputType getTakingInputType() {
		
		TakingInputType type = null;
		
		boolean loopFlag = true;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please choose one of the option to take text input:");
		System.out.println("(Give its number to choose)");
		System.out.println("1)Console\n2)File");
		while (loopFlag) {
			
			String chosenOperation = scanner.nextLine();
			
			if (chosenOperation.equalsIgnoreCase("1")) { //Input from console.
				type = TakingInputType.CONSOLE;
				loopFlag = false;
				
			} else if (chosenOperation.equalsIgnoreCase("2")) { //Input from txt file
				type = TakingInputType.FILE;	
				loopFlag = false;
				
			} else {		
				System.out.println("Please give a valid index...");
			}	
		}
		
		return type;
	}

	public int getNumber() {
		String choice = "";
		Scanner input = new Scanner(System.in);
		boolean validInputFlag = false;

		while (!validInputFlag) {
			choice = input.nextLine();
			if (choice.matches("[0-9].*") && choice.matches("[\\S].*") 
					&& Integer.parseInt(choice) > 0) {
				validInputFlag = true;
			} else {
				System.out.println("Enter a valid number...");
			}
		}
		return Integer.parseInt(choice);
	}
	
	
	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getDestinationFileName() {
		return destinationFileName;
	}

	public void setDestinationFileName(String destinationFileName) {
		this.destinationFileName = destinationFileName;
	}

	public String getText() {
		return text;
	}
	
	public IDataAccessLayer getDal() {
		return dal;
	}

	public void setDal(IDataAccessLayer dal) {
		this.dal = dal;
	}
	
}
