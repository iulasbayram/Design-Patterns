package fileAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataAccessLayer implements IDataAccessLayer{

	public DataAccessLayer() {

	}

	@Override
	public String readInputFromFile(String inputFileName, String destinationFileName) {
		String text = "";

		try {

			Scanner readFile = new Scanner(new File(inputFileName));
			
			boolean firstLineFlag = true; //Specifies first line of the text.
			
			while(readFile.hasNextLine()){
				
				String line = readFile.nextLine();
				
				text += line;
				text += "\n";
				
				if(firstLineFlag) { //We added that line to seperate added texts better in destination file.
					line = "\n__________TEXT__________"; 
				}
				
				saveText(line, destinationFileName);

				firstLineFlag = false;
			}
			
			readFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error is:" + e.getMessage());
		}
		
		return text;
	}
	
	@Override
	public void saveText(String text, String destinationFileName) {
		try {
			
			File file = new File(destinationFileName);
			if(!file.exists()) {
				file.createNewFile();
			} 

			FileWriter fw = new FileWriter(destinationFileName, true); //the true will append the new data
			text += "\n";
			
			fw.write(text); //appends the string to the file
			
			fw.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
