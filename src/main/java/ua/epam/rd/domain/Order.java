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
import javax.persistence.ManyToOne;
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
			+ "WHERE o.name = :orderName"),
	@NamedQuery(name="Order.updateOrderNameById", query="UPDATE Order o SET name = :newName "
			+ "WHERE o.id = :id"),
	@NamedQuery(name="Order.deleteOrderByName", query="DELETE FROM Order o "
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private List<PizzasInOrders> pizzasInOrders;
	
	@Column(name = "orders_price")
	private Double price;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="orders_status_id")
	private OrderStatus orderStatus;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="client_id")
	private Client client;
	
	public Order() {
		date = new Date(Calendar.getInstance().getTimeInMillis());
		name = "";				
		price = new Double(0.0);
		orderStatus = new OrderStatus();
		client = new Client();
	}
	
	public int getId() {
		return id;
	}	
	public void setId(final int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(final Date date) {
		this.date = date;
	}
	
	public String getName() {
		return name;
	}	
	public void setName(final String name) {
		this.name = name;
	}

	public List<Pizza> getPizzas() {
		return new ArrayList<Pizza>();		
	}

	public void addPizza(final Pizza p) {
		addPizzasInOrders(p, 1);
	}
	public void addPizza(final Pizza p, final int quantity) {
		addPizzasInOrders(p, quantity);
	}
	
	public List<PizzasInOrders> getPizzasInOrders() {
		return pizzasInOrders;
	}
	public void setPizzasInOrders(final List<PizzasInOrders> pizzasInOrders) {
		this.pizzasInOrders = pizzasInOrders;
	}
	public void addPizzasInOrders(final Pizza p, final int quantity) {
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
	void calculatePrice() {
		if ((!pizzasInOrders.isEmpty()) && (price == 0.0)) {
			for (PizzasInOrders pio: pizzasInOrders) {
				if (pio.getPizza() != null) {
					price += pio.getPizza().getPrice().doubleValue()
							* pio.getPizzaInOrderQuantity();
				}
			}
		}
	}
	
	public OrderStatus getStatus() {
		return orderStatus;
	}
	public void setStatus(final OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		String res;
		
		res = "{" + id + "; " + name + "; ";
		if (client == null) {
			res += "CLIENT DELETED";
		} else {
			res += client.getName(); 
		}
		res += "; " + orderStatus.getName() + "; " + date + "; " + price + "; ";
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
