package Exceptions;

public class UpdateDeliveredDeliveryException extends Exception{

	@Override
	public String toString() {
		return "Can not updated a delivery that has been delivered!";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UpdateDeliveredDeliveryException() {
		super("Can not updated a delivery that has been delivered!");
	}
}