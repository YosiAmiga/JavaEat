package Exceptions;

public class IncorrectPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IncorrectPasswordException() {
		super("Password is incorrect!");
	}
	@Override
	public String toString() {
		return "Password is incorrect! try again";
	}

}