package domain;

import java.util.Random;

public class SavedOrder implements OrderState{

	
	@Override
	public void prepareOrder(Order order, Customer customerOfOrder) {
		
		System.out.println("Order is in saved state...");
		
		int orderId = getUniqueIdForOrder(customerOfOrder);
		int orderTrackingNumber = getTrackingNumberForOrder();
		int customerId = customerOfOrder.getId();
		String customerName = customerOfOrder.getName();
		
		order.setId(orderId);
		order.setTrackingNumber(orderTrackingNumber);
		order.setCustomerId(customerId);
		order.setCustomerName(customerName);
		
	}
	
	public int getUniqueIdForOrder(Customer customerOfOrder) {
		int id = 0;
		for(Order order: customerOfOrder.getOrders()) {
			if(order.getId() == id) {
				id++;
			}
		}
		return id;	
	}
	
	public int getTrackingNumberForOrder() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    return Integer.parseInt(String.format("%06d", number));
	}

	@Override
	public String toString() {
		return "Saved State";
	}
	
	
	
}
