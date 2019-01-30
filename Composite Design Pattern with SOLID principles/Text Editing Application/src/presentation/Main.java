package presentation;

import domain.AutoTextCorrecter;
import domain.TextEditingComponent;
import domain.TextEditingComposite;
import domain.Counter;
import domain.Searcher;
import domain.TextSaver;
import fileAccess.DataAccessLayer;
import fileAccess.IDataAccessLayer;

public class Main {

	 
	public static void main(String[] args) {
		
		System.out.println("Welcome to Text Editing Application...");
		System.out.println("______________________________________");

		IDataAccessLayer dal = new DataAccessLayer();
		
		TextEditingComponent textEditingComposite = new TextEditingComposite();		
		
		IConsoleIO textSaverConsoleIO = new TextSaverConsoleIO(dal, "input.txt", "texts.txt");
		TextEditingComponent textSaver = new TextSaver(textSaverConsoleIO); 	
		
		TextEditingComponent autoCorrecter = new AutoTextCorrecter();		
		TextEditingComponent counter = new Counter();	
		
		IConsoleIO searcherConsoleIO = new SearcherConsoleIO();//In Searcher component, we need to take target characters from user(like CTRL+F).
		TextEditingComponent searcher = new Searcher(searcherConsoleIO);		

		((TextEditingComposite) textEditingComposite).addComponent(textSaver);
		((TextEditingComposite) textEditingComposite).addComponent(autoCorrecter);
		((TextEditingComposite) textEditingComposite).addComponent(counter);
		((TextEditingComposite) textEditingComposite).addComponent(searcher);

		textEditingComposite.performOperationOnText("No Text");//Text will be taken in TextSaver component.
	}
	
	
}

