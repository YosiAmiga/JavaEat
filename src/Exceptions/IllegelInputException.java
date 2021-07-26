package Exceptions;

public class IllegelInputException extends Exception{

	@Override
	public String toString() {
		return "Wrong Input";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IllegelInputException() {
		super("Wrong Input");
	}
}