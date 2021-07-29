package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import Model.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ManagerDataBaseController implements Initializable {
//	@FXML
//	private TableView<OrdersForTable> orders;
//
//	@FXML
//	private TableColumn<OrdersForTable, Integer> ordersID;
//
//	@FXML
//	private TableColumn<OrdersForTable, String> ordersOwner;
//
//	@FXML
//	private TableColumn<OrdersForTable, Integer> ordersQuantity;
//
//	@FXML
//	private TableColumn<OrdersForTable,Double> ordersPrice;
//
//	@FXML
//	private TableColumn<OrdersForTable, String> OrdersFlights;
//
//	@FXML
//	private TableColumn<OrdersForTable, String> OrderAccom;
//
//	@FXML
//	private TableColumn<OrdersForTable, String> ordersTrips;
//
//	@FXML
//	private TableView<TravelPackageForTable> packages;
//
//	@FXML
//	private TableColumn<TravelPackageForTable, String> packageName;
//
//	@FXML
//	private TableColumn<TravelPackageForTable, Integer> packageQuantity;
//
//	@FXML
//	private TableColumn<TravelPackageForTable, Double> packagePrice;
//
//	@FXML
//	private TableColumn<TravelPackageForTable, String> packageFlights;
//
//	@FXML
//	private TableColumn<TravelPackageForTable, String> packageAccom;
//
//	@FXML
//	private TableColumn<TravelPackageForTable, String> packageTrips;
//
//	@FXML
//	private TableView<Flight> flights;
//	@FXML
//	private TableColumn<Flight,String> flightNumber;
//
//	@FXML
//	private TableColumn<Flight, Integer> flightNumofSeats;
//
//	@FXML
//	private TableColumn<Flight, Destination> flightFrom;
//
//	@FXML
//	private TableColumn<Flight, Destination> FlightTo;
//
//	@FXML
//	private TableColumn<Flight, Double> FlightsPrice;
//
//	@FXML
//	private TableColumn<Flight, Date> FlightDate;
//
//
//	@FXML
//	private TableView<AccommodationForTable> accommodations;
//
//	@FXML
//	private TableColumn<AccommodationForTable, Long> accID;
//
//	@FXML
//	private TableColumn<AccommodationForTable, String> accName;
//
//	@FXML
//	private TableColumn<AccommodationForTable, Integer> accRooms;
//
//	@FXML
//	private TableColumn<AccommodationForTable, Integer> accPeople;
//
//	@FXML
//	private TableColumn<AccommodationForTable, Double> accPrice;
//
//	@FXML
//	private TableColumn<AccommodationForTable, Destination> accLocation;
//
//	@FXML
//	private TableColumn<AccommodationForTable, String> accType;
//
//	@FXML
//	private TableColumn<AccommodationForTable, Integer> accOccupied;
//
//	@FXML
//	private TableView<Destination> destinations;
//
//	@FXML
//	private TableColumn<Destination, String> destCountry;
//
//	@FXML
//	private TableColumn<Destination, String> destCity;
//
//	@FXML
//	private TableView<Customer> customers;
//
//	@FXML
//	private TableColumn<Customer, Long> customerID;
//
//	@FXML
//	private TableColumn<Customer, String> customerFirst;
//
//	@FXML
//	private TableColumn<Customer, String> customerLast;
//
//	@FXML
//	private TableColumn<Customer, Date> customerBirth;
//
//	@FXML
//	private TableColumn<Customer, String> customerPass;
//
//	@FXML
//	private TableColumn<Customer, String> customerAnswer;
//
//	@FXML
//	private TableColumn<Customer, HashSet<Order>> customerOrders;
//
//	@FXML
//	private TableColumn<Customer, String> customerEmail;
//
//	@FXML
//	private TableColumn<Customer, Address> customerAdd;
//
//	@FXML
//	private TableView<Guide> guides;
//
//	@FXML
//	private TableColumn<Guide, Long> guideID;
//
//	@FXML
//	private TableColumn<Guide, String> guideFirst;
//
//	@FXML
//	private TableColumn<Guide, String> guideLast;
//
//	@FXML
//	private TableColumn<Guide, Date> guideBirth;
//
//	@FXML
//	private TableColumn<Guide, String> guidePass;
//
//	@FXML
//	private TableColumn<Guide, String> guideAnswer;
//
//	@FXML
//	private TableColumn<Guide, HashSet<Destination>> guideExp;
//	
//
//   
//
//	@FXML
//	private TableColumn<Guide, Date> guideStart;
//	@FXML
//	private TableColumn<Guide, String> guideEmail;
//
//	@FXML
//	private TableColumn<Guide, Address> guideAdd;
//
//	@FXML
//	private TableColumn<Guide, HashSet<GroupTrip>> guideTrips;
//
//
//	@FXML
//	private TableView<GroupTrip> trips;
//
//	@FXML
//	private TableColumn<GroupTrip, Integer> tripID;
//
//	@FXML
//	private TableColumn<GroupTrip, String> tripDescription;
//
//	@FXML
//	private TableColumn<GroupTrip, Date> tripDate;
//
//	@FXML
//	private TableColumn<GroupTrip, Destination> tripLocation;
//
//	@FXML
//	private TableColumn<GroupTrip, Guide> tripGuide;
//
//	@FXML
//	private TableColumn<GroupTrip, Double> tripPrice;
//
//	@FXML
//	private TableColumn<GroupTrip, Integer> tripCapacity;
//
//	@FXML
//	private TableColumn<GroupTrip, Integer> tripOccupied;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
//		//populate Orders Table from DB
//		ordersID.setCellValueFactory(new PropertyValueFactory<OrdersForTable, Integer>("id"));
//		ordersOwner.setCellValueFactory(new PropertyValueFactory<OrdersForTable, String>("owner"));
//		ordersQuantity.setCellValueFactory(new PropertyValueFactory<OrdersForTable, Integer>("quantity"));
//		ordersPrice.setCellValueFactory(new PropertyValueFactory<OrdersForTable, Double>("Price"));
//		OrdersFlights.setCellValueFactory(new PropertyValueFactory<OrdersForTable, String>("flights"));
//		OrderAccom.setCellValueFactory(new PropertyValueFactory<OrdersForTable, String>("accommodations"));
//		ordersTrips.setCellValueFactory(new PropertyValueFactory<OrdersForTable, String>("groupTrips"));
//		orders.setItems(getItems());
//		//populate Packages Table from DB
//		packageName.setCellValueFactory(new PropertyValueFactory<TravelPackageForTable, String>("Name"));
//		packageQuantity.setCellValueFactory(new PropertyValueFactory<TravelPackageForTable, Integer>("quantity"));
//		packagePrice.setCellValueFactory(new PropertyValueFactory<TravelPackageForTable, Double>("Price"));
//		packageFlights.setCellValueFactory(new PropertyValueFactory<TravelPackageForTable, String>("flights"));
//		packageAccom.setCellValueFactory(new PropertyValueFactory<TravelPackageForTable, String>("accommodations"));
//		packageTrips.setCellValueFactory(new PropertyValueFactory<TravelPackageForTable, String>("groupTrips"));
//		packages.setItems(getPackageItems());
//		//populate Flights Table from DB
//		flightNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightNumber"));
//		flightNumofSeats.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("numberOfSeats"));
//		flightFrom.setCellValueFactory(new PropertyValueFactory<Flight, Destination>("from"));
//		FlightTo.setCellValueFactory(new PropertyValueFactory<Flight, Destination>("to"));
//		FlightsPrice.setCellValueFactory(new PropertyValueFactory<Flight, Double>("price"));
//		FlightDate.setCellValueFactory(new PropertyValueFactory<Flight, Date>("dateTakeoff"));
//		flights.setItems(getFlights());
//		//populating Accommodations Table from DB
//		accID.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Long>("businessId"));
//		accName.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, String>("displayName"));
//		accRooms.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Integer>("numberOfRooms"));
//		accPeople.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Integer>("numberOfPeoplePerRoom"));
//		accPrice.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Double>("pricePerPerson"));
//		accLocation.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Destination>("location"));
//		accType.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, String>("type"));
//		accOccupied.setCellValueFactory(new PropertyValueFactory<AccommodationForTable, Integer>("currentNumberOfOccupiedRooms"));
//		accommodations.setItems(getAcc());
//		//Populating Destination Table from DB
//		destCountry.setCellValueFactory(new PropertyValueFactory<Destination, String>("country"));
//		destCity.setCellValueFactory(new PropertyValueFactory<Destination, String>("city"));
//		destinations.setItems(getDests());
//		//populating customer table from DB
//		customerID.setCellValueFactory(new PropertyValueFactory<Customer, Long>("id"));
//		customerFirst.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
//		customerLast.setCellValueFactory(new PropertyValueFactory<Customer, String>("surname"));
//		customerBirth.setCellValueFactory(new PropertyValueFactory<Customer, Date>("birthDate"));
//		customerPass.setCellValueFactory(new PropertyValueFactory<Customer, String>("password"));
//		customerAnswer.setCellValueFactory(new PropertyValueFactory<Customer, String>("answer"));
//		customerOrders.setCellValueFactory(new PropertyValueFactory<Customer, HashSet<Order>>("orders"));
//		customerEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
//		customerAdd.setCellValueFactory(new PropertyValueFactory<Customer, Address>("address"));
//		customers.setItems(getCustomers());
//		//populating Guide table from DB
//		guideID.setCellValueFactory(new PropertyValueFactory<Guide, Long>("id"));
//		guideFirst.setCellValueFactory(new PropertyValueFactory<Guide, String>("firstName"));
//		guideLast.setCellValueFactory(new PropertyValueFactory<Guide, String>("surname"));
//		guideBirth.setCellValueFactory(new PropertyValueFactory<Guide, Date>("birthDate"));
//		guidePass.setCellValueFactory(new PropertyValueFactory<Guide, String>("password"));
//		guideAnswer.setCellValueFactory(new PropertyValueFactory<Guide, String>("answer"));
//		guideExp.setCellValueFactory(new PropertyValueFactory<Guide, HashSet<Destination>>("experiencedDestinations"));
//		guideStart.setCellValueFactory(new PropertyValueFactory<Guide, Date>("startDate"));
//		guideEmail.setCellValueFactory(new PropertyValueFactory<Guide, String>("email"));
//		guideAdd.setCellValueFactory(new PropertyValueFactory<Guide, Address>("address"));
//		guideTrips.setCellValueFactory(new PropertyValueFactory<Guide, HashSet<GroupTrip>>("groupsTrip"));
//		guides.setItems(getGuides());
//		//Populating GroupTrip table from DB
//		tripID.setCellValueFactory(new PropertyValueFactory<GroupTrip, Integer>("tripId"));
//		tripDescription.setCellValueFactory(new PropertyValueFactory<GroupTrip, String>("description"));
//		tripDate.setCellValueFactory(new PropertyValueFactory<GroupTrip, Date>("date"));
//		tripLocation.setCellValueFactory(new PropertyValueFactory<GroupTrip, Destination>("location"));
//		tripGuide.setCellValueFactory(new PropertyValueFactory<GroupTrip, Guide>("guide"));
//		tripPrice.setCellValueFactory(new PropertyValueFactory<GroupTrip, Double>("price"));
//		tripCapacity.setCellValueFactory(new PropertyValueFactory<GroupTrip, Integer>("maxCapacity"));
//		tripOccupied.setCellValueFactory(new PropertyValueFactory<GroupTrip, Integer>("currentNumberOfTourists"));
//		trips.setItems(getTrips());
	}

//	private ObservableList<GroupTrip> getTrips() {
//		ObservableList<GroupTrip> trips=FXCollections.observableArrayList();
//		ArrayList<GroupTrip> query=new ArrayList<GroupTrip>(Shared.getInstance().getTrips().values());
//
//		trips.addAll(query);
//		return trips;	
//	}

//	private ObservableList<Guide> getGuides() {
//		ObservableList<Guide> guides=FXCollections.observableArrayList();
//		ArrayList<Guide> query=new ArrayList<Guide>(Shared.getInstance().getGuides().values());
//
//		guides.addAll(query);
//		return guides;	
//	}
//
//	private ObservableList<Customer> getCustomers() {
//		ObservableList<Customer> customer=FXCollections.observableArrayList();
//		ArrayList<Customer> query=new ArrayList<Customer>(Shared.getInstance().getCustomers().values());
//
//		customer.addAll(query);
//		return customer;	
//	}
//
//	private ObservableList<Destination> getDests() {
//		ObservableList<Destination> dest=FXCollections.observableArrayList();
//		ArrayList<String> query=new ArrayList<String>(Shared.getInstance().getDestinations().keySet());
//		ArrayList<Destination> tmp=new ArrayList<Destination> ();
//		for(String s:query)
//			tmp.addAll(Shared.getInstance().getDestinations().get(s));
//		dest.addAll(tmp);
//		return dest;	
//	}
//
//	private ObservableList<AccommodationForTable> getAcc() {
//		ObservableList<AccommodationForTable> acc=FXCollections.observableArrayList();
//		ArrayList<Accommodation> query=new ArrayList<Accommodation>(Shared.getInstance().getAccommodations().values());
//		ArrayList<AccommodationForTable> convertedForTable=new ArrayList<AccommodationForTable>();
//		for(Accommodation a:query)
//			convertedForTable.add(new AccommodationForTable(a));
//		acc.addAll(convertedForTable);
//		return acc;
//	}
//
//	private ObservableList<Flight> getFlights() {
//		ObservableList<Flight> flights=FXCollections.observableArrayList();
//		ArrayList<Flight> query=new ArrayList<Flight>(Shared.getInstance().getFlights().values());
//
//		flights.addAll(query);
//		return flights;	
//	}
//
//	private ObservableList<TravelPackageForTable> getPackageItems() {
//		ObservableList<TravelPackageForTable> packages=FXCollections.observableArrayList();
//		ArrayList<TravelPackage> query=new ArrayList<TravelPackage>(Shared.getInstance().getPackages().values());
//		ArrayList<TravelPackageForTable> convertedForTable=new ArrayList<TravelPackageForTable>();
//		for(TravelPackage o:query)
//			convertedForTable.add(new TravelPackageForTable(o));
//		packages.addAll(convertedForTable);
//		return packages;
//	}
//
//	private ObservableList<OrdersForTable> getItems() {
//		ObservableList<OrdersForTable> orders=FXCollections.observableArrayList();
//		ArrayList<Order> query=new ArrayList<Order>(Shared.getInstance().getOrders().values());
//		ArrayList<OrdersForTable> convertedForTable=new ArrayList<OrdersForTable>();
//		for(Order o:query)
//			convertedForTable.add(new OrdersForTable(o));
//		orders.addAll(convertedForTable);
//		System.out.println(convertedForTable.get(1).getOwner());
//		return orders;
//
//	}

}
