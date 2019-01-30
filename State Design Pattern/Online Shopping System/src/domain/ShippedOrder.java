package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ShippedOrder implements OrderState {

	@Override
	public void prepareOrder(Order order, Customer customerOfOrder) {
		
		System.out.println("Order is in shipped state...");
		
		int plannedShippingDuration = getRandomDayNumber(1, 10);
		int realShippingDuration = getRandomDayNumber(1, 10);
		
		if( (realShippingDuration - plannedShippingDuration) > 7) {
			System.out.println("Error: Not shipped.");
			
			OrderState newOrderState = new ChargedOrder();
			newOrderState.prepareOrder(order, customerOfOrder);
			order.setState(newOrderState);		
			
		} else {
			Date dateShipped = new Date();			
			order.setDateShipped(dateShipped);	
		}
				
	}

	public int getRandomDayNumber(int lowerBound, int upperBound) {
		Random rnd = new Random();
		int randomDayNumber = rnd.nextInt(upperBound - lowerBound) + lowerBound;
			
		return randomDayNumber;
	}
	
	@Override
	public String toString() {
		return "Shipped State";
	}
	
}
