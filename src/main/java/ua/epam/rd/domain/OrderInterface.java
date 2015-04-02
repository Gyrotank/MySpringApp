package ua.epam.rd.domain;

import java.sql.Date;
import java.util.List;

public interface OrderInterface {
	public int getId();	
	public void setId(int id);
	
	public Date getDate();
	public void setDate(Date date);
	
	public String getName();
	public void setName(String name);
	
	public List<Pizza> getPizzas();
	public void addPizza(Pizza p);
	
	public Double getPrice();
	
	public OrderStatus getStatus();
	public void setStatus(OrderStatus orderStatus);
	
	public Client getClient();
	public void setClient(Client client);
	
	public List<PizzasInOrders> getPizzasInOrders();
	public void addPizzasInOrders(Pizza p, int quantity);
}
