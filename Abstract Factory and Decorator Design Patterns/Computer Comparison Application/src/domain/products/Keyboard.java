package domain.products;

public class Keyboard extends InputDevice{

	private int numberOfKeys;
	private boolean hasBacklight;
	private double price;

	public Keyboard() {
		super();		
	}
	
	public Keyboard(int numberOfKeys, boolean hasBacklight, double price) {
		super();
		this.numberOfKeys = numberOfKeys;
		this.hasBacklight = hasBacklight;
		this.price = price;
	}

	@Override
	public double getPrice() {
		return price;
	}
	
	public int getNumberOfKeys() {
		return numberOfKeys;
	}

	public void setNumberOfKeys(int numberOfKeys) {
		this.numberOfKeys = numberOfKeys;
	}

	public boolean isHasBacklight() {
		return hasBacklight;
	}

	public void setHasBacklight(boolean hasBacklight) {
		this.hasBacklight = hasBacklight;
	}

	public void setPrice(double price) {
		this.price = price;
	}
		
}
