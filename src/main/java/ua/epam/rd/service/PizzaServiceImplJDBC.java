package ua.epam.rd.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.epam.rd.repository.Pizza;
import ua.epam.rd.repository.PizzaType;

@Service("pizzaServiceJDBC")
public class PizzaServiceImplJDBC implements PizzaService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Override
	public List<Pizza> getAllPizzas() {
		List<Pizza> result = em.createNamedQuery("Pizza.findAll", Pizza.class).getResultList();
		return result;
	}
	
	@Transactional
	@Override
	public Pizza getPizzaById(int id) {
		return em.find(Pizza.class, id);		
	}
	
	@Transactional
	@Override
	public List<Pizza> getPizzasByType(PizzaType type) {
		List<Pizza> result = em.createNamedQuery("Pizza.findAllByType", Pizza.class)
				.setParameter("pizzaType", type)
				.getResultList();
		return result;
	}

}
