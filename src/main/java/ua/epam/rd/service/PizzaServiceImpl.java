package ua.epam.rd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.epam.rd.repository.Pizza;
import ua.epam.rd.repository.PizzaType;

public class PizzaServiceImpl implements PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Override
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.getAll();
	}

	@Override
	public List<Pizza> getPizzasByType(PizzaType type) {
		return pizzaRepository.getByType(type);
	}

}
