package domain.factories;

import domain.products.Computer;

public class ComputerFactoryUsingGoldCopper extends ComputerFactoryDecorator{

	public ComputerFactoryUsingGoldCopper(ComputerFactory computerFactory) {
		
		super(computerFactory);
				
	}

	//After the basic factory is decorated with gold copper, the factory will produce computer using gold copper.
	//And that will affect the speed and price of the resulting computers.
	@Override
	public Computer assembleComputer() {
		
		Computer computerWithGoldCopper = computerFactory.assembleComputer();
		
		System.out.println("Speed of computer with just copper:" + computerWithGoldCopper.getSpeed());
		double speedOfGoldCopperComputer = computerWithGoldCopper.getSpeed() * 6; //Six times faster than copper.
		computerWithGoldCopper.setSpeed(speedOfGoldCopperComputer);
		
		System.out.println("Price of computer with just copper:" + computerWithGoldCopper.getPrice());
		double priceOfGoldCopperComputer = computerWithGoldCopper.getPrice() + 500.0; //Gold cable is 500TL.
		computerWithGoldCopper.setPrice(priceOfGoldCopperComputer);
		
		return computerWithGoldCopper;
		
	}	
	
}
