package ua.epam.rd.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.epam.rd.domain.OrderInterface;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {
	
	private List<OrderInterface> orders = new ArrayList<OrderInterface>();
	
	@Override
	public void insert(OrderInterface order) {
		int maxId = -1;
		for (OrderInterface o : orders) {
			if (o.getId() > maxId) {
				maxId = o.getId();
			}
		}
		order.setId(maxId + 1);
		order.setName("" + order.getId() + "-" + order.getName());
		orders.add(order);
	}

	@Override
	public OrderInterface getById(int id) {
		OrderInterface res = null;
		
		for (OrderInterface o : orders) {
			if (o.getId() == id) {
				res = o;
				break;
			}
		}
		
		return res;
	}

	@Override
	public List<OrderInterface> getAll() {
		return orders;
	}

	@Override
	public void update(OrderInterface o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(OrderInterface o) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean createOrder(OrderInterface o) {
		return false;
		// TODO Auto-generated method stub
		
	}

}
