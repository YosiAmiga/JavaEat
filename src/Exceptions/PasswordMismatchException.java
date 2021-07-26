package Exceptions;

public class PasswordMismatchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PasswordMismatchException() {
		super("The password in the relevent fields dont match");
	}
	@Override
	public String toString() {
		return "The password needs to match verification";
	}
	
}