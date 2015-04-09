package ua.epam.rd.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.OrderInterface;
import ua.epam.rd.domain.PizzasInOrders;
import ua.epam.rd.repository.OrderRepository;


@Service("orderServiceJDBC")
public class OrderServiceImplJDBC implements OrderService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	@Qualifier("orderRepositoryJDBC")
	private OrderRepository orderRepository;
	
	OrderServiceImplJDBC() {
		
	}
	
	OrderServiceImplJDBC(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	boolean isWorkingDay() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        return dayOfWeek != DayOfWeek.SUNDAY;        
    }
	
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
	public OrderInterface createNewOrder() {
		return new Order();
	}
	
	@Transactional
	@Override
	public void createOrder(OrderInterface order) {		
		orderRepository.createOrder(order);
	}
	
	@Transactional
	@Override
	public void createOrderControlled(OrderInterface order) {
		if (!order.getStatus().getName().contentEquals("NEW"))
			throw new IllegalArgumentException();
		if (order.getPizzasInOrders().isEmpty())
			throw new IllegalArgumentException();
		if (!isWorkingDay())
			throw new IllegalArgumentException();
		
		orderRepository.createOrder(order);
	}
	
	@Transactional
	@Override
	public int updateOrderNameById(int id, String newName) {
		Query query = em.createNamedQuery("Order.updateOrderNameById")
				.setParameter("id", id).setParameter("newName", newName);
		return query.executeUpdate();		
	}
	
	@Transactional
	@Override
	public void deleteOrderByName(String orderName) {
		OrderInterface orderToBeDeleted = 
				em.createNamedQuery("Order.findByName", OrderInterface.class)
				.setParameter("orderName", orderName).getSingleResult();
		for (PizzasInOrders pio : orderToBeDeleted.getPizzasInOrders()) {
			pio.setOrder(null);
		}
		orderToBeDeleted.getPizzasInOrders().clear();
		em.remove(orderToBeDeleted);
	}
}
