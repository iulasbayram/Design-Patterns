package presentation;

import java.io.IOException;

import domain.Shopping;
import domain.Store;
import fileAccess.DataAccessLayer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Store store = new Store("BiMilyoncu");
		DataAccessLayer DAL = new DataAccessLayer();
		Shopping shopping = new Shopping(store, DAL);
		
		shopping.init();
	}
		
	
}
