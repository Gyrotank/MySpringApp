package ua.epam.rd.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Pizza.readAll", query="SELECT p FROM Pizza p"),
	@NamedQuery(name="Pizza.readAllByType", query="SELECT p FROM Pizza p "
			+ "WHERE p.type = :pizzaType"),
	@NamedQuery(name="Pizza.findByName", query="SELECT p FROM Pizza p "
					+ "WHERE p.name = :pizzaName"),
	@NamedQuery(name="Pizza.updatePizzaPriceByName", query="UPDATE Pizza p SET price = :newPrice "
			+ "WHERE p.name = :pizzaName"),
	@NamedQuery(name="Pizza.deletePizzaByName", query="DELETE FROM Pizza p "
			+ "WHERE p.name = :pizzaName")
})
@Table(name = "pizzas")
public class Pizza {
	
	@Id
	@GeneratedValue
	@Column(name = "pizzas_id")
	private Integer pizzaId;
	
	@Column(name = "pizzas_name")
	private String name;
	
	@Column(name = "pizzas_price")
	private Double price;
	
	@Column(name = "pizzas_type")
	private PizzaType type;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "pizza_id")
	private List<PizzasInOrders> pizzasInOrders;
	
	public Pizza(){
		this.name = "Default Pizza";
		this.price = 50.0;
		this.type = PizzaType.MEAT;
	}
	
	public Pizza(final String name, final Double price, final PizzaType type){
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	public Integer getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(final Integer pizzaId) {
		this.pizzaId = pizzaId;
	}

	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(final Double price) {
		this.price = price;
	}
	
	public PizzaType getType() {
		return type;
	}
	public void setType(final PizzaType type) {
		this.type = type;
	}
	
	public List<PizzasInOrders> getPizzasInOrders() {
		return pizzasInOrders;
	}
	public void setOrders(final List<PizzasInOrders> pizzasInOrders) {
		this.pizzasInOrders = pizzasInOrders;
	}

	@Override
	public String toString() {
		String res;
		res = "{" + this.pizzaId + "; " + this.name + "; "
				+ this.type + "; " + this.price + "; ";
		res += "[ ";
		if (pizzasInOrders == null) {
			res += "NO ORDERS ";
		} else { 
			for (PizzasInOrders pio : pizzasInOrders) {
				if (pio.getOrder() == null) {
					res += "ORDER WAS DELETED";
				} else {
					res += pio.getOrder().getName();
				}
				res += ", " + pio.getPizzaInOrderQuantity();
				res += "; ";
			}
		}
		res += "] ";
		return res;
	}
}
