package ua.pizzadelivery.rd.domain;

import java.lang.annotation.Annotation;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@OrderAnnotation(
		name = "ATTENTION! ANNOTATED ORDER!"
)
public class OrderAnnotated implements OrderInterface {

	private int id;
	private Date date;
	private String name;
	private List<Pizza> pizzas;
	private Double price;
	
	public OrderAnnotated() {
		Class<OrderAnnotated> obj = OrderAnnotated.class;
		Annotation annotation = obj.getAnnotation(OrderAnnotation.class);
		OrderAnnotation orderAnnotation = (OrderAnnotation) annotation;
		
		date = new Date(Calendar.getInstance().getTimeInMillis());
		name = orderAnnotation.name();
		pizzas = new ArrayList<Pizza>();		
		price = new Double(0.0);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	
	public void addPizza(Pizza p) {
		pizzas.add(p);
		price += p.getPrice().doubleValue();
	}
	
	public Double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		String res;
		
		res = "{" + id + "; " + name + "; " + date + "; " + price + "; ";
		res += "[ ";
		for (Pizza p : pizzas) {
			res += p + "; \n"; 
		}
		res += "] }";
		
		return res;		
	}

	@Override
	public List<PizzasInOrders> getPizzasInOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPizzasInOrders(Pizza p, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client getClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClient(Client client) {
		// TODO Auto-generated method stub
		
	}

}
