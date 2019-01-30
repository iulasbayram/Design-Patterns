package domain;

import java.util.ArrayList;
import java.util.List;

import autocorrection.AutoCorrect;

//There can be a TextEditingComponent which won't use the showCountingResult method, so we created interface for every TextEditingComponent
public class AutoTextCorrecter implements TextEditingComponent, IAutoTextCorrecter{

	private String targetText;
	private String correctedText;

	public AutoTextCorrecter() {

	}

	@Override
	public void performOperationOnText(String text) {
		
		this.targetText = text;
		
		AutoCorrect textCorrecter = new AutoCorrect();
		textCorrecter.initialize();
		
		correctedText = "";
		
		String lines[] = targetText.split("\\r?\\n");
		List<Character> charsBetweenTwoWords = new ArrayList<Character>();
		
		int index;
		for (String line : lines) {
			
			index = -1;
			if (line.equals("") == false) {
				String[] words = line.split("[\\s,.!?]+");
				
				for (String word : words) {
											
					String correctedWord = textCorrecter.autoCorrect(word);
					
					if(correctedWord.equalsIgnoreCase("No Suggestions.")) {
						correctedText += word;
					} else {
						correctedText += correctedWord;	
					}
					
					index += word.length();
					index += 1;
					
					if(index < line.length()) {//That if block appends characters between two words to correctedText.
											   //There can be letters like ',!?.' between 2 words and that if block appends them.
						charsBetweenTwoWords = findCharsBetweenTwoWords(line, index);
						
						for(Character c: charsBetweenTwoWords) {
							correctedText += c;
						}

						index += charsBetweenTwoWords.size() - 1;
					}
					
					
					
				}
								
			}
			correctedText += "\n";	
		}
		showAutoCorrectionResult();
	}

	private List<Character> findCharsBetweenTwoWords(String line, int currentIndex) {
		
		List<Character> charactersBetweenTwoWords = new ArrayList<Character>();
		
		boolean flag = true;//When we reach the other word, flag becomes false;
		while(flag) {
			
			if(currentIndex < line.length()) {//If we reach the end of the line, loop is finished.
				
				if(!Character.isLetter(line.charAt(currentIndex))) {
					charactersBetweenTwoWords.add(line.charAt(currentIndex));//Adds characters between two words to list.
				
				} else {
					flag = false;//If we reach the other word, operation ends.
				}
				
			} else {
				flag = false;
			}
				
			currentIndex++;
		}
		
		return charactersBetweenTwoWords;
	}
	
	public String getTargetText() {
		return targetText;
	}

	public String getCorrectedText() {
		return correctedText;
	}

	@Override
	public void showAutoCorrectionResult() {
		System.out.println("-------------TEXT CORRECTION OPERATION-----------");
		
		System.out.println("Given text:");
		System.out.println(targetText);
		
		System.out.println("Corrected Text:");
		System.out.println(correctedText);

		System.out.println("Text correction is completed successfully...");
		System.out.println("______________________________________");		
	}
	
}
