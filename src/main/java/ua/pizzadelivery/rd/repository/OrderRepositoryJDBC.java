package ua.pizzadelivery.rd.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.pizzadelivery.rd.domain.Order;
import ua.pizzadelivery.rd.domain.OrderInterface;
import ua.pizzadelivery.rd.domain.OrderStatus;
import ua.pizzadelivery.rd.domain.PizzasInOrders;

@Repository("orderRepositoryJDBC")
public class OrderRepositoryJDBC implements OrderRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void insert(OrderInterface o) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderInterface getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderInterface> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(OrderInterface o) {
		// TODO Auto-generated method stub

	}
	
	@Transactional
	@Override
	public void delete(OrderInterface o) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean createOrder(OrderInterface order) {
		OrderInterface newOrder = new Order();
		newOrder.setDate(order.getDate());
		if (order.getName().isEmpty()) {
			newOrder.setName(newOrder.getDate().toString());
		} else {
			newOrder.setName(order.getName());
		}
		for (PizzasInOrders pio: order.getPizzasInOrders()) {
			newOrder.addPizzasInOrders(pio.getPizza(), pio.getPizzaInOrderQuantity());
		}
		newOrder.setStatus(em.find(OrderStatus.class, 1));
		newOrder.setClient(order.getClient());
		em.persist(newOrder);
		return true;
	}

}
