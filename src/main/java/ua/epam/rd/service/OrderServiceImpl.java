package ua.epam.rd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.rd.repository.Order;
import ua.epam.rd.repository.Pizza;

@Service("orderService")
public abstract class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.getAll();		
	}

	@Override
	public Order getOrderById(int id) {		
		return orderRepository.getById(id);
	}

	@Override
	public abstract Order createNewOrder();

	@Override
	public void placeOrder(Order order) {
		Order newOrder = createNewOrder();
		newOrder.setDate(order.getDate());
		for (Pizza p: order.getPizzas()) {
			newOrder.addPizza(p);
		}		
		orderRepository.insert(order);
	}

}
