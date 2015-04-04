package ua.epam.rd.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.epam.rd.domain.OrderInterface;
import ua.epam.rd.domain.OrderStatus;
import ua.epam.rd.domain.PizzasInOrders;


@Service("orderServiceJDBC")
public abstract class OrderServiceImplJDBC implements OrderService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Override
	public List<OrderInterface> readAllOrders() {
		List<OrderInterface> result = em.createNamedQuery("Order.findAll", OrderInterface.class).getResultList();
		return result;
	}
	
	@Transactional
	@Override
	public OrderInterface readOrderById(int id) {
		OrderInterface result = em.createNamedQuery("Order.findById", OrderInterface.class)
				.setParameter("orderId", id).getSingleResult();
		return result;
	}
	
	@Transactional
	public OrderInterface readOrderByName(String name) {
		OrderInterface result = em.createNamedQuery("Order.findByName", OrderInterface.class)
				.setParameter("orderName", name).getSingleResult();
		return result;
	}
	
	@Override
	public abstract OrderInterface createNewOrder();
	
	@Transactional
	@Override
	public void createOrder(OrderInterface order) {
		OrderInterface newOrder = createNewOrder();
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
	}
}
