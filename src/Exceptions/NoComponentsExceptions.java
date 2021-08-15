package Exceptions;

import Model.Dish;

public class NoComponentsExceptions extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoComponentsExceptions(Dish dish) {
		super("The dish "+ dish + " does not include components and is removed from the menu!");
		
	}

	
}
