package domain;

import presentation.IConsoleIO;

//There can be a TextEditingComponent which won't use the showCountingResult method, so we created interface for every TextEditingComponent
public class TextSaver implements TextEditingComponent, ITextSaver{

	IConsoleIO textSaverConsoleIO;
	String textInput;

	public TextSaver(IConsoleIO textSaverConsoleIO) {
		this.textSaverConsoleIO = textSaverConsoleIO;
		textInput = "";
	}
		
	@Override
	public void performOperationOnText(String text) {
		
		textInput = (String) textSaverConsoleIO.getInput();
		
		showTextSavingResult();//That method is added for simulation.
	}

	@Override
	public void showTextSavingResult() {
		System.out.println("-------------TEXT SAVING OPERATION-----------");
		System.out.println("Text input is taken successfully...");
		System.out.println("Text input is saved to file successfully...");
		System.out.println("______________________________________");		
	}
	
	public String getTextInput() {
		return textInput;
	}
	
	public IConsoleIO getTextSaverConsoleIO() {
		return textSaverConsoleIO;
	}
	
}
