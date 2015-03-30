package ua.epam.rd.service;

import java.util.List;

import ua.epam.rd.repository.OrderInterface;

public interface OrderRepository {
	
	public void insert(OrderInterface o);
	public OrderInterface getById(int id);
	public List<OrderInterface> getAll();
	public void update(OrderInterface o);
	public void delete(OrderInterface o);
	
}
