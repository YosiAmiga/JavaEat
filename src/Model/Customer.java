package Model;

import java.time.LocalDate;

import Utils.Gender;
import Utils.Neighberhood;

public class Customer extends Person{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private Neighberhood neighberhood;
	private boolean isSensitiveToLactose;
	private boolean isSensitiveToGluten;
	/*constructor with id and password*/
	public Customer(int id, String firstName, String lastName, LocalDate birthDay, Gender gender,String password,
			Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten) {
		super(id, firstName, lastName, birthDay, gender,password);
		this.neighberhood = neighberhood;
		this.isSensitiveToLactose = isSensitiveToLactose;
		this.isSensitiveToGluten = isSensitiveToGluten;
	}
	
	/*constructor with password*/
	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,String password,
			Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten) {
		super(idCounter++, firstName, lastName, birthDay, gender,password);
		this.neighberhood = neighberhood;
		this.isSensitiveToLactose = isSensitiveToLactose;
		this.isSensitiveToGluten = isSensitiveToGluten;
	}

	/*regular constructor*/
	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,
			Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten) {
		super(idCounter++, firstName, lastName, birthDay, gender);
		this.neighberhood = neighberhood;
		this.isSensitiveToLactose = isSensitiveToLactose;
		this.isSensitiveToGluten = isSensitiveToGluten;
	}
	
	/*Constructor for a customer user*/
	public Customer(int id, String password) {
		super(id,password);
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Customer.idCounter = idCounter;
	}

	public Neighberhood getNeighberhood() {
		return neighberhood;
	}

	public void setNeighberhood(Neighberhood neighberhood) {
		this.neighberhood = neighberhood;
	}

	public boolean isSensitiveToLactose() {
		return isSensitiveToLactose;
	}

	public void setSensitiveToLactose(boolean isSensitiveToLactose) {
		this.isSensitiveToLactose = isSensitiveToLactose;
	}

	public boolean isSensitiveToGluten() {
		return isSensitiveToGluten;
	}

	public void setSensitiveToGluten(boolean isSensitiveToGluten) {
		this.isSensitiveToGluten = isSensitiveToGluten;
	}

	@Override
	public String toString() {
		return "Customer [neighberhood=" + neighberhood + ", isSensitiveToLactose=" + isSensitiveToLactose
				+ ", isSensitiveToGluten=" + isSensitiveToGluten + ", getId()=" + getId() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getBirthDay()=" + getBirthDay()
				+ ", getGender()=" + getGender() + ", getPassword()=" + getPassword() + "]";
	}

//	@Override
//	public String toString() {
//		return super.toString()+" Customer [isSensitiveToLactose=" + isSensitiveToLactose + ", isSensitiveToGluten=" + isSensitiveToGluten
//				+ "]";
//	}
	
	
}
