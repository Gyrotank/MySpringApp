package ua.epam.rd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.epam.rd.repository.Pizza;
import ua.epam.rd.repository.PizzaType;

@Service("pizzaServiceJDBC")
public class PizzaServiceImplJDBC implements PizzaService {

	@Override
	public List<Pizza> getAllPizzas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pizza> getPizzasByType(PizzaType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
