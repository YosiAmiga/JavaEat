package view;

import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Exceptions.EmptyComboBoxException;
import Exceptions.IllegalCustomerException;
import Exceptions.IllegelInputException;
import Exceptions.NoComponentsExceptions;
import Exceptions.SensitiveException;
import Exceptions.SimilarIDInSystemException;
import controller.PrimaryController;
import controller.Sounds;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import Model.Component;
import Model.Customer;
import Model.Delivery;
import Model.Dish;
import Model.Order;
import Model.Restaurant;
import Utils.DishType;

public class CustomerMyOrdersController implements Initializable {
	private static final String Input = "Rest.ser";

	/*The controller to add and remove everything from the GUI to the database*/
	PrimaryController control=new PrimaryController();
	//the customer currently logged in
	static Customer customer;
	/***********New order Page************/
	@FXML
	private ComboBox<String> starters;
	@FXML
	private Button addStarter;
	@FXML
	private ComboBox<String> MainDishes;	
	@FXML
	private Button addMainDish;
	@FXML
	private ComboBox<String> desserts;
	@FXML
	private Button addDessert;
	@FXML
	private TextArea dishesInOrderShow;	
	@FXML
	private Button clearDishesInOrder;
	@FXML
	private Button addOrder;
	
	/*******************Customize your dish page********************/
	@FXML
	private ComboBox<String> dishesInMenu;
	@FXML
	private Button addDishToEdit;
	@FXML
	private ComboBox<String> componentsInDish;
	@FXML
	private Button addComp;
	@FXML
	private Button clearComp;
	@FXML
	private TextArea compList;
	@FXML
	private Button addCustomDish;
	
	@FXML
	private Button clearCustomDishes;
	@FXML
	private TextArea customDishesList;
	@FXML
	private Button addCustomOrder;
	
	
	/***********Remove order Page************/
	@FXML
	private ComboBox<String> currentOrders;
	
	/*******************Orders history page********************/
	@FXML
	private TableView<Order> ordersTable;
	@FXML
	private TableColumn<Order,Integer> orderID;
	@FXML
	private TableColumn<Order, Customer> orderCustomer;
	@FXML
	private TableColumn<Order, ArrayList<Dish>> orderDishes;
//	@FXML
//	private TableColumn<Order, Delivery> orderDelivery;

	/*******************View dishes in menu page********************/
	
	/****************Dishes Table****************/
	@FXML
	private TableView<Dish> dishesTable;
	@FXML
	private TableColumn<Dish,Integer> dishID;
	@FXML
	private TableColumn<Dish, String> dishName;
	@FXML
	private TableColumn<Dish, DishType> dishType;
	@FXML
	private TableColumn<Dish, ArrayList<Component>> dishComponents;
	@FXML
	private TableColumn<Dish, Integer> dishTime;
	/***************************************************************/
	ArrayList<Integer> dishesInOrderList = new ArrayList<>();
	ArrayList<String> dishesInOrderText = new ArrayList<>();
	ArrayList<Integer> customDishesInOrderList = new ArrayList<>();
	ArrayList<String> customComponentsInOrderText = new ArrayList<>();
	ArrayList<String> customDishesInOrderText = new ArrayList<>();
	

	//the custom made dishes by the customer
	ArrayList<Dish> customMadeDishes = new ArrayList<>();
	ArrayList<Component> compsInDish = new ArrayList<>();
	ArrayList<Integer> componentsInDishList = new ArrayList<>();
	ArrayList<String> componentsInDishesStrings = new ArrayList<>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		/*set in the table all the dishes data from database for each of their fields*/
		dishID.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
		dishName.setCellValueFactory(new PropertyValueFactory<Dish, String>("dishName"));
		dishType.setCellValueFactory(new PropertyValueFactory<Dish, DishType>("type"));
		dishComponents.setCellValueFactory(new PropertyValueFactory<Dish, ArrayList<Component>>("componenets"));
		dishTime.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("timeToMake"));
		dishesTable.setItems(getDishes());
		
		/***************Load list of components in the system***************/
		ArrayList<String> componentsDB = new ArrayList<>();
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			componentsDB.add("Component ID: "+c.getId() + " Component Name: " + c.getComponentName());
		}
		ObservableList<String> ObservableListComponents = FXCollections.observableArrayList();
		ObservableListComponents.addAll(componentsDB);
		componentsInDish.setItems(ObservableListComponents);
		
		
		/**************Load list of dishes in system*********/
		ArrayList<String> startersDB = new ArrayList<>();
		ArrayList<String> mainDishesDB = new ArrayList<>();
		ArrayList<String> dessertsDB = new ArrayList<>();
		ArrayList<String> dishesDB = new ArrayList<>();
		
		for(Dish d :  Restaurant.getInstance().getDishes().values()) {
			dishesDB.add("Dish ID: " + d.getId() + " Dish Name: " + d.getDishName());
			
			if(d.getType().toString().equals("Starter")) {
				startersDB.add("Dish ID: " + d.getId() + " Dish Name: " + d.getDishName());
			}
			if(d.getType().toString().equals("Main")) {
				mainDishesDB.add("Dish ID: " + d.getId() + " Dish Name: " + d.getDishName());
			}
			
			if(d.getType().toString().equals("Dessert")) {
				dessertsDB.add("Dish ID: " + d.getId() + " Dish Name: " + d.getDishName());
			}
		}
		
		/************load list of orders in the system **************/
		ArrayList<String> ordersDB = new ArrayList<>();
		for(Order o :  Restaurant.getInstance().getOrders().values()) {
			if(o.getCustomer().getId() == customer.getId()) {
				ordersDB.add(o.toString());			
			}
		}
		ObservableList<String> ObservableListOrders = FXCollections.observableArrayList();
		ObservableListOrders.addAll(ordersDB);
		currentOrders.setItems(ObservableListOrders);

	
		ObservableList<String> ObservableListStarters = FXCollections.observableArrayList();
		ObservableList<String> ObservableListMainDishes = FXCollections.observableArrayList();
		ObservableList<String> ObservableListDesserts = FXCollections.observableArrayList();
		ObservableList<String> ObservableListDishes = FXCollections.observableArrayList();
		
		ObservableListStarters.addAll(startersDB);
		starters.setItems(ObservableListStarters);		
		
		ObservableListMainDishes.addAll(mainDishesDB);
		MainDishes.setItems(ObservableListMainDishes);

		ObservableListDesserts.addAll(dessertsDB);
		desserts.setItems(ObservableListDesserts);
		
		ObservableListDishes.addAll(dishesDB);
		dishesInMenu.setItems(ObservableListDishes);
		
		/*set in the table all the orders data from database for each of their fields*/
		orderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		orderCustomer.setCellValueFactory(new PropertyValueFactory<Order, Customer>("customer"));
		orderDishes.setCellValueFactory(new PropertyValueFactory<Order, ArrayList<Dish>>("dishes"));
		ordersTable.setItems(getOrders());
		
		
	}
	/***********Add custom order********/
	public void addCustomOrder(ActionEvent e) {
		String section = "Order";
		try {
			int orderID = 1;
			for(Order or : Restaurant.getInstance().getOrders().values()) {
				//if there is an order with the current id, keep adding to orderID
				if(or.getId() == orderID) {
					orderID++;
				}
			}					

			int custForOrder= customer.getId();//get the customer's id after viewing the combo box				
			if(control.addCustomOrder(orderID,custForOrder, customMadeDishes)) {
				successAdded(section, "Success");
				Restaurant.save(Input);		
			}
			//if could not add customer
			else {
				fail(section,"This id already exists in the orders database!");
			}
			for(Order or : Restaurant.getInstance().getOrders().values()) {
				System.out.println(or.getDelivery());
			}


		}

		catch(SensitiveException e1) {
			fail(section, e1.toString());
		}
		catch(IllegalCustomerException e1) {
			fail(section, e1.toString());
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(NumberFormatException e1) {
			fail(section, "Wrong Input!");
		}
		catch (Exception e1) {
			fail(section, e1.toString());
		}
		finally {
			refreshScreen();
		}
	}
	
	/******clear the order from the custom dishes******/
	public void clearDishesFromOrder(ActionEvent e) {
		customMadeDishes.removeAll(customMadeDishes);
		customDishesInOrderText.removeAll(customDishesInOrderText);
		customDishesList.setText("");
	}


	/************Add custom dish to order***********/
	public void addCustomDish(ActionEvent e) {
		String section = "Dishes";
		//order can have several dishes
		try {
			//clean the compsInDish list
			componentsInDishesStrings.removeAll(componentsInDishesStrings);
			compsInDish.removeAll(compsInDish);
			if(dishesInMenu.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			
			String str = dishesInMenu.getValue();
			customDishesInOrderText.add(str);
			
			String dishIDOnly= str.replaceAll("[^0-9]", "");	
			customDishesInOrderList.add(Integer.parseInt(dishIDOnly));

			String list="";
			for(String s : customDishesInOrderText) {
				list += s+"\n";
			}			
			customDishesList.setText(list);
			
			
			if(compList.getText() == null) {
				throw new NoComponentsExceptions();
			}
			for(String compData : compList.getText().split("\\n")) {
				componentsInDishesStrings.add(compData);
			}
			System.out.println("\nshow comps: " + componentsInDishesStrings);

			
			//componentsInDishes AL of the components to be added to dish
			for(String compIDString : componentsInDishesStrings) {
				String compIDOnly= compIDString.replaceAll("[^0-9]", "");
				componentsInDishList.add(Integer.parseInt(compIDOnly));
			}
			//if the component ID exist both in the system and in the ArrayList of components,
			//add it to components ArrayList
			for(Component co : Restaurant.getInstance().getComponenets().values()) {
				for(int i : componentsInDishList) {
					if(co.getId() == i) {
						compsInDish.add(co);
					}
				}
			}

			Dish customDish = Restaurant.getInstance().getRealDish(Integer.parseInt(dishIDOnly));
			//before comp change
			System.out.println("\ncomps in dish before change: " + customDish.getComponenets());

			Dish blalal = new Dish(customDish.getId(),customDish.getDishName(),customDish.getType(),compsInDish,customDish.getTimeToMake());
			
			//after comp change
			System.out.println("\ncomps in dish after change: " + blalal.getComponenets());
			customMadeDishes.add(blalal);
			System.out.println("\ncustom dishes AL: "+customMadeDishes);

		}
		catch(NoComponentsExceptions e1) {
			fail("empty dish",e1.toString());
		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
		
	}
	
	/*********clear all components form dish***/
	public void clearComponentsFromDish(ActionEvent e) {
		customComponentsInOrderText.removeAll(customComponentsInOrderText);
		compList.setText("");
	}
	
	/********add component to custom dish******/
	public void addComponentToDish(ActionEvent e) {
		String section = "Component";
		try {
			
			//show all the components value in the text area
			if(componentsInDish.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			String str = componentsInDish.getValue();
			customComponentsInOrderText.add(str);			
			
			String list="";
			for(String s : customComponentsInOrderText) {
				list += s+"\n";
			}
			compList.setText(list);

		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
		
	}
	
	/************Load dishes components******/
	public void loadComponents(ActionEvent e) {
		String section ="Components";
		try {
			if(dishesInMenu.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			//remove previous saved components
			customComponentsInOrderText.removeAll(customComponentsInOrderText);
			
			String str = dishesInMenu.getValue();
			String numberOnly= str.replaceAll("[^0-9]", "");
			
			Dish tempDish = Restaurant.getInstance().getRealDish(Integer.parseInt(numberOnly));
			
			for(Component c : tempDish.getComponenets()) {
				customComponentsInOrderText.add("Component ID: " + c.getId() + " Component Name: " + c.getComponentName());				
			}
			
			String list="";
			for(String s : customComponentsInOrderText) {
				list += s+"\n";
			}
			compList.setText(list);
			
		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
		catch(Exception e1) {
			
		}
	}
	/**************Add an Order*************/
	public void addOrder(ActionEvent e){
		String section = "Order";
		try {
			int orderID = 1;
			for(Order or : Restaurant.getInstance().getOrders().values()) {
				//if there is an order with the current id, keep adding to orderID
				if(or.getId() == orderID) {
					orderID++;
				}
			}					

			int custForOrder= customer.getId();//get the customer's id after viewing the combo box				
			if(control.addOrderFromGUI(orderID,custForOrder, dishesInOrderList)) {
				successAdded(section, "Success");
				Restaurant.save(Input);		
			}
			//if could not add customer
			else {
				fail(section,"This id already exists in the orders database!");
			}
			for(Order or : Restaurant.getInstance().getOrders().values()) {
				System.out.println(or.getDelivery());
			}


		}

		catch(SensitiveException e1) {
			fail(section, e1.toString());
		}
		catch(IllegalCustomerException e1) {
			fail(section, e1.toString());
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(SimilarIDInSystemException e1) {
			fail(section,e1.toString());
		}
		catch(NumberFormatException e1) {
			fail(section, "Wrong Input!");
		}
		catch (Exception e1) {
			fail(section, e1.toString());
		}
		finally {
			refreshScreen();
		}
	}
	
	/**************Remove an Order*************/

	public void removeOrder(ActionEvent e) {
		String section = "Order";
		try {
			if(currentOrders.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			String str = currentOrders.getValue();
			//Extract only the Order ID in order to remove him
			String numberOnly= str.replaceAll("[^0-9]", "");	
			
			int id = Integer.parseInt(numberOnly);
			
			if(control.removeOrderFromGUI(id)) {
				successRemove(section, "Success");
				Restaurant.save(Input);
			}
			else {
				fail(section, "This id does not exists in the orders database!");
			}
			
		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
		catch(IllegelInputException e1) {
			fail(section, e1.toString());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		refreshScreen();
	}
	
	/*get all the orders from the database*/
	private ObservableList<Order> getOrders(){
		ObservableList<Order> orders=FXCollections.observableArrayList();
		ArrayList<Order> query=new ArrayList<Order>();
		for(Order o :  Restaurant.getInstance().getOrders().values()) {
			if(o.getCustomer().getId() == customer.getId()) {
				query.add(o);			
			}
		}
		orders.addAll(query);
		return orders;	
	}
	
	/**
	 * a method to show all the dishes in the order
	 */
	public void addStartersToOrder(ActionEvent e) {
		String section = "Dishes";
		//order can have several dishes
		try {
			
			//show all the components value in the text area
			if(starters.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			String str = starters.getValue();
			dishesInOrderText.add(str);
			
			//Extract only the component ID in order to add him to the dish
			String numberOnly= str.replaceAll("[^0-9]", "");	
			dishesInOrderList.add(Integer.parseInt(numberOnly));
			String list="";
			for(String s : dishesInOrderText) {
				list += s+"\n";
			}
			dishesInOrderShow.setText(list);

		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
	}
	
	/**
	 * a method to show all the dishes in the order
	 */
	public void addMainDishToOrder(ActionEvent e) {
		String section = "Dishes";
		//order can have several dishes
		try {
			
			//show all the components value in the text area
			if(MainDishes.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			String str = MainDishes.getValue();
			dishesInOrderText.add(str);
			
			//Extract only the component ID in order to add him to the dish
			String numberOnly= str.replaceAll("[^0-9]", "");	
			dishesInOrderList.add(Integer.parseInt(numberOnly));
			String list="";
			for(String s : dishesInOrderText) {
				list += s+"\n";
			}
			dishesInOrderShow.setText(list);
			System.out.println(dishesInOrderList);
		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
	}
	
	/**
	 * a method to show all the dishes in the order
	 */
	public void addDessertsToOrder(ActionEvent e) {
		String section = "Dishes";
		//order can have several dishes
		try {
			
			//show all the components value in the text area
			if(desserts.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			String str = desserts.getValue();
			dishesInOrderText.add(str);
			
			//Extract only the component ID in order to add him to the dish
			String numberOnly= str.replaceAll("[^0-9]", "");	
			dishesInOrderList.add(Integer.parseInt(numberOnly));
			String list="";
			for(String s : dishesInOrderText) {
				list += s+"\n";
			}
			dishesInOrderShow.setText(list);
			System.out.println(dishesInOrderList);

		}
		catch(EmptyComboBoxException e1) {
			failSelection(section,e1.toString());
		}
	}
	
	public void clearDishesInOrderList(ActionEvent e) {
		dishesInOrderText.removeAll(dishesInOrderText);
		dishesInOrderList.removeAll(dishesInOrderList);
		dishesInOrderShow.setText("");
	}
	
	
	public void refreshScreen(){ 
		/************load list of orders in the system **************/
		ArrayList<String> ordersDB = new ArrayList<>();
		for(Order o :  Restaurant.getInstance().getOrders().values()) {
			if(o.getCustomer().getId() == customer.getId()) {
				ordersDB.add(o.toString());			
			}
		}
		ObservableList<String> ObservableListOrders = FXCollections.observableArrayList();
		ObservableListOrders.addAll(ordersDB);
		currentOrders.setItems(ObservableListOrders);
		
		/*set in the table all the orders data from database for each of their fields*/
		orderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		orderCustomer.setCellValueFactory(new PropertyValueFactory<Order, Customer>("customer"));
		orderDishes.setCellValueFactory(new PropertyValueFactory<Order, ArrayList<Dish>>("dishes"));
		ordersTable.setItems(getOrders());
		
	}
	
	
	
	/*get all the dishes from the database*/
	private ObservableList<Dish> getDishes(){
		ObservableList<Dish> dishes=FXCollections.observableArrayList();
		ArrayList<Dish> query=new ArrayList<Dish>(Restaurant.getInstance().getDishes().values());
		dishes.addAll(query);
		return dishes;	
	}
	
	
	
	
	
	
	public void successRemove(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Removed Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successAdded(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Added Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successUpdate(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Updated Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failSelection(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to select : " + content);
		al.setHeaderText(header);
		al.setTitle("ComboBox");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failUpdate(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to update : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void fail(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to add : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}

	public void goodSound() {
		Sounds s = new Sounds();
		try {
			s.successSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void badSound() {
		Sounds s = new Sounds();
		try {
			s.errorSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void successSound() {
		Sounds s = new Sounds();
		try {
			s.addSound();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	

}
