package ua.epam.rd.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;

public class Order {
	private int id;
	private Date date;
	private String name;
	private List<Pizza> pizzas;
	private Double price;
	
	public Order() {
		date = new Date(Calendar.getInstance().getTimeInMillis());
		name = "" + id + "-" + date;
		pizzas = new ArrayList<Pizza>();
		//Pizza p = new Pizza();
		//pizzas.add(p);
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
}
