package fileAccess;

public interface IDataAccessLayer {

	//Reads the given text from 
	public String readInputFromFile(String sourceFileName, String destinationFileName);//
	
	public void saveText(String text, String destinationFileName);
	
}
