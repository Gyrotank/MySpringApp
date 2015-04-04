package ua.epam.rd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.rd.domain.OrderInterface;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.repository.OrderRepository;

@Service("orderService")
public abstract class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<OrderInterface> readAllOrders() {
		return orderRepository.getAll();		
	}

	@Override
	public OrderInterface readOrderById(int id) {		
		return orderRepository.getById(id);
	}

	@Override
	public abstract OrderInterface createNewOrder();

	@Override
	public void createOrder(OrderInterface order) {
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
