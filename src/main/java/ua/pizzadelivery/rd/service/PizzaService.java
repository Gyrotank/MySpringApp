package ua.pizzadelivery.rd.service;

import java.util.List;

import ua.pizzadelivery.rd.domain.*;

public interface PizzaService {
	
	List<Pizza> readAllPizzas();
	List<Pizza> readPizzasByType(PizzaType type);
	Pizza readPizzaById(int id);
	void createPizza(String name, PizzaType type, Double price);
	int updatePizzaPriceByName(String name, Double newPrice);
	void deletePizzaByName(String pizzaName);
	List<PizzasInOrders> readAllPizzasInOrders();
}
