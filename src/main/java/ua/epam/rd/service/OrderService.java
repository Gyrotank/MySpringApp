package ua.epam.rd.service;

import java.util.List;
import ua.epam.rd.repository.*;

public interface OrderService {
	
	List<Order> getAllOrders();
	Order getOrderById(int id);
	Order createNewOrder(); // Order should be a prototype bean
	void placeOrder(Order order);
}
