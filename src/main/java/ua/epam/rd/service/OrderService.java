package ua.epam.rd.service;

import java.util.List;

import ua.epam.rd.domain.*;

public interface OrderService {
	
	List<OrderInterface> readAllOrders();
	OrderInterface readOrderByName(String name);
	OrderInterface readOrderById(int id);
	OrderInterface createNewOrder();
	void createOrder(OrderInterface order);
	int updateOrderNameById(int id, String newName);
	void deleteOrderByName(String orderName);
}
