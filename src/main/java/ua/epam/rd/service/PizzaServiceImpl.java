package ua.epam.rd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;
import ua.epam.rd.domain.PizzasInOrders;
import ua.epam.rd.repository.PizzaRepository;

@Service("pizzaService")
public class PizzaServiceImpl implements PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Override
	public List<Pizza> readAllPizzas() {
		return pizzaRepository.getAll();
	}
	
	@Override
	public Pizza readPizzaById(int id) {
		return new Pizza();		
	}

	@Override
	public List<Pizza> readPizzasByType(PizzaType type) {
		return pizzaRepository.getByType(type);
	}

	@Override
	public void createPizza(String name, PizzaType type, Double price) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updatePizzaPriceByName(String name, Double newPrice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deletePizzaByName(String pizzaName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PizzasInOrders> readAllPizzasInOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
