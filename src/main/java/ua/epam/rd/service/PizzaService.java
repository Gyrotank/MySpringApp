package ua.epam.rd.service;

import java.util.List;
import ua.epam.rd.repository.*;

public interface PizzaService {
	
	List<Pizza> getAllPizzas();
	List<Pizza> getPizzasByType(PizzaType type);
}
