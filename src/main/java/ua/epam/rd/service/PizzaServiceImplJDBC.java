package ua.epam.rd.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;

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

}
