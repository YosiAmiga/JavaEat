package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class ExpressDelivery extends Delivery implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order order;
	private double postage;
	
	//constructors for GUI
	
	public ExpressDelivery(int id, DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered , Order order , double postage, LocalDate deliveredDate) {
		super(id, deliveryPerson, area, isDelivered, deliveredDate);
		this.order = order;
		this.postage = postage;
	}
	/*if no new postage was enterd*/
	public ExpressDelivery(int id, DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered , Order order , LocalDate deliveredDate) {
		super(id, deliveryPerson, area, isDelivered, deliveredDate);
		this.order = order;
		this.postage = 5.0;
	}
	/******************************************/
	public ExpressDelivery(DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered , Order order , double postage, LocalDate deliveredDate) {
		super(deliveryPerson, area, isDelivered, deliveredDate);
		this.order = order;
		this.postage = postage;
	}
	
	public ExpressDelivery(DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered , Order order, LocalDate deliveredDate) {
		super(deliveryPerson, area, isDelivered,deliveredDate);
		this.order = order;
		this.postage = 5.0;
	}
	
	public ExpressDelivery(int id) {
		super(id);
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public double getPostage() {
		return postage;
	}
	
	public void setPostage(double postage) {
		this.postage = postage;
	}
	
	@Override
	public String toString() {
		return "Express delivery ID: " + this.getId() +
				"\nDelivery Person: " + this.getDeliveryPerson().getFirstName() + " " +this.getDeliveryPerson().getLastName() +
				"\nArea : " + this.getArea().getAreaName() +
//				"\nCustomer's Order: " + getOrder().getCustomer().getFirstName() + " " + getOrder().getCustomer().getLastName() +
				"\nis Deliverd: " + this.isDelivered()  +
				"\nExpress fee: " + postage;
	}	
}
