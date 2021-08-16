package Exceptions;

public class EmptyComboBoxException extends Exception{

	@Override
	public String toString() {
		return "Please select a value from the combo box!";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmptyComboBoxException() {
		super("Please select a value from the combo box!");
	}
}