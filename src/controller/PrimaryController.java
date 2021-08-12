package controller;

import  Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import Utils.*;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;

import Exceptions.IllegelInputException;
import Exceptions.IllegelPasswordException;
import Exceptions.PasswordMismatchException;
import javafx.collections.ObservableList;



public class PrimaryController {
	
	/**************************************Customer Page*****************************************/
	

	/**
	 * This method is used to remove a customer from the database.
	 * @param id - the id of the customer.
	 * @return true if success, false if failed.
	 * @throws IllegelInputException 
	 */
	public boolean removeCustomerGUI(int id) throws IllegelInputException {
		boolean validate = requireNotZeroOrNegative(id);
		if(!validate) {
			throw new IllegelInputException();			
		}
		Customer customerDel = Restaurant.getInstance().getRealCustomer(id);		
		return Restaurant.getInstance().removeCustomer(customerDel);
	}
	

	
	
	/**
	 * This method is used to add a new customer to the system.
	 *
	 * 1) validate parameters.
	 * 2) check if customer does not exist already.
	 * 3) create customer object.
	 * 4) add customer to the system.
	 *
	 * @param id The id of the customer.
	 * @param firstName The first name of the customer.
	 * @param LastName The LastName of the customer.
	 * @param birthday The customer's birthday.
	 * @param gender The gender of the customer.
	 * @param password the password of the customer.
	 * @param verifyPass another password to verify the password entered by the user.
	 * @param hood The neighborhood at which the customer lives.
	 * @param lactose is the customer allergic to lactose.
	 * @param gluten is the customer allergic to gluten.
	 * @return true if successfully added.
	 */
	public boolean addCustomerFromGUI(int id, String firstName, String LastName, LocalDate birthday,Gender gender,String password,String verifyPass, Neighberhood hood, boolean lactose, boolean gluten) throws Exception{

		//check for parameters
		boolean validate = (require(firstName,LastName,birthday,gender,password,hood,lactose,gluten)) && (requireNotZeroOrNegative(id));

		if(!validate) {
			throw new IllegelInputException();			
		}
//		if(id < 0 || houseNumber < 0)
//			throw new NegativeNumberNotPriceException();
		if(!password.equals(verifyPass)) {
			throw new PasswordMismatchException();			
		}
		if(password.isBlank() || verifyPass.isBlank()) {
			throw new IllegelPasswordException();
		}
		//check if doesn't exist already

		//create customer object

		Customer customer = new Customer(id,firstName,LastName,birthday,gender,password,hood,lactose,gluten);
		System.out.println(customer);
		// add the customer to the user confirmation system

		//add
		return Restaurant.getInstance().addCustomer(customer);
	}
	
	/**************************************Delivery Person Page****************************************/
	
	/**
	 * a method to remove a delivery person from the database
	 * @param id
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean removeDeliveryPersonGUI(int id) throws IllegelInputException {
		boolean validate = requireNotZeroOrNegative(id);
		if(!validate) {
			throw new IllegelInputException();
		}
		DeliveryPerson dp = Restaurant.getInstance().getRealDeliveryPerson(id);
		return Restaurant.getInstance().removeDeliveryPerson(dp);
	}

	/**
	 * a method to add a delivery person to the database
	 * @param id - the id of the delivery person
	 * @param fName - first name of the delivery person
	 * @param lName - last name of the delivery person
	 * @param bDay - birthday of the delivery person
	 * @param g - gender of the the delivery person
	 * @param v - vehicle of the delivery person
	 * @param da - delivery area of the delivery person
	 * @return true if added successfully, false if not
	 * @throws IllegelInputException
	 */
	public boolean addDeliveryPersonGUI(int id,String fName, String lName, LocalDate bDay, Gender g, Vehicle v ,String da) throws IllegelInputException {
//		public DeliveryPerson(int id,String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,
//		DeliveryArea area)
		
		boolean validate = (require(id, fName, lName, bDay, g, v,da) && requireNotZeroOrNegative(id));
		if(!validate) {
			throw new IllegelInputException();
		}
		//TODO ADD DELIVERY AREA!!!
		DeliveryArea tempArea = null;
		int areaID = 0;
		for(DeliveryArea d : Restaurant.getInstance().getAreas().values()) {
			if(d.getAreaName().equals(da)) {
				areaID = d.getId();
			}
		}
		tempArea = Restaurant.getInstance().getRealDeliveryArea(areaID);
		DeliveryPerson delPerson = new DeliveryPerson(id,fName,lName,bDay,g,v,tempArea);
		return Restaurant.getInstance().addDeliveryPerson(delPerson, tempArea);
		
	}
	
	/**************************************Cook Page*****************************************/
	/**
	 * This method is used to remove a cook from the database.
	 * @param id - the id of the cook.
	 * @return true if success, false if failed.
	 * @throws IllegelInputException 
	 */
	public boolean removeCookFromGUI(int id) throws IllegelInputException {
		boolean validate = requireNotZeroOrNegative(id);
		if(!validate) {
			throw new IllegelInputException();			
		}
		Cook cookDelete = Restaurant.getInstance().getRealCook(id);		
		return Restaurant.getInstance().removeCook(cookDelete);
	}
	
	/**
	 * a method to add a cook to the database
	 * @param id - the id of the cook
	 * @param firstName - first name of the cook
	 * @param LastName - last name of the cook
	 * @param birthday - birth date of the cook
	 * @param gender - gender of the cook
	 * @param expert - expertise of the cook
	 * @param isChef - is the cook a chef
	 * @return  true if success, false if failed.
	 * @throws Exception
	 */
	public boolean addCookFromGUI(int id, String firstName, String LastName, LocalDate birthday,Gender gender, Expertise expert,boolean isChef) throws Exception{

		//check for parameters of class cook and validate id
		boolean validate = (require(id,firstName,LastName,birthday,gender,expert,isChef)) && (requireNotZeroOrNegative(id));

		// if not valid throw exception
		if(!validate) {
			throw new IllegelInputException();			
		}

		// create a new cook instance with the data we got from GUI
		Cook cook = new Cook(id,firstName,LastName,birthday,gender,expert,isChef);
		return Restaurant.getInstance().addCook(cook);
	}
	
	/**************************************Component Page****************************************/
	
	/**
	 * a method to update the data of an existing component
	 * @param id
	 * @param cName
	 * @param lactose
	 * @param gluten
	 * @param price
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean updateComponentGUI(int id, String cName, boolean lactose, boolean gluten, double price, int newID) throws IllegelInputException {
		boolean validate = (requireNotZeroOrNegative(id) && requireNotZeroOrNegative(newID));
		if(!validate) {
			throw new IllegelInputException();
		}
		//get the current component by its id from the database
		Component componentUpdate = Restaurant.getInstance().getRealComponent(id);
		//check each non empty parameter to update
		//if new id is different AND does not exist in the database for a different component, update it
		if(newID != componentUpdate.getId() && Restaurant.getInstance().getComponenets().containsKey(newID) && require(newID)) {
			componentUpdate.setId(newID);
		}
		//if new component name is different, update it
		if(cName != componentUpdate.getComponentName() && require(cName)) {
			componentUpdate.setComponentName(cName);
		}
		//if new has / not has lactose is different, update it
		if(lactose != componentUpdate.isHasLactose() && require(lactose)) {
			componentUpdate.setHasLactose(lactose);
		}
		//if new has / not has gluten is different, update it
		if(gluten != componentUpdate.isHasGluten() && require(gluten)) {
			componentUpdate.setHasGluten(gluten);
		}
		//if new component price is different, update it
		if(price != componentUpdate.getPrice() && require(price)) {
			componentUpdate.setPrice(price);
		}
		
		//after all changes were checked, update the component in the database
		return Restaurant.getInstance().getComponenets().put(componentUpdate.getId(), componentUpdate) == null;
	}
	
	
	/**
	 * a method to remove a component from the database.
	 * @param id - id of the component.
	 * @return true if success, false if failed.
	 */
	public boolean removeComponentGUI(int id) throws IllegelInputException{
		boolean validate = requireNotZeroOrNegative(id);
		if(!validate) {
			throw new IllegelInputException();
		}
		Component componentdDel = Restaurant.getInstance().getRealComponent(id);
		return Restaurant.getInstance().removeComponent(componentdDel);
	}

	/**
	 * a method to add a component to the database
	 * @param id - id of the component
	 * @param cName - name of the component
	 * @param lactose - does it contains lactose
	 * @param gluten - does it contains gluten
	 * @param price - price of the component
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean addComponentGUI(int id, String cName, boolean lactose, boolean gluten, double price) throws IllegelInputException {
		boolean validate = (require(id,cName,lactose,gluten,price) && requireNotZeroOrNegative(id) && requireNotZeroOrNegative(price));
		if(!validate) {
			throw new IllegelInputException();
		}
		Component compToAdd = new Component(id,cName,lactose,gluten,price);
		
		return Restaurant.getInstance().addComponent(compToAdd);
	}

	/**************************************Dish Page*****************************************/

	/**
	 * a method to remove a dish from the database
	 * @param id
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean removeDishFromGUI(int id) throws IllegelInputException{
		boolean validate = requireNotZeroOrNegative(id);
		if(!validate) {
			throw new IllegelInputException();
		}
		Dish dishDelete = Restaurant.getInstance().getRealDish(id);		
		return Restaurant.getInstance().removeDish(dishDelete);
	}
	
	/**
	 * a method to add a dish to the database
	 * @param id - the id of the dish
	 * @param dName - the name of the dish
	 * @param type - the type of the dish
	 * @param componentsInDishList - the components of the dish
	 * @param toMake - time to make the dish
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean addDishFromGUI(int id, String dName,DishType type, ArrayList<Integer> componentsInDishList, int toMake) throws IllegelInputException {
		boolean validate = (require(id, dName,type) && requireNotZeroOrNegative(id) && requireNotZeroOrNegative(toMake));
		if(!validate) {
			throw new IllegelInputException();
		}

		ArrayList<Component> components = new ArrayList<>();
		for(int compID : componentsInDishList) {
			//if the id of the component in componentsInDishList from user exist in system, add the component to the dish
			if(Restaurant.getInstance().getComponenets().containsKey(compID)){
				//get the component data by his id
				Component temp = Restaurant.getInstance().getRealComponent(compID);
				System.out.println(temp);
				//add it to the ArrayList of components in the dish
				components.add(temp);
			}
		}
				
		Dish newDish = new Dish(id,dName,type,components,toMake);	
		System.out.println(newDish);
		return Restaurant.getInstance().addDish(newDish);
	}
	
	
	/**************************************Order Page*****************************************/

	
	/**
	 * a method to add an order to the database
	 * @param id - the id of the order
	 * @param custForOrder - the id of the customer in the order
	 * @param dishesInOrderList - the dishes in the order
	 * @param DeliveriesInOrderList - the delivery in the order
	 * @return true if success, false if failed.
	 * @throws Exception
	 */
	public boolean addOrderFromGUI(int id,int custForOrder, ArrayList<String> dishesInOrderList,String DeliveriesInOrderList) throws Exception{

		//check for parameters of class order and validate id
		boolean validate = (require(id,custForOrder,dishesInOrderList)) && (requireNotZeroOrNegative(id));

		// if not valid throw exception
		if(!validate) {
			throw new IllegelInputException();			
		}

		// create a new customer instance with the data we got from GUI		
		Customer c= Restaurant.getInstance().getRealCustomer(custForOrder);
		
		ArrayList<Dish> AlDishes=new ArrayList<Dish>();
			
		for(Dish d : Restaurant.getInstance().getDishes().values()) {
			for(String s : dishesInOrderList) {
				if(d.getDishName().equals(s)) {
					AlDishes.add(d);
				}
			}		
		}
		
		Delivery del=null;		
		Order order = new Order(id,c,AlDishes,del);
		
		return Restaurant.getInstance().addOrder(order);
	}
	
	public boolean removeOrderFromGUI(int id) throws IllegelInputException{
		boolean validate = requireNotZeroOrNegative(id);
		if(!validate) {
			throw new IllegelInputException();
		}
		Order orderDelete = Restaurant.getInstance().getRealOrder(id);		
		return Restaurant.getInstance().removeOrder(orderDelete);
	}
	
	/************************************Delivery Page*******************************************/
	
	public boolean addRegularDeliveryFromGUI(int id, int dpID, String dArea, boolean isDelivered, LocalDate diliveredDate, ArrayList<Integer> orders) throws Exception{

		//check for parameters of class cook and validate id
		boolean validate = (require(id,dpID,dArea,isDelivered,diliveredDate,orders) && (requireNotZeroOrNegative(id,dpID)));

		// if not valid throw exception
		if(!validate) {
			throw new IllegelInputException();			
		}
		
				
		//search for the given delivery person
		DeliveryPerson delPer = null;
		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
			if(dp.getId() == dpID) {
				delPer = dp;
			}
		}
		//search for the given delivery area
		DeliveryArea tempArea = null;
		int areaID = 0;
		for(DeliveryArea d : Restaurant.getInstance().getAreas().values()) {
			if(d.getAreaName().equals(dArea)) {
				areaID = d.getId();
			}
		}
		tempArea = Restaurant.getInstance().getRealDeliveryArea(areaID);

		/*NO NEED -> the order gets its delivery from the add delivery method in Restaurant
		 * so if its an RegularDelivery -> no need to create the set*/
		TreeSet<Order> o = new TreeSet<>();

		for(int temp : orders) {
			for(Order ordersss : Restaurant.getInstance().getOrders().values()) {
				if(temp == ordersss.getId()) {
					o.add(ordersss);
				}
			}
		}
		RegularDelivery deliveryToAdd = new RegularDelivery(id,delPer,tempArea,isDelivered,diliveredDate);			
		for(Order or : o) {
			deliveryToAdd.addOrder(or);
		}
		System.out.println(deliveryToAdd.getOrders());		
		return Restaurant.getInstance().addDelivery(deliveryToAdd);
		
	}
	
	public boolean addDeliveryFromGUI(int id, int dpID, String dArea, boolean isDelivered, LocalDate diliveredDate, boolean isEXP, double postage, ArrayList<Integer> orders) throws Exception{

		//check for parameters of class cook and validate id
		boolean validate = (require(id,dpID,dArea,isDelivered,diliveredDate,orders) && (requireNotZeroOrNegative(id)));

		// if not valid throw exception
		if(!validate) {
			throw new IllegelInputException();			
		}
		
		
		boolean isDel= isDelivered;
		LocalDate delDate= diliveredDate;
		
		//search for the given delivery person
		DeliveryPerson delPer = null;
		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
			if(dp.getId() == dpID) {
				delPer = dp;
			}
		}
		//search for the given delivery area
		DeliveryArea tempArea = null;
		int areaID = 0;
		for(DeliveryArea d : Restaurant.getInstance().getAreas().values()) {
			if(d.getAreaName().equals(dArea)) {
				areaID = d.getId();
			}
		}
		tempArea = Restaurant.getInstance().getRealDeliveryArea(areaID);

//		public Delivery(int id, DeliveryPerson deliveryPerson, DeliveryArea area,
//				boolean isDelivered,LocalDate diliveredDate) 
		
//		//constructors for GUI
//		
//		public ExpressDelivery(int id, DeliveryPerson deliveryPerson, DeliveryArea area,
//				boolean isDelivered , Order order , double postage, LocalDate deliveredDate) {
//			super(id, deliveryPerson, area, isDelivered, deliveredDate);
//			this.order = order;
//			this.postage = postage;
//		}
//		/*if no new postage was enterd*/
//		public ExpressDelivery(int id, DeliveryPerson deliveryPerson, DeliveryArea area,
//				boolean isDelivered , Order order , LocalDate deliveredDate) {
//			super(id, deliveryPerson, area, isDelivered, deliveredDate);
//			this.order = order;
//			this.postage = 5.0;
//		}
		
		
//		public RegularDelivery(int id, DeliveryPerson deliveryPerson, DeliveryArea area,
//				boolean isDelivered,LocalDate deliveredDate) {
//			super(id, deliveryPerson, area, isDelivered, deliveredDate);
//			this.orders = new TreeSet<>();
//		}
		
		//if it is an express delivery like the user entered AND the orders size is 1 
		/*isEXP is necessary because if we want to change the postage we can't do it just by putting only 1 order,
		 * this helps us check if it's really an ExpressDelivery or not
		 * 
		 * TODO create an exception where one is true and the other is not -> expressDeliveryMissMatchException*/
		
//		if((isEXP== true && orders.size()!=1) || (isEXP== false && orders.size()==1)) {
//			throw new expressDeliveryMissMatchException();
//		}
		
		if(isEXP && orders.size()==1) {
			//if a new postage was entered, use it
			if(postage != 5.0) {
				Order o = null;
				for(Order temp : Restaurant.getInstance().getOrders().values()) {
					if(temp.getId() == orders.get(0)) {
						o = temp;
					}
				}
				Delivery deliveryToAdd = new ExpressDelivery(id,delPer,tempArea,isDelivered,o,postage,diliveredDate);
				return Restaurant.getInstance().addDelivery(deliveryToAdd);
				
			}
			//if no new postage, the default is 5.0
			else {
				Order o = null;
				for(Order temp : Restaurant.getInstance().getOrders().values()) {
					if(temp.getId() == orders.get(0)) {
						o = temp;
					}
				}
				Delivery deliveryToAdd = new ExpressDelivery(id,delPer,tempArea,isDelivered,o,diliveredDate);
				return Restaurant.getInstance().addDelivery(deliveryToAdd);

			}
			
		}
		//if it is not an express, then its a RegularDelivery 
		else {
			
			/*NO NEED -> the order gets its delivery from the add delivery method in Restaurant
			 * so if its an RegularDelivery -> no need to create the set*/
			TreeSet<Order> o = new TreeSet<>();

			for(int temp : orders) {
				for(Order ordersss : Restaurant.getInstance().getOrders().values()) {
					if(temp == ordersss.getId()) {
						o.add(ordersss);
					}
				}
			}
			RegularDelivery deliveryToAdd = new RegularDelivery(id,delPer,tempArea,isDelivered,diliveredDate);			
			for(Order or : o) {
				deliveryToAdd.addOrder(or);
			}
			
			return Restaurant.getInstance().addDelivery(deliveryToAdd);
		}
	}
	

	
	/**************************************Delivery Area Page****************************************/

	
	/**
	 * a method to replace a given delivery area with a new one, both of them has to exist in the system!
	 * @param oldAreaID
	 * @param newAreaID
	 * @return true if added successfully, false if not
	 * @throws IllegelInputException
	 */
	public boolean replaceDeliveryAreaGUI(int oldAreaID,int newAreaID) throws IllegelInputException {
		boolean validate = (requireNotZeroOrNegative(oldAreaID) && requireNotZeroOrNegative(newAreaID));
		if(!validate) {
			throw new IllegelInputException();
		}

		DeliveryArea newArea = Restaurant.getInstance().getRealDeliveryArea(newAreaID);
		DeliveryArea oldArea = Restaurant.getInstance().getRealDeliveryArea(oldAreaID);
		System.out.println(oldArea);
		return Restaurant.getInstance().removeDeliveryArea(oldArea, newArea);
	}
	
	/**
	 * a method to add add a delivery area from GUI
	 * @param id - the id of the delivery area
	 * @param aName - the name of the delivery area
	 * @param hoods - the neighborhoods in the delivery area
	 * @param deliveryTime - the delivery time of the delivery area
	 * @return true if added successfully, false if not
	 * @throws IllegelInputException 
	 */
	public boolean addDeliveryAreaGUI(int id,String aName,ArrayList<String> hoods, int deliveryTime) throws IllegelInputException {
		//create a HashSet of neighborhoods to set in the delivery area, convert the ArrayList of strings into HashSet of Neighberhood
		boolean validate = (require(id, aName,deliveryTime) && requireNotZeroOrNegative(id));
		if(!validate) {
			throw new IllegelInputException();
		}
		HashSet<Neighberhood> aHoods = new HashSet<>();
		for(String h : hoods) {
			for(Neighberhood n : Neighberhood.values()) {
				if(n.toString().equals(h)) {
					aHoods.add(n);
				}
				
			}
		}

		DeliveryArea da = new DeliveryArea(id,aName,aHoods,deliveryTime);
		return Restaurant.getInstance().addDeliveryArea(da);	
	}

	/*************************************Blacklist Page*************************************/


	public boolean addToBlacklistFromGUI(int id) throws IllegelInputException{
		boolean validate = requireNotZeroOrNegative(id);
		if(!validate) {
			throw new IllegelInputException();
		}
		Customer customerToBlacklist = Restaurant.getInstance().getRealCustomer(id);		
		return Restaurant.getInstance().addCustomerToBlackList(customerToBlacklist);
	}

	
	

	

/******************************************/
	
	


	/**
	 * a method to check if all the field are not empty
	 * @param values
	 * @return
	 */
	public static boolean require(Object... values){
		
		for (Object value : values)
			if(value == null) {
				return false;				
			}

		return true;
	}

	/**
	 * a method to check if a field is smaller or equal to zero
	 * @param numbers
	 * @return
	 */
	public static boolean requireNotZeroOrNegative(Number... numbers){
		for (Number number : numbers) {
			if(number.equals(0) || number.intValue() < 0) {
				return false;
				
			}
		}
		return true;
	}











}
