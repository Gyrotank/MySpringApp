package ua.epam.rd.repository;

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
}
