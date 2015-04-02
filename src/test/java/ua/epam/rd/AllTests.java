package ua.epam.rd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.epam.rd.service.ClientDAOJDBCTest;

@RunWith(Suite.class)
@SuiteClasses({
				ClientDAOJDBCTest.class
			})

public class AllTests {

}
