package domain.factories;

import domain.products.Computer;

public class ComputerFactoryDecorator implements ComputerFactory{

	//That basic factory object will be decorated using gold copper of silver copper.
	protected ComputerFactory computerFactory;
	
	public ComputerFactoryDecorator(ComputerFactory computerFactory) {
		this.computerFactory = computerFactory;
	}

	//After basic factory object is decorated with gold or silver copper, the factory will produce computers using gold or silver copper.
	@Override
	public Computer assembleComputer() {

		return computerFactory.assembleComputer();

	}
	
	public ComputerFactory getComputerFactory() {
		return computerFactory;
	}

	public void setComputerFactory(ComputerFactory computerFactory) {
		this.computerFactory = computerFactory;
	}
	
}
