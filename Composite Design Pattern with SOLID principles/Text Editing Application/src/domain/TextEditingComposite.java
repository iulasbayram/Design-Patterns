package domain;

import java.util.ArrayList;
import java.util.List;

public class TextEditingComposite implements TextEditingComponent{

	private List<TextEditingComponent> components;
	private String text;
	
	public TextEditingComposite() {
		text = "";
		components = new ArrayList<TextEditingComponent>();
	}

	@Override
	public void performOperationOnText(String textTemp) {//every component performs its own task on given text.
		
		for(TextEditingComponent component: components) {
			
			if(component instanceof TextSaver) {
				component.performOperationOnText(text);
				text = ((TextSaver) component).getTextInput();
				
			} else {
				component.performOperationOnText(text);
			}
			
		}			
	}
	
	public void addComponent(TextEditingComponent component) {
		components.add(component);
	}
	
	public List<TextEditingComponent> getComponents() {
		return components;
	}

	public String getText() {
		return text;
	}

}
