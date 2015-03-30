package ua.epam.rd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.rd.repository.OrderInterface;
import ua.epam.rd.repository.Pizza;

@Service("orderService")
public abstract class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<OrderInterface> getAllOrders() {
		return orderRepository.getAll();		
	}

	@Override
	public OrderInterface getOrderById(int id) {		
		return orderRepository.getById(id);
	}

	@Override
	public abstract OrderInterface createNewOrder();

	@Override
	public void placeOrder(OrderInterface order) {
		OrderInterface newOrder = createNewOrder();
		newOrder.setDate(order.getDate());
		if (order.getName().isEmpty()) {
			newOrder.setName(newOrder.getDate().toString());
		} else {
			newOrder.setName(order.getName());
		}
		for (Pizza p: order.getPizzas()) {
			newOrder.addPizza(p);
		}		
		orderRepository.insert(newOrder);
	}

}
