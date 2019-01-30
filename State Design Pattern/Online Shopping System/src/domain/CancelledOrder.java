package domain;

public class CancelledOrder implements OrderState {

	@Override
	public void prepareOrder(Order cancelledOrder, Customer customerOfOrder) {

		System.out.println("Order is in calcelled state...");
		
		customerOfOrder.getOrders().remove(cancelledOrder);
		
	}

}
