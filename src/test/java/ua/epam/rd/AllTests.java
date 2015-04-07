package ua.epam.rd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.epam.rd.domain.ClientDAOJDBCTest;
import ua.epam.rd.domain.OrderDAOJDBCTest;
import ua.epam.rd.service.ClientServiceJDBCTest;
import ua.epam.rd.service.OrderServiceJDBCTest;
import ua.epam.rd.service.PizzaServiceJDBCTest;

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
