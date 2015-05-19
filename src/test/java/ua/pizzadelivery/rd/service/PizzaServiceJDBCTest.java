package ua.pizzadelivery.rd.service;

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

import ua.pizzadelivery.rd.DAOTestsTemplate;
import ua.pizzadelivery.rd.domain.PizzaType;
import ua.pizzadelivery.rd.service.OrderService;
import ua.pizzadelivery.rd.service.PizzaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringConfigTest.xml")
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PizzaServiceJDBCTest extends DAOTestsTemplate{
	
	@Autowired
    @Qualifier("pizzaServiceJDBC")
	private transient PizzaService pizzaService;
	
	@Autowired
    @Qualifier("orderServiceJDBC")
	private transient OrderService orderService;
	
	@Test
	public void testReadAllPizzas() {
		Assert.assertTrue(pizzaService.readAllPizzas().size() == 2);
	}
	
	@Test
    public void testReadPizzaByIdExisting() {
    	Assert.assertTrue(pizzaService.readPizzaById(1).getName()
    			.contentEquals("Big Meat Pizza"));
    	Assert.assertTrue(pizzaService.readPizzaById(2).getName()
    			.contentEquals("Big Sea Pizza"));    	
    }
    
    @Test
    public void testReadPizzaByIdNonExisting() {
    	 Assert.assertTrue(pizzaService.readPizzaById(3) == null);
    }
    
    @Test
    public void testReadPizzaByTypeExisting() {
    	Assert.assertTrue(pizzaService.readPizzasByType(PizzaType.MEAT).get(0)
    			.getName().contentEquals("Big Meat Pizza"));
    	Assert.assertTrue(pizzaService.readPizzasByType(PizzaType.SEA).get(0)
    			.getName().contentEquals("Big Sea Pizza"));
    	
    }
    
    @Test
    public void testReadPizzaByTypeNonExisting() {
    	Assert.assertTrue(pizzaService.readPizzasByType(PizzaType.VEGETARIAN).isEmpty());
    }
    
    @Test
    public void testCreatePizza() {
    	Assert.assertTrue(pizzaService.readAllPizzas().size() == 2);
    	pizzaService.createPizza("New Vegetarian Pizza", PizzaType.VEGETARIAN, 600.0);
    	Assert.assertTrue(pizzaService.readAllPizzas().size() == 3);
    	Assert.assertTrue(pizzaService.readPizzaById(3).getName()
    			.contentEquals("New Vegetarian Pizza"));
    }
    
    @Test
    public void testUpdatePizzaPriceByName() {
    	Assert.assertTrue(pizzaService.updatePizzaPriceByName("Big Meat Pizza", 1000.0) == 1);
    }
    
    @Test
    public void testDeletePizzaByName() {
    	Assert.assertTrue(pizzaService.readAllPizzas().size() == 2);
    	pizzaService.deletePizzaByName("Big Meat Pizza");
    	Assert.assertTrue(pizzaService.readAllPizzas().size() == 1);
    }
    
    @Test
    public void testReadAllPizzasInOrders() {
    	Assert.assertTrue(pizzaService.readAllPizzasInOrders().size() == 4);
    }
    
    @Test
    public void testReadAllPizzasInOrdersAfterOrderDelete() {
    	orderService.deleteOrderByName("Order1");
    	Assert.assertTrue(pizzaService.readAllPizzasInOrders().size() == 4);
    }
}
