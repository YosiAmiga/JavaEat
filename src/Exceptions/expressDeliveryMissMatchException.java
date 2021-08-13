package Exceptions;

public class expressDeliveryMissMatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public expressDeliveryMissMatchException() {
		super("It can't be an express delivery with more than 1 order!");
	}

	@Override
	public String toString() {
		return "It can't be an express delivery with more than 1 order!";
	}

}
