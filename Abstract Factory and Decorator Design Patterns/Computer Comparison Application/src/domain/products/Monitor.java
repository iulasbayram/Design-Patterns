package domain.products;

public class Monitor extends OutputDevice{

	private double inch;
	private double price;

	public Monitor() {
		super();
	}
	
	public Monitor(double inch, double price) {
		super();
		this.inch = inch;
		this.price = price;
	}

	@Override
	public double getPrice() {
		return price;
	}
	
	public double getInch() {
		return inch;
	}

	public void setInch(double inch) {
		this.inch = inch;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
