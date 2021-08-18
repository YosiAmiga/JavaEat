package controller;

import  Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import Utils.*;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;

import Exceptions.IllegalCustomerException;
import Exceptions.IllegelInputException;
import Exceptions.IllegelPasswordException;
import Exceptions.PasswordMismatchException;
import Exceptions.SensitiveException;
import Exceptions.SimilarIDInSystemException;
import Exceptions.expressDeliveryMissMatchException;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;



public class PrimaryController {
	
	/**************************************Customer Page*****************************************/
	/**
	 * a method to update the data of an existing Customer
	 * @param id
	 * @param cName
	 * @param lactose
	 * @param gluten
	 * @param price
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean updateCustomerGUI(int id, String firstName, String LastName, LocalDate birthday,Gender gender,String password,String verifyPass, Neighberhood hood, boolean lactose, boolean gluten) throws IllegelInputException {
		boolean validate = (requireNotZeroOrNegative(id));
		if(!validate) {
			throw new IllegelInputException();
		}
		//get the current Customer by its id from the database
		Customer customerUpdate = Restaurant.getInstance().getRealCustomer(id);
		//check each non empty parameter to update

		//if new Customer name is different, update it
		if(firstName != customerUpdate.getFirstName() && require(firstName)) {
			customerUpdate.setFirstName(firstName);
		}
		if(LastName != customerUpdate.getFirstName() && require(LastName)) {
			customerUpdate.setLastName(LastName);;
		}

		if(birthday != customerUpdate.getBirthDay() && require(birthday)) {
			customerUpdate.setBirthDay(birthday);;
		}

		if(gender != customerUpdate.getGender() && require(gender)) {
			customerUpdate.setGender(gender);;
		}

		if(password != customerUpdate.getPassword() && require(password)) {
			customerUpdate.setPassword(password);
		}

		if(hood != customerUpdate.getNeighberhood() && require(hood)) {
			customerUpdate.setNeighberhood(hood);;
		}

		if(lactose != customerUpdate.isSensitiveToLactose() && require(lactose)) {
			customerUpdate.setSensitiveToLactose(lactose);;
		}
		if(gluten != customerUpdate.isSensitiveToGluten() && require(gluten)) {
			customerUpdate.setSensitiveToGluten(gluten);;
		}
		
		//after all changes were checked, update the component in the database
		return Restaurant.getInstance().getCustomers().put(customerUpdate.getId(), customerUpdate) != null;
	}

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
	 * a method to add a new customer to the database.
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
		
		if(Restaurant.getInstance().getCustomers().containsKey(id)) {
			throw new SimilarIDInSystemException();
		}

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
	 * a method to update the data of an existing delivery person
	 * @param id
	 * @param cName
	 * @param lactose
	 * @param gluten
	 * @param price
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean updateDeliveryPersonGUI(int id,String fName, String lName, LocalDate bDay, Gender g, Vehicle v ,String da) throws IllegelInputException {
		boolean validate = (requireNotZeroOrNegative(id));
		if(!validate) {
			throw new IllegelInputException();
		}
		DeliveryArea tempArea = null;
		int areaID = 0;
		for(DeliveryArea d : Restaurant.getInstance().getAreas().values()) {
			if(d.getAreaName().equals(da)) {
				areaID = d.getId();
			}
		}
		tempArea = Restaurant.getInstance().getRealDeliveryArea(areaID);
		//get the current Customer by its id from the database
		DeliveryPerson delPUpdate = Restaurant.getInstance().getRealDeliveryPerson(id);
		//check each non empty parameter to update

		//if new Customer name is different, update it
		if(fName != delPUpdate.getFirstName() && require(fName)) {
			delPUpdate.setFirstName(fName);
		}
		if(lName != delPUpdate.getFirstName() && require(lName)) {
			delPUpdate.setLastName(lName);;
		}

		if(bDay != delPUpdate.getBirthDay() && require(bDay)) {
			delPUpdate.setBirthDay(bDay);;
		}

		if(g != delPUpdate.getGender() && require(g)) {
			delPUpdate.setGender(g);;
		}


		if(v != delPUpdate.getVehicle() && require(v)) {
			delPUpdate.setVehicle(v);;
		}

		if(tempArea != delPUpdate.getArea() && require(tempArea)) {
			delPUpdate.setArea(tempArea);;
		}
		tempArea = Restaurant.getInstance().getRealDeliveryArea(areaID);

		
		//after all changes were checked, update the component in the database
		return Restaurant.getInstance().getDeliveryPersons().put(delPUpdate.getId(), delPUpdate) != null;
	}

	
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
	 * a method to update the data of an existing Cook
	 * @param id
	 * @param cName
	 * @param lactose
	 * @param gluten
	 * @param price
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean updateCookGUI(int id, String firstName, String LastName, LocalDate birthday,Gender gender, Expertise expert,boolean isChef) throws IllegelInputException {
		boolean validate = (requireNotZeroOrNegative(id));
		if(!validate) {
			throw new IllegelInputException();
		}
		
		//get the current cook by its id from the database
		Cook cookUpdate =Restaurant.getInstance().getRealCook(id);
		
		//check each non empty parameter to update

		//if new Customer name is different, update it
		if(firstName != cookUpdate.getFirstName() && require(firstName)) {
			cookUpdate.setFirstName(firstName);
		}
		if(LastName != cookUpdate.getFirstName() && require(LastName)) {
			cookUpdate.setLastName(LastName);;
		}

		if(birthday != cookUpdate.getBirthDay() && require(birthday)) {
			cookUpdate.setBirthDay(birthday);;
		}

		if(gender != cookUpdate.getGender() && require(gender)) {
			cookUpdate.setGender(gender);;
		}


		if(expert != cookUpdate.getExpert() && require(expert)) {
			cookUpdate.setExpert(expert);
		}

		if(isChef != cookUpdate.isChef() && require(isChef)) {
			cookUpdate.setChef(isChef);;
		}

		
		//after all changes were checked, update the Cook in the database
		return Restaurant.getInstance().getCooks().put(cookUpdate.getId(), cookUpdate) != null;
	}
	
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
		return Restaurant.getInstance().getComponenets().put(componentUpdate.getId(), componentUpdate) != null;
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
	 * a method to update the data of an existing Dish
	 * @param id
	 * @param cName
	 * @param lactose
	 * @param gluten
	 * @param price
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean updateDishGUI(int id, String dName,DishType type, ArrayList<Integer> componentsInDishList, int toMake) throws IllegelInputException {
		boolean validate = (requireNotZeroOrNegative(id));
		if(!validate) {
			throw new IllegelInputException();
		}
		
		//get the current Dish by its id from the database
		Dish dishUpdate = Restaurant.getInstance().getRealDish(id);
		//check each non empty parameter to update

		//if new Customer name is different, update it
		if(dName != dishUpdate.getDishName() && require(dName)) {
			dishUpdate.setDishName(dName);
		}
		if(type != dishUpdate.getType() && require(type)) {
			dishUpdate.setType(type);;
		}

		if(toMake != dishUpdate.getTimeToMake() && require(toMake)) {
			dishUpdate.setTimeToMake(toMake);;
		}
		//first remove all current dishes so no duplicates will be added
		for(Component c : dishUpdate.getComponenets()) {
			dishUpdate.removeComponent(c);
		}
		
		ArrayList<Component> components = new ArrayList<>();
		for(int compID : componentsInDishList) {
			//if the id of the component in componentsInDishList from user exist in system, add the component to the dish
			if(Restaurant.getInstance().getComponenets().containsKey(compID)){
				//get the component data by his id
				Component temp = Restaurant.getInstance().getRealComponent(compID);
				//add it to the ArrayList of components in the dish
				components.add(temp);
			}
		}
		//add new components after change
		for(Component c : components) {
			dishUpdate.addComponent(c);
		}

		
		
		//after all changes were checked, update the Cook in the database
		return Restaurant.getInstance().getDishes().put(dishUpdate.getId(), dishUpdate) != null;
	}
	
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
	 * a method to add custom made dishes by the customer to his order
	 * @param custForOrder - the customer for the order
	 * @param dishesInOrderList - the custom made dishes for the order
	 * @return
	 * @throws IllegelInputException 
	 * @throws SensitiveException 
	 * @throws IllegalCustomerException 
	 */
	public boolean addCustomOrder(int id, int custForOrder, ArrayList<Dish> dishesInOrderList) throws IllegelInputException, IllegalCustomerException, SensitiveException {
		boolean validate = (requireNotZeroOrNegative(custForOrder) );
		if(!validate) {
			throw new IllegelInputException();
		}
		Customer c= Restaurant.getInstance().getRealCustomer(custForOrder);
		Order order = new Order(id,c,dishesInOrderList,null);
		return Restaurant.getInstance().addCustomOrder(order);
	}
	/**
	 * a method to update the data of an existing Order
	 * @param id
	 * @param cName
	 * @param lactose
	 * @param gluten
	 * @param price
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean updateOrderGUI(int id,int custForOrder, ArrayList<Integer> dishesInOrderList) throws IllegelInputException {
		boolean validate = (requireNotZeroOrNegative(id,custForOrder) );
		if(!validate) {
			throw new IllegelInputException();
		}
		
		//get the current Order by its id from the database
		Order orderUpdate = Restaurant.getInstance().getRealOrder(id);
		//check each non empty parameter to update

		//if new customer to the updated order was entered, set him
		if(custForOrder != orderUpdate.getCustomer().getId() && require(custForOrder)) {
			orderUpdate.setCustomer(Restaurant.getInstance().getRealCustomer(custForOrder));
		}

		//first remove all current dishes so no duplicates will be added
		for(Dish d : orderUpdate.getDishes()) {
			orderUpdate.removeDish(d);
		}
		
		ArrayList<Dish> dishes = new ArrayList<>();
		for(int dishID : dishesInOrderList) {
			//if the id of the Dish in dishesInOrderList from user exist in system, add the Dish to the Order
			if(Restaurant.getInstance().getDishes().containsKey(dishID)){
				//get the Dish data by his id
				Dish temp = Restaurant.getInstance().getRealDish(dishID);
				//add it to the ArrayList of components in the dish
				dishes.add(temp);
			}
		}
		//add new dishes after change
		for(Dish d2 : dishes) {
			orderUpdate.addDish(d2);
		}

		
		
		//after all changes were checked, update the Cook in the database
		return Restaurant.getInstance().getOrders().put(orderUpdate.getId(), orderUpdate) != null;
	}
	
	/**
	 * a method to add an order to the database
	 * @param id - the id of the order
	 * @param custForOrder - the id of the customer in the order
	 * @param dishesInOrderList - the dishes in the order
	 * @param DeliveriesInOrderList - the delivery in the order
	 * @return true if success, false if failed.
	 * @throws Exception
	 */

	
	//trying to create order without creating a delivery, and only after linking delivery to it, add the order to HashSet again
	public boolean addOrderFromGUI(int id,int custForOrder, ArrayList<Integer> dishesInOrderList) throws Exception{

		//check for parameters of class order and validate id
		boolean validate = (require(id,custForOrder,dishesInOrderList)) && (requireNotZeroOrNegative(id,custForOrder));

		// if not valid throw exception
		if(!validate) {
			throw new IllegelInputException();			
		}

		// create a new customer instance with the data we got from GUI		
		Customer c= Restaurant.getInstance().getRealCustomer(custForOrder);
		
		ArrayList<Dish> AlDishes=new ArrayList<Dish>();
			
		for(Dish d : Restaurant.getInstance().getDishes().values()) {
			for(int i : dishesInOrderList) {
				if(d.getId().equals(i)) {
					AlDishes.add(d);
				}
			}		
		}
		
		
		Order order = new Order(id,c,AlDishes,null);
		
		return Restaurant.getInstance().addOrder(order);
	}
	
	/**
	 * a method to remove an order from the database
	 * @param id
	 * @return
	 * @throws IllegelInputException
	 */
	public boolean removeOrderFromGUI(int id) throws IllegelInputException{
		boolean validate = requireNotZeroOrNegative(id);
		if(!validate) {
			throw new IllegelInputException();
		}
		Order orderDelete = Restaurant.getInstance().getRealOrder(id);		
		return Restaurant.getInstance().removeOrder(orderDelete);
	}
	
	/************************************Delivery Page
	 * @throws IllegelInputException *******************************************/
	public boolean removeDeliveryFromGUI(int id) throws IllegelInputException {
		boolean validate = requireNotZeroOrNegative(id);
		if(!validate) {
			throw new IllegelInputException();
		}
		Delivery delToDelete = Restaurant.getInstance().getRealDelivery(id);
		return Restaurant.getInstance().removeDelivery(delToDelete);
	}
	
	/**
	 * a method to add a regular delivery to the database
	 * @param id - the id of the regular delivery
	 * @param dpID - the delivery person of the regular delivery
	 * @param dArea - the delivery area of the regular delivery
	 * @param isDelivered - is the regular delivery delivered
	 * @param diliveredDate - the date of the regular delivery
	 * @param orders - the orders of the regular delivery
	 * @return
	 * @throws Exception
	 */
	public boolean addRegularDeliveryFromGUI(int id, int dpID, String dArea, boolean isDelivered, LocalDate diliveredDate, HashSet<Integer> orders) throws Exception{

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

		/*create the TreeSet of orders in the regular order*/
		TreeSet<Order> ordersForRegular = new TreeSet<>();

		for(int temp : orders) {
			for(Order ordersss : Restaurant.getInstance().getOrders().values()) {
				if(temp == ordersss.getId()) {
					ordersForRegular.add(ordersss);
				}
			}
		}
		
		RegularDelivery deliveryToAdd = new RegularDelivery(id,ordersForRegular,delPer,tempArea,isDelivered,diliveredDate);			
		System.out.println(deliveryToAdd.getOrders());		
		return Restaurant.getInstance().addDelivery(deliveryToAdd);
		
	}
	
	/**
	 * a method to add an express delivery to the database
	 * @param id - the id of the express delivery
	 * @param dpID - the delivery person of the express delivery
	 * @param dArea - the delivery area of the express delivery
	 * @param isDelivered - is express delivery delivered
	 * @param diliveredDate - the date of the express delivery
	 * @param isEXP - is it an express delivery
	 * @param postage - the extra fee (postage) of the express delivery
	 * @param orders - the order of the express delivery
	 * @return
	 * @throws Exception
	 */
	public boolean addDeliveryFromGUI(int id, int dpID, String dArea, boolean isDelivered, LocalDate diliveredDate, boolean isEXP, double postage, HashSet<Integer> orders) throws Exception{

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

		
		//if it is an express delivery like the user entered AND the orders size is 1 
		/*isEXP is necessary because if we want to change the postage we can't do it just by putting only 1 order,
		 * this helps us check if it's really an ExpressDelivery or not
		 * 
		 * TODO create an exception where one is true and the other is not -> expressDeliveryMissMatchException*/
		
		if((isEXP== true && orders.size()!=1) || (isEXP== false && orders.size()==1)) {
			throw new expressDeliveryMissMatchException();
		}
		
		if(isEXP && orders.size()==1) {
			//if a new postage was entered, use it
			if(postage != 5.0) {
				Order o = null;
				for(Order temp : Restaurant.getInstance().getOrders().values()) {
					for(int i : orders) {
						if(temp.getId() == i) {
							o = temp;
						}
						
					}
				}
				//use the given postage
				Delivery deliveryToAdd = new ExpressDelivery(id,delPer,tempArea,isDelivered,o,postage,diliveredDate);
				return Restaurant.getInstance().addDelivery(deliveryToAdd);
				
			}
			//if no new postage, the default is 5.0
			else {
				Order o = null;
				for(Order temp : Restaurant.getInstance().getOrders().values()) {
					for(int i : orders) {
						if(temp.getId() == i) {
							o = temp;
						}
						
					}
				}
				Delivery deliveryToAdd = new ExpressDelivery(id,delPer,tempArea,isDelivered,o,diliveredDate);
				return Restaurant.getInstance().addDelivery(deliveryToAdd);

			}
			
		}
		else {
			 throw new expressDeliveryMissMatchException();
		}

	}
	

	
	/**************************************Delivery Area Page****************************************/
	
	/**
	 * a method to add add a delivery area from GUI
	 * @param id - the id of the delivery area
	 * @param aName - the name of the delivery area
	 * @param hoods - the neighborhoods in the delivery area
	 * @param deliveryTime - the delivery time of the delivery area
	 * @return true if added successfully, false if not
	 * @throws IllegelInputException 
	 */
	public boolean updateDeliveryAreaGUI(int id,String aName,ArrayList<String> hoods, int deliveryTime) throws IllegelInputException {
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
		DeliveryArea oldArea = Restaurant.getInstance().getRealDeliveryArea(id);
		
		for(Neighberhood n : oldArea.getNeighberhoods()) {
			oldArea.removeNeighberhood(n);
		}
		for(Neighberhood n : aHoods) {
			oldArea.addNeighberhood(n);			
		}

		for(DeliveryPerson dp : oldArea.getDelPersons()) {
			dp.setArea(oldArea);
		}
		for(Delivery d : oldArea.getDelivers()) {
			d.setArea(oldArea);
		}

		
		
		return Restaurant.getInstance().getAreas().put(oldArea.getId(), oldArea) == null;	
	}
	
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
