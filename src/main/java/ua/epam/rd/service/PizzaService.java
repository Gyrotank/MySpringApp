package ua.epam.rd.service;

import java.util.List;

import ua.epam.rd.domain.*;

public interface PizzaService {
	
	List<Pizza> readAllPizzas();
	List<Pizza> readPizzasByType(PizzaType type);
	Pizza readPizzaById(int id);
}
