package ua.epam.rd.service;

import java.util.List;

import ua.epam.rd.repository.*;

public interface OrderService {
	
	List<OrderInterface> getAllOrders();
	OrderInterface getOrderById(int id);
	OrderInterface createNewOrder();
	void placeOrder(OrderInterface order);
}