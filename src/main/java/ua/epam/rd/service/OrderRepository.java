package ua.epam.rd.service;

import java.util.List;

import ua.epam.rd.repository.Order;

public interface OrderRepository {
	
	public void insert(Order o);
	public Order getById(int id);
	public List<Order> getAll();
	public void update(Order o);
	public void delete(Order o);
	
}
