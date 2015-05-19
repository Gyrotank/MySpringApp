package ua.pizzadelivery.rd.repository;

import java.util.List;

import ua.pizzadelivery.rd.domain.OrderInterface;

public interface OrderRepository {
	
	public void insert(OrderInterface o);
	public OrderInterface getById(int id);
	public List<OrderInterface> getAll();
	public Boolean createOrder(OrderInterface o);
	public void update(OrderInterface o);
	public void delete(OrderInterface o);
	
}
