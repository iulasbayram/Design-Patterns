package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DeliveredOrder implements OrderState {

	@Override
	public void prepareOrder(Order order, Customer customerOfOrder) {
		
		System.out.println("Order is in delivered state...");
		
		int plannedDeliveryDuration = getRandomDayNumber(3, 12);
		int realDeliveryDuration = getRandomDayNumber(3, 12);
		
		if( (realDeliveryDuration - plannedDeliveryDuration) > 8) {
			System.out.println("Error:Lost in delivering");
			
			OrderState newOrderState = new ChargedOrder();
			newOrderState.prepareOrder(order, customerOfOrder);
			order.setState(newOrderState);	
			
		} else {
			Date dateDelivered = new Date();			
			order.setDateDelivered(dateDelivered);	
		}
				
	}
	
	public int getRandomDayNumber(int lowerBound, int upperBound) {
		Random rnd = new Random();
		int randomDayNumber = rnd.nextInt(upperBound - lowerBound) + lowerBound;
			
		return randomDayNumber;
	}

	@Override
	public String toString() {
		return "Delivered State";
	}
	
}
