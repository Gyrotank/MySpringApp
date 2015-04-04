package ua.epam.rd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.epam.rd.service.ClientDAOJDBCTest;
import ua.epam.rd.service.OrderDAOJDBCTest;
import ua.epam.rd.service.PizzaDAOJDBCTest;

@RunWith(Suite.class)
@SuiteClasses({
				ClientDAOJDBCTest.class,
				PizzaDAOJDBCTest.class,
				OrderDAOJDBCTest.class
			})

public class AllTests {

}
