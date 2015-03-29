package ua.epam.rd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.epam.rd.repository.Order;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {
	
	private List<Order> orders = new ArrayList<Order>();
	
	@Override
	public void insert(Order order) {
		int maxId = -1;
		for (Order o : orders) {
			if (o.getId() > maxId) {
				maxId = o.getId();
			}
		}
		order.setId(maxId + 1);
		orders.add(order);
	}

	@Override
	public Order getById(int id) {
		Order res = null;
		
		for (Order o : orders) {
			if (o.getId() == id) {
				res = o;
				break;
			}
		}
		
		return res;
	}

	@Override
	public List<Order> getAll() {
		return orders;
	}

	@Override
	public void update(Order o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Order o) {
		// TODO Auto-generated method stub

	}

}
