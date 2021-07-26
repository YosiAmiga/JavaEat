package Exceptions;

public class SimilarIDInSystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimilarIDInSystemException() {
		super("The database already contains this id!");
	}

	@Override
	public String toString() {
		return "The database already contains this id!";
	}

	
}