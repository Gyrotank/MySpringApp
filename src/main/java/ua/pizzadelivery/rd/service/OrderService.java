package ua.pizzadelivery.rd.service;

import java.util.List;

import ua.pizzadelivery.rd.domain.*;

public interface OrderService {
	
	List<OrderInterface> readAllOrders();
	OrderInterface readOrderByName(String name);
	OrderInterface readOrderById(int id);
	OrderInterface createNewOrder();
	void createOrder(OrderInterface order);
	void createOrderControlled(OrderInterface order);
	int updateOrderNameById(int id, String newName);
	void deleteOrderByName(String orderName);	
}
