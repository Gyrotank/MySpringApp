package ua.epam.rd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;
import ua.epam.rd.repository.PizzaRepository;

@Service("pizzaService")
public class PizzaServiceImpl implements PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Override
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.getAll();
	}
	
	@Override
	public Pizza getPizzaById(int id) {
		return new Pizza();		
	}

	@Override
	public List<Pizza> getPizzasByType(PizzaType type) {
		return pizzaRepository.getByType(type);
	}

}
