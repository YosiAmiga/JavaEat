package Exceptions;

import Model.Dish;

public class NoComponentsExceptions extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoComponentsExceptions() {
		super("The dish does not include components and need to be removed from the menu!");
		
	}

	
}
