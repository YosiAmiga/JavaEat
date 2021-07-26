package Exceptions;

import java.util.Arrays;

public class IllegalCustomerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalCustomerException() {
		super("The restaurant is in conflict with this customer!");
	}

	@Override
	public String toString() {
		return "The restaurant is in conflict with this customer!";
	}

	
}
