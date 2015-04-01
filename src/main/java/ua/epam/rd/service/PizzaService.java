package ua.epam.rd.service;

import java.util.List;

import ua.epam.rd.domain.*;

public interface PizzaService {
	
	List<Pizza> getAllPizzas();
	List<Pizza> getPizzasByType(PizzaType type);
	Pizza getPizzaById(int id);
}
