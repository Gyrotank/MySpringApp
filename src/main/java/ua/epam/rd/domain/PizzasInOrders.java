package ua.epam.rd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="PizzasInOrders.findAll", query="SELECT pio FROM PizzasInOrders pio"),
	@NamedQuery(name="PizzasInOrders.findAllByOrder", query="SELECT pio FROM PizzasInOrders pio "
			+ "WHERE pio.order = :order"),
	@NamedQuery(name="PizzasInOrders.findAllPizzasInOrder", query="SELECT pio.pizza FROM PizzasInOrders pio "
			+ "WHERE pio.order = :order")
})
@Table(name = "pizzasinorders")
public class PizzasInOrders {
	
	@Id
	@GeneratedValue
	@Column(name = "pizzas_in_orders_id")
	private int pizzasInOrdersId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")	
	private Order order;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pizza_id")	
	private Pizza pizza;
	
	@Column(name = "quantity")	
	private Integer pizzaInOrderQuantity;

	public int getPizzasInOrdersId() {
		return pizzasInOrdersId;
	}
	public void setPizzasInOrdersId(int pizzasInOrdersId) {
		this.pizzasInOrdersId = pizzasInOrdersId;
	}
	
	public PizzasInOrders() {
		
	}
	
	public PizzasInOrders(Order order, Pizza pizza, int quantity) {
		this.order = order;
		this.pizza = pizza;
		this.pizzaInOrderQuantity = quantity;
	}

	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Integer getPizzaInOrderQuantity() {
		return pizzaInOrderQuantity;
	}
	public void setPizzaInOrderQuantity(Integer pizzaInOrderQuantity) {
		this.pizzaInOrderQuantity = pizzaInOrderQuantity;
	}
	
	@Override
	public String toString() {		
		String res = "{" + pizzasInOrdersId + "; ";
		if (order == null) {
			res += "ORDER WAS DELETED";
		} else {
			res += order.getName();
		}				
		if (pizza == null) {
			res += "; PIZZA NO LONGER AVAILABLE";
		} else {
			res += "; " + pizza.getName();
		}
		res += "; " + pizzaInOrderQuantity + '}'+"\n";
		return res;
	}
}
