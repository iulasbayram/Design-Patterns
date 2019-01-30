package domain;

import java.util.Random;

public class PlacedOrder implements OrderState {

	@Override
	public void prepareOrder(Order order, Customer customerOfOrder) {
		
		System.out.println("Order is in placed state...");
		
		if(order.getState() instanceof SavedOrder) {
			
			double cargoPrice = getRandomDeliveryDistance(100, 500) * 0.53;
			
			double productPrice = order.getWeight() * 55;
			
			order.setCargoPrice(cargoPrice);
			order.setProductPrice(productPrice);
			
		} 
				
	}
	
	public int getRandomDeliveryDistance(int lowerBound, int upperBound) {
		Random rnd = new Random();
		int deliveryDistance = rnd.nextInt(upperBound - lowerBound) + lowerBound;
		
		return deliveryDistance;
	}

	@Override
	public String toString() {
		return "Placed State";
	}

}
