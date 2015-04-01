package ua.epam.rd.repository;

import java.util.List;

import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;

public interface PizzaRepository {
	
	public void insert(Pizza p);
	public List<Pizza> getByType(PizzaType pizzaType);
	public List<Pizza> getAll();
	public void update(Pizza p);
	public void delete(Pizza p);
	
}
