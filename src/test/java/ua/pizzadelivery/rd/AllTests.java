package ua.pizzadelivery.rd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.pizzadelivery.rd.domain.ClientDAOJDBCTest;
import ua.pizzadelivery.rd.domain.OrderDAOJDBCTest;
import ua.pizzadelivery.rd.service.ClientServiceJDBCTest;
import ua.pizzadelivery.rd.service.OrderServiceJDBCTest;
import ua.pizzadelivery.rd.service.PizzaServiceJDBCTest;

@RunWith(Suite.class)
@SuiteClasses({
				ClientDAOJDBCTest.class,
				OrderDAOJDBCTest.class,
				ClientServiceJDBCTest.class,
				PizzaServiceJDBCTest.class,
				OrderServiceJDBCTest.class
			})

public class AllTests {

}
