package domain.products;

public class RAM extends HardwareDevice{

	private int capacity;
	private double price;

	public RAM() {
		super();		
	}
	
	public RAM(int capacity, double price) {
		super();
		this.capacity = capacity;
		this.price = price;
	}

	@Override
	public double getPrice() {
		return price;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setPrice(double price) {
		this.price = price;
	}
		
	
}
