package ua.pizzadelivery.rd.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orderstatuses")
public class OrderStatus {
	
	@Id
	@GeneratedValue
	@Column(name = "order_statuses_id")
	private int id;
	
	@Column(name = "order_statuses_name")
	private String name;
	
	@OneToMany
	@JoinColumn(name = "orders_status_id")
	private List<Order> orders;
	
	public OrderStatus() {
		
	}
	
	public OrderStatus(String name) {
		this.name = name;
	}
		
	public int getId() {
		return id;
	}
	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(final List<Order> orders) {
		this.orders = orders;
	}
	
	
}
