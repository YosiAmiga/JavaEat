package Model;

import java.io.Serializable;
import java.time.LocalDate;

import Utils.Gender;

public abstract class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private LocalDate birthDay;
	private Gender gender;
	
	private String password;
	// -------------------------------Constructors------------------------------
    /**
     * Partial constructor using only id.
     * @param id The id of the person.
     * @param password - the full name of the given customer -> first name + last name, combined
     */
	public Person(int id, String password) {
		this.id=id;
		this.password=password;
	}
	
	public Person(int id, String firstName, String lastName, LocalDate birthDay, Gender gender,String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = birthDay;
		this.gender = gender;
		this.password= password;
	}
	
	/**
     * Full constructor.
     *
     * @param id The ID of the person.
     * @param firstName First name of the person.
     * @param lastName is the Last name of the person.
     * @param birthDay The birth date of the person.
     * @param gender The person's gender.
     */
	public Person(int id, String firstName, String lastName, LocalDate birthDay, Gender gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = birthDay;
		this.gender = gender;
	}
	
	public Person(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	

	
	
	
	
	
}
