package ua.epam.rd.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o"),
	@NamedQuery(name="Order.findByName", query="SELECT o FROM Order o "
			+ "WHERE o.name = :orderName")
})
@Table(name = "orders")
public class Order implements OrderInterface {
	
	@Id
	@GeneratedValue
	@Column(name = "orders_id")
	private int id;
	
	@Column(name = "orders_date")
	private Date date;
	
	@Column(name = "orders_name")
	private String name;
	
	//private List<Pizza> pizzas;
	
	@Column(name = "orders_price")
	private Double price;
	
	public Order() {
		date = new Date(Calendar.getInstance().getTimeInMillis());
		name = "";
		//pizzas = new ArrayList<Pizza>();		
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
//		return pizzas;
		return new ArrayList<Pizza>();
	}
//	public void setPizzas(List<Pizza> pizzas) {
//		this.pizzas = pizzas;
//	}
	public void addPizza(Pizza p) {
//		pizzas.add(p);
		price += p.getPrice().doubleValue();
	}
	
	public Double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		String res;
		
		res = "{" + id + "; " + name + "; " + date + "; " + price + "; ";
//		res += "[ ";
//		for (Pizza p : pizzas) {
//			res += p + "; \n"; 
//		}
//		res += "]
		res += "}";
		
		return res;		
	}
}
