package ua.epam.rd.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ua.epam.rd.DAOTestsTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringConfigTest.xml")
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PizzaDAOJDBCTest extends DAOTestsTemplate{
	
	@Autowired
    @Qualifier("pizzaServiceJDBC")
	private transient PizzaService pizzaService;
	
	@Test
	public void testReadAllPizzas() {
		Assert.assertTrue(pizzaService.readAllPizzas().size() == 2);
	}
}
