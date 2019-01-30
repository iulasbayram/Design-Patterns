package domain.factories;

import domain.products.Computer;

public class ComputerFactoryUsingSilverCopper extends ComputerFactoryDecorator{

	public ComputerFactoryUsingSilverCopper(ComputerFactory computerFactory) {
		
		super(computerFactory);
		
	}

	//After the basic factory is decorated with silver copper, the factory will produce computer using silver copper.
	//And that will affect the speed and price of the resulting computers.
	@Override
	public Computer assembleComputer() {
		
		Computer computerWithSilverCopper = computerFactory.assembleComputer();
		
		System.out.println("Speed of computer with just copper:" + computerWithSilverCopper.getSpeed());
		double speedOfSilverCopperComputer = computerWithSilverCopper.getSpeed() * 4; //Four times faster than copper.
		computerWithSilverCopper.setSpeed(speedOfSilverCopperComputer);
		
		System.out.println("Price of computer with just copper:" + computerWithSilverCopper.getPrice());
		double priceOfSilverCopperComputer = computerWithSilverCopper.getPrice() + 350.0; //Silver cable is 350TL.
		computerWithSilverCopper.setPrice(priceOfSilverCopperComputer);
		
		return computerWithSilverCopper;
		
	}
	
}
