package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//There can be a TextEditingComponent which won't use the showCountingResult method, so we created interface for every TextEditingComponent
public class Counter implements TextEditingComponent, ICounter{
	
	private int paragraphCount;
	private int lineCount;
	private int wordCount;
	private int characterCount;
	
	public Counter() {
		paragraphCount = 0;
		lineCount = 0;
		wordCount = 0;
		characterCount = 0;
	}
	
	@Override
	public void performOperationOnText(String text) {
		
		List<String> linesList = new ArrayList<String>();
		String lines[] = text.split("\\r?\\n");
		linesList = Arrays.asList(lines);
		
		for (int i = 0; i < linesList.size(); i++) {
			
			if (linesList.get(i).equals("") && i+1<linesList.size() && linesList.get(i+1).equals("") == false) {
				paragraphCount += 1;
			
			} else if (linesList.get(i).equals("") == false) {
				
				String[] words = linesList.get(i).split("[\\s,.!?]+");
				
				lineCount += 1;
				wordCount += words.length;
				characterCount += linesList.get(i).length();
			}
		}
		paragraphCount += 1;
		
		showCountingResult();
	}
	
	@Override
	public void showCountingResult() {
		System.out.println("-------------COUNTING OPERATION-----------");

		System.out.println("Total Paragraph number of the text:");
		System.out.println(paragraphCount);
		System.out.println("Total Line number of the text:");
		System.out.println(lineCount);
		System.out.println("Total Word number of the text:");
		System.out.println(wordCount);
		System.out.println("Total Character number of the text:");
		System.out.println(characterCount);
		
		System.out.println("Counting the paragraph, line, word and character number is completed successfully...");
		System.out.println("______________________________________");		
	}
	
	public int getParagraphCount() {
		return paragraphCount;
	}

	public int getLineCount() {
		return lineCount;
	}

	public int getWordCount() {
		return wordCount;
	}

	public int getCharacterCount() {
		return characterCount;
	}

	@Override
	public String toString() {
		return "Counter [paragraphCount=" + paragraphCount + ", lineCount=" + lineCount + ", wordCount=" + wordCount
				+ ", characterCount=" + characterCount + "]";
	}

}
