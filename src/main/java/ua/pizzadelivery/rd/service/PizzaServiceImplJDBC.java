package ua.pizzadelivery.rd.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pizzadelivery.rd.domain.Pizza;
import ua.pizzadelivery.rd.domain.PizzaType;
import ua.pizzadelivery.rd.domain.PizzasInOrders;

@Service("pizzaServiceJDBC")
public class PizzaServiceImplJDBC implements PizzaService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Override
	public List<Pizza> readAllPizzas() {
		List<Pizza> result = em.createNamedQuery("Pizza.readAll", Pizza.class).getResultList();
		return result;
	}
	
	@Transactional
	@Override
	public Pizza readPizzaById(int id) {
		return em.find(Pizza.class, id);		
	}
	
	@Transactional
	@Override
	public List<Pizza> readPizzasByType(PizzaType type) {
		List<Pizza> result = em.createNamedQuery("Pizza.readAllByType", Pizza.class)
				.setParameter("pizzaType", type)
				.getResultList();
		return result;
	}
	
	@Transactional
	@Override
	public void createPizza(String name, PizzaType type, Double price) {
		Pizza createdPizza = new Pizza(name, price, type);
		em.persist(createdPizza);		
	}
	
	@Transactional
	@Override
	public int updatePizzaPriceByName(String name, Double newPrice) {
		Query query = em.createNamedQuery("Pizza.updatePizzaPriceByName")
				.setParameter("pizzaName", name).setParameter("newPrice", newPrice);
		return query.executeUpdate();
	}
	
	@Transactional
	@Override
	public void deletePizzaByName(String pizzaName) {
		Pizza pizzaToBeDeleted = em.createNamedQuery("Pizza.findByName",Pizza.class)
				.setParameter("pizzaName", pizzaName).getSingleResult();
		for (PizzasInOrders pio : pizzaToBeDeleted.getPizzasInOrders()) {
			pio.setPizza(null);
		}
		pizzaToBeDeleted.getPizzasInOrders().clear();
		em.remove(pizzaToBeDeleted);
	}
	
	@Transactional
	@Override
	public List<PizzasInOrders> readAllPizzasInOrders() {
		List<PizzasInOrders> result = 
				em.createNamedQuery("PizzasInOrders.findAll", PizzasInOrders.class)
				.getResultList();
		return result;
	}
}
