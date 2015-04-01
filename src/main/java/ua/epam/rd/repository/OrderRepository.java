package ua.epam.rd.repository;

import java.util.List;

import ua.epam.rd.domain.OrderInterface;

public interface OrderRepository {
	
	public void insert(OrderInterface o);
	public OrderInterface getById(int id);
	public List<OrderInterface> getAll();
	public void update(OrderInterface o);
	public void delete(OrderInterface o);
	
}
