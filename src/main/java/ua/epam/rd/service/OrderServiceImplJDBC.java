package ua.epam.rd.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.epam.rd.repository.OrderInterface;


@Service("orderServiceJDBC")
public class OrderServiceImplJDBC implements OrderService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Override
	public List<OrderInterface> getAllOrders() {
		List<OrderInterface> result = em.createNamedQuery("Order.findAll", OrderInterface.class).getResultList();
		return result;
	}
	
	@Transactional
	@Override
	public OrderInterface getOrderById(int id) {
		em.find(OrderInterface.class, id);
		return null;
	}
	
	@Transactional
	public OrderInterface getOrderByName(String name) {
		OrderInterface result = em.createNamedQuery("Order.findByName", OrderInterface.class).getSingleResult();
		return result;
	}
	
	@Transactional
	@Override
	public OrderInterface createNewOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public void placeOrder(OrderInterface order) {
		// TODO Auto-generated method stub

	}

}
