package ua.epam.rd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.epam.rd.repository.Pizza;
import ua.epam.rd.repository.PizzaType;

@Repository("pizzaRepository")
public class PizzaRepositoryImpl implements PizzaRepository {
	
	private List<Pizza> pizzas = new ArrayList<Pizza>();
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	@Override
	public void insert(Pizza p) {
		pizzas.add(p);
	}

	@Override
	public List<Pizza> getByType(PizzaType pizzaType) {
		List<Pizza> res = new ArrayList<Pizza>();
		
		for (Pizza p : pizzas) {
			if (p.getType().compareTo(pizzaType) == 0) {
				res.add(p);				
			}
		}
		
		return res;
	}

	@Override
	public List<Pizza> getAll() {
		return pizzas;
	}

	@Override
	public void update(Pizza p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Pizza p) {
		// TODO Auto-generated method stub

	}

}
