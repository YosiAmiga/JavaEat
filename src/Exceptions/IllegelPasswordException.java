package Exceptions;

public class IllegelPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IllegelPasswordException() {
		super("Password is illegel!");
	}
	@Override
	public String toString() {
		return "Password is illegel! no input";
	}

}