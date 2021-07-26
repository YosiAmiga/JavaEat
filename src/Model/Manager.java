package Model;

public class Manager extends Person{
	public Manager(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Manager manager;
	
	/*TODO add constructor to Person*/
	private Manager(int id, String password)
	{
		super(id, password); 
	} 
	
	public static Manager getInstance() {
		if(manager == null)
			manager = new Manager(0, "admin");
		return manager;
	}
}

