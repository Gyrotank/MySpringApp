package ua.pizzadelivery.rd.domain;

import java.util.List;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@NamedQueries({
	@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c"),
	@NamedQuery(name="Client.findByName", query="SELECT c FROM Client c "
			+ "WHERE c.name = :clientName"),
	@NamedQuery(name="Client.findOrdersForAClientByName", query="SELECT c.orders FROM Client c "
			+ "WHERE c.name = :clientName"),
	@NamedQuery(name="Client.updateClientNameById", query="UPDATE Client c SET name = :newName "
			+ "WHERE c.id = :id"),
	@NamedQuery(name="Client.deleteClientByName", query="DELETE FROM Client c "
			+ "WHERE c.name = :clientName")
})
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue
	@Column(name = "clients_id")
	private int id;
	
	@Column(name = "clients_name")
	private String name;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "client")
	private List<Order> orders;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	public Client() {
		name = "Anonymous";
	}
	
	public Client(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String res;
		
		res = "{" + id + "; " + name + "; ";
		if (address == null) {
			res += "ADDRESS NOT AVAILABLE; ";
		} else {
			res += address.toString() + "; ";
		}
		res += "[ ";
		if (orders == null) {
			res += "NO ORDERS";
		} else {
			for (Order o: orders) {
				res += o.getName() + "; " + o.getPrice() + "; \n"; 
			}
		}
		res += " ]";
		res += "}";
		
		return res;
	}
}
