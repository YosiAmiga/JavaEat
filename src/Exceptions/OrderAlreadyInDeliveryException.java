package Exceptions;

public class OrderAlreadyInDeliveryException extends Exception{

	@Override
	public String toString() {
		return "This order is already in a delivery and can not be deleted!";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OrderAlreadyInDeliveryException() {
		super("This order is already in a delivery and can not be deleted!");
	}
}
