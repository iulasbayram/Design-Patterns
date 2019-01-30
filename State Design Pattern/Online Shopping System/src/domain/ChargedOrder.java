package domain;

public class ChargedOrder implements OrderState {

	@Override
	public void prepareOrder(Order order, Customer customerOfOrder) {
		
		System.out.println("Order is in charged state...");
		
		double totalPrice = order.getCargoPrice() + order.getProductPrice();
		order.setTotalPrice(totalPrice);
		
		
	}

	@Override
	public String toString() {
		return "Charged State";
	}
	
}
