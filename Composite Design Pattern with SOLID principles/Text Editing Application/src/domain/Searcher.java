package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import presentation.IConsoleIO;

//There can be a TextEditingComponent which won't use the showCountingResult method, so we created interface for every TextEditingComponent
public class Searcher implements TextEditingComponent, ISearcher{
	
	private List<String> foundedWords;
	private String targetCharacters;
	private IConsoleIO searcherConsoleIO;
	
	public Searcher(IConsoleIO searcherConsoleIO) {
		foundedWords = new ArrayList<String>();
		this.searcherConsoleIO = searcherConsoleIO;
	}

	@Override
	public void performOperationOnText(String text) {
		
		String lines[] = text.split("\\r?\\n");
		
		targetCharacters = (String) searcherConsoleIO.getInput();
		
		for (String line : lines) {
			if (line.equals("") == false) {
				
				String[] words = line.split("[\\s,.!?]+");
				for (String word : words) {
					if (word.contains(targetCharacters)) {
						foundedWords.add(word);
					}
				}
			}
		}
		
		removeDuplicatedWords();
		showSearchingResult();
	}
	
	@Override
	public void showSearchingResult() {
		System.out.println("-------------SEARCHING OPERATION-----------");
		
		System.out.println("Word count containing inputted characters:" + foundedWords.size());
		
		if(foundedWords.size() > 0) {
			System.out.println("Words containing inputted characters:");
			for (String word : foundedWords) {
				System.out.println(word);
			}
			
		} else {
			System.out.println("There is no word containing given characters...");
		}
		
		System.out.println("Searching is completed successfully...");
		System.out.println("______________________________________");		
	}
	
	public void removeDuplicatedWords(){
		Set<String> tempWords = new HashSet<>();
		tempWords.addAll(foundedWords);
		foundedWords.clear();
		foundedWords.addAll(tempWords);
	}
	
	public List<String> getFoundedWords() {
		return foundedWords;
	}

	public IConsoleIO getSearcherConsoleIO() {
		return searcherConsoleIO;
	}

	public String getTargetCharacters() {
		return targetCharacters;
	}

}
