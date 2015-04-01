package ua.epam.rd.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Order.findById", query="SELECT o FROM Order o "
			+ "WHERE o.id = :orderId"),
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
	
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(
//			name="pizzasinorders",
//			joinColumns={@JoinColumn(name="order_id", referencedColumnName="orders_id")},
//			inverseJoinColumns={@JoinColumn(name="pizza_id", referencedColumnName="pizzas_id")})
//	private List<Pizza> pizzas;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private List<PizzasInOrders> pizzasInOrders;
	
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
//		price += p.getPrice().doubleValue();
		addPizzasInOrders(p, 1);
	}
	public void addPizza(Pizza p, int quantity) {
		addPizzasInOrders(p, quantity);
	}
	
	public List<PizzasInOrders> getPizzasInOrders() {
		return pizzasInOrders;
	}
	public void setPizzasInOrders(List<PizzasInOrders> pizzasInOrders) {
		this.pizzasInOrders = pizzasInOrders;
	}
	public void addPizzasInOrders(Pizza p, int quantity) {
		if (pizzasInOrders == null) {
			pizzasInOrders = new ArrayList<PizzasInOrders>();
			pizzasInOrders.add(new PizzasInOrders(this, p, quantity));
			return;
		}
		if (pizzasInOrders.isEmpty()) {
			pizzasInOrders.add(new PizzasInOrders(this, p, quantity));
			return;
		}
		for (PizzasInOrders pio : pizzasInOrders) {
			if (pio.getPizza().getName().equals(p.getName())) {
				pio.setPizzaInOrderQuantity(pio.getPizzaInOrderQuantity() + quantity);
				if (pio.getPizzaInOrderQuantity() < 0) {
					pio.setPizzaInOrderQuantity(0);
				}
				return;
			}
		}
		pizzasInOrders.add(new PizzasInOrders(this, p, quantity));
	}

	public Double getPrice() {
		return price;
	}
	
	@PostLoad
	private void calculatePrice() {
		if ((!pizzasInOrders.isEmpty()) && (price == 0.0)) {
			for (PizzasInOrders pio: pizzasInOrders) {				
				price += pio.getPizza().getPrice().doubleValue() * pio.getPizzaInOrderQuantity();
			}
		}
	}
	
	@Override
	public String toString() {
		String res;
		
		res = "{" + id + "; " + name + "; " + date + "; " + price + "; ";
		res += "[ ";
		if (pizzasInOrders == null) {
			res += "NO PIZZAS";
		} else {
			for (PizzasInOrders pio: pizzasInOrders) {
				res += pio.getPizza() + "; " + pio.getPizzaInOrderQuantity() + "; \n"; 
			}
		}
		res += " ]";
		res += "}";
		
		return res;		
	}
}
