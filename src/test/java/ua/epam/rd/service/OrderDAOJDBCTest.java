package ua.epam.rd.service;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.NoResultException;

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
import ua.epam.rd.domain.OrderInterface;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringConfigTest.xml")
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderDAOJDBCTest extends DAOTestsTemplate {
	
	@Autowired
    @Qualifier("orderServiceJDBC")
	private transient OrderService orderService;
	
	@Autowired
    @Qualifier("clientServiceJDBC")
    private transient ClientService clientService;
	
	@Autowired
    @Qualifier("pizzaServiceJDBC")
	private transient PizzaService pizzaService;
	
	@Test
	public void testReadAllOrders() {
		Assert.assertTrue(orderService.readAllOrders().size() == 3);
	}
	
	@Test
    public void testReadOrderByIdExisting() {
    	Assert.assertTrue(orderService.readOrderById(1).getName()
    			.contentEquals("Order1"));
    	Assert.assertTrue(orderService.readOrderById(2).getName()
    			.contentEquals("Order2"));
    	Assert.assertTrue(orderService.readOrderById(3).getName()
    			.contentEquals("Order3"));
    }
    
    @Test(expected = NoResultException.class)
    public void testReadOrderByIdNonExisting() {
    	 Assert.assertTrue(orderService.readOrderById(4) == null);
    }
    
    @Test
    public void testReadOrderByNameExisting() {
    	Assert.assertTrue(orderService.readOrderByName("Order1").getId() == 1);
    	Assert.assertTrue(orderService.readOrderByName("Order2").getId() == 2);
    	Assert.assertTrue(orderService.readOrderByName("Order3").getId() == 3);
    }
    
    @Test(expected = NoResultException.class)
    public void testReadOrderByNameNonExisting() {
    	Assert.assertTrue(orderService.readOrderByName("Order4") == null);
    }
    
    @Test
    public void testCreateOrderWithNameSet() {
    	OrderInterface createdOrder = orderService.createNewOrder();
    	createdOrder.setName("Order4");
    	createdOrder.setDate(new Date((long) (Calendar.getInstance().getTimeInMillis()*0.9)));
    	createdOrder.setClient(clientService.readClientById(1));
    	createdOrder.addPizza(pizzaService.readPizzaById(1));
    	orderService.createOrder(createdOrder);
    	Assert.assertTrue(orderService.readOrderByName("Order4").getId() == 4);
    }
    
    @Test
    public void testCreateOrderWithoutNameSet() {
    	OrderInterface createdOrder = orderService.createNewOrder();
    	createdOrder.setDate(new Date((long) (Calendar.getInstance().getTimeInMillis()*0.9)));
    	createdOrder.setClient(clientService.readClientById(1));
    	createdOrder.addPizza(pizzaService.readPizzaById(1));
    	orderService.createOrder(createdOrder);
    	Assert.assertTrue(orderService.readOrderByName(createdOrder.getDate().toString()).getId()
    			== 4);
    }
    
    @Test
    public void testUpdateOrderNameById() {
    	Assert.assertTrue(orderService.updateOrderNameById(
        		orderService.readOrderByName("Order1").getId(), "Order1_updated") == 1);
    }
}
