package presentation;

import domain.factories.ComputerFactory;
import domain.factories.ComputerFactoryUsingGoldCopper;
import domain.factories.ComputerFactoryUsingSilverCopper;
import domain.factories.GamingComputerFactory;
import domain.factories.RegularComputerFactory;
import domain.products.Computer;

public class ComputerComparisonApplication {

	public static void main(String[] args) {
		
		//---------------------------------------------------------------------------
		
		System.out.println("\n--------REGULAR COMPUTER WITH COPPER---------");
		
		System.out.println("A new Regular Computer is assembling using Copper...");
		ComputerFactory regularComputerFactory = new RegularComputerFactory();//This is basic regular computer factory object without using any decorator.
		
		Computer newRegularComputerUsingCopper = regularComputerFactory.assembleComputer();//Regular Computer is produced using copper.
		
		System.out.println("Resulting Regular Computer using Copper:");
		System.out.println(newRegularComputerUsingCopper.toString());
		
		//---------------------------------------------------------------------------
		
		System.out.println("\n--------GAMING COMPUTER WITH COPPER---------");
		
		System.out.println("A new Gaming Computer is assembling with Copper...");
		ComputerFactory gamingComputerFactory = new GamingComputerFactory();//This is basic gaming computer factory object without using any decorator.

		Computer newGamingComputerUsingCopper = gamingComputerFactory.assembleComputer();//Gaming Computer is produced using copper.
		
		System.out.println("Resulting Gaming Computer using Copper:");
		System.out.println(newGamingComputerUsingCopper.toString());
		
		//---------------------------------------------------------------------------
		
		System.out.println("\n--------REGULAR COMPUTER WITH GOLD COPPER---------");
		
		System.out.println("A new Regular Computer is assembling using Gold Copper...");
		
		//Regular computer factory is a basic factory producing computers with copper and it is decorated with GoldCopper, and means that that factory object now can produce regular computers using gold copper.
		ComputerFactoryUsingGoldCopper regularComputerFactoryUsingGoldCopper = new ComputerFactoryUsingGoldCopper(regularComputerFactory);
		
		Computer newRegularComputerUsingGoldCopper = regularComputerFactoryUsingGoldCopper.assembleComputer(); //Computer is produced using gold copper.
				
		System.out.println("Resulting Regular Computer using Gold Copper:");
		System.out.println(newRegularComputerUsingGoldCopper.toString());
		
		//---------------------------------------------------------------------------

		System.out.println("\n--------REGULAR COMPUTER WITH SILVER COPPER---------");
		
		System.out.println("A new Regular Computer is assembling using Silver Copper...");
		
		//Regular computer factory is a basic factory producing computers with copper and it is decorated with SilverCopper, and means that that factory object now can produce regular computers using silver copper.
		ComputerFactoryUsingSilverCopper regularComputerFactoryUsingSilverCopper = new ComputerFactoryUsingSilverCopper(regularComputerFactory);
		
		Computer newRegularComputerUsingSilverCopper = regularComputerFactoryUsingSilverCopper.assembleComputer();//Computer is produced using silver copper.
				
		System.out.println("Resulting Regular Computer using Silver Copper:");
		System.out.println(newRegularComputerUsingSilverCopper.toString());
		
		//---------------------------------------------------------------------------
		
		System.out.println("\n--------GAMING COMPUTER WITH GOLD COPPER---------");
		
		System.out.println("A new Gaming Computer is assembling using Gold Copper...");
		
		//Gaming computer factory is a basic factory producing computers with copper and it is decorated with GoldCopper, and means that that factory object now can produce gaming computers using gold copper.
		ComputerFactoryUsingGoldCopper gamingComputerFactoryUsingGoldCopper = new ComputerFactoryUsingGoldCopper(gamingComputerFactory);
		
		Computer newGamingComputerUsingGoldCopper = gamingComputerFactoryUsingGoldCopper.assembleComputer();//Computer is produced using gold copper.
		
		System.out.println("Resulting Gaming Computer using Gold Copper:");
		System.out.println(newGamingComputerUsingGoldCopper.toString());
		
		//---------------------------------------------------------------------------

		System.out.println("\n--------GAMING COMPUTER WITH SILVER COPPER---------");
		
		System.out.println("A new Gaming Computer is assembling using Silver Copper...");
		
		//Gaming computer factory is a basic factory producing computers with copper and it is decorated with SilverCopper, and means that that factory object now can produce gaming computers using silver copper.
		ComputerFactoryUsingSilverCopper gamingComputerFactoryUsingSilverCopper = new ComputerFactoryUsingSilverCopper(regularComputerFactory);
		
		Computer newGamingComputerUsingSilverCopper = gamingComputerFactoryUsingSilverCopper.assembleComputer();//Computer is produced using silver copper.
		
		System.out.println("Resulting Gaming Computer using Silver Copper:");
		System.out.println(newGamingComputerUsingSilverCopper.toString());
		
		//---------------------------------------------------------------------------
		
	}
	
	
	
}
