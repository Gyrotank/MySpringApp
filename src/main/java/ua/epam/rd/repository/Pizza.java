package ua.epam.rd.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Pizza.findAll", query="SELECT p FROM Pizza p"),
	@NamedQuery(name="Pizza.findAllByType", query="SELECT p FROM Pizza p "
			+ "WHERE p.type = :pizzaType")
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
	
	public Pizza(){
		this.name = "Default Pizza";
		this.price = 50.0;
		this.type = PizzaType.MEAT;
	}
	
	public Pizza(String name, Double price, PizzaType type){
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	public Integer getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(Integer pizzaId) {
		this.pizzaId = pizzaId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public PizzaType getType() {
		return type;
	}
	public void setType(PizzaType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "{" + this.pizzaId + "; " + this.name + "; " + this.type + "; " + this.price + "}";
	}
}
