package Exceptions;

public class EmptyTextFieldException  extends Exception{

	@Override
	public String toString() {
		return "Empty text field!";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmptyTextFieldException() {
		super("Empty text field!");
	}
}