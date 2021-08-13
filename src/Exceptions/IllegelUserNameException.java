package Exceptions;

public class IllegelUserNameException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IllegelUserNameException() {
		super("Invalid Customer ID!");
	}
	@Override
	public String toString() {
		return "Invalid Customer ID!";
	}
	
}
