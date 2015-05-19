package ua.pizzadelivery.rd.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import ua.pizzadelivery.rd.domain.Address;
import ua.pizzadelivery.rd.domain.Client;
import ua.pizzadelivery.rd.domain.Order;
import ua.pizzadelivery.rd.domain.OrderStatus;
import ua.pizzadelivery.rd.domain.Pizza;
import ua.pizzadelivery.rd.domain.PizzaType;
import ua.pizzadelivery.rd.domain.PizzasInOrders;
import ua.pizzadelivery.rd.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringConfigTest.xml")
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderDAOJDBCTest extends DAOTestsTemplate {
	
	@Autowired
	@Qualifier("orderServiceJDBC")
	private transient OrderService orderService;
	
	@Test
	public void testSetGetId() {
		Order newOrder = new Order();
		newOrder.setId(35);
		Assert.assertTrue(newOrder.getId() == 35);
	}
	
	@Test
	public void testSetGetName() {
		Order newOrder = new Order();
		newOrder.setName("Dummy Order");
		Assert.assertTrue(newOrder.getName().contentEquals("Dummy Order"));
	}
	
	@Test
	public void testSetGetDate() {
		Order newOrder = new Order();
		java.sql.Date date = new Date(Calendar.getInstance().getTimeInMillis());
		newOrder.setDate(date);
		Assert.assertTrue(newOrder.getDate().equals(date));
	}
	
	@Test
	public void testSetGetPizzasInOrders() {
		Order newOrder = new Order();
		List<PizzasInOrders> pizzasInOrders = new ArrayList<PizzasInOrders>();
		pizzasInOrders.add(new PizzasInOrders(newOrder, new Pizza(), 0));
		newOrder.setPizzasInOrders(pizzasInOrders);
		Assert.assertTrue(newOrder.getPizzasInOrders().get(0).getPizzaInOrderQuantity() == 0);
	}
	
	@Test
	public void testAddPizzasInOrdersNull() {
		Order newOrder = new Order();
		newOrder.addPizzasInOrders(new Pizza(), 1);
		Assert.assertTrue(newOrder.getPizzasInOrders().get(0).getPizzaInOrderQuantity() == 1);
	}
	
	@Test
	public void testAddPizzasInOrdersEmpty() {
		Order newOrder = new Order();
		newOrder.setPizzasInOrders(new ArrayList<PizzasInOrders>());
		newOrder.addPizzasInOrders(new Pizza(), 1);
		Assert.assertTrue(newOrder.getPizzasInOrders().get(0).getPizzaInOrderQuantity() == 1);
	}
	
	@Test
	public void testAddPizzasInOrdersNotEmptySameNamePositiveQuantity() {
		Order newOrder = new Order();
		List<PizzasInOrders> pizzasInOrders = new ArrayList<PizzasInOrders>();
		pizzasInOrders.add(new PizzasInOrders(newOrder, new Pizza(), 1));
		newOrder.setPizzasInOrders(pizzasInOrders);
		newOrder.addPizzasInOrders(new Pizza(), 1);
		Assert.assertTrue(newOrder.getPizzasInOrders().size() == 1);
		Assert.assertTrue(newOrder.getPizzasInOrders().get(0).getPizzaInOrderQuantity() == 2);
	}
	
	@Test
	public void testAddPizzasInOrdersNotEmptySameNameNegativeQuantity() {
		Order newOrder = new Order();
		List<PizzasInOrders> pizzasInOrders = new ArrayList<PizzasInOrders>();
		pizzasInOrders.add(new PizzasInOrders(newOrder, new Pizza(), 1));
		newOrder.setPizzasInOrders(pizzasInOrders);
		newOrder.addPizzasInOrders(new Pizza(), -2);
		Assert.assertTrue(newOrder.getPizzasInOrders().size() == 1);
		Assert.assertTrue(newOrder.getPizzasInOrders().get(0).getPizzaInOrderQuantity() == 0);
	}
	
	@Test
	public void testAddPizzasInOrdersNotEmptyDifferentNames() {
		Order newOrder = new Order();
		List<PizzasInOrders> pizzasInOrders = new ArrayList<PizzasInOrders>();
		pizzasInOrders.add(new PizzasInOrders(
				newOrder, new Pizza("P1", 100.0, PizzaType.MEAT), 1));
		newOrder.setPizzasInOrders(pizzasInOrders);
		newOrder.addPizzasInOrders(new Pizza("P2", 100.0, PizzaType.MEAT), 1);
		Assert.assertTrue(newOrder.getPizzasInOrders().size() == 2);
		Assert.assertTrue(newOrder.getPizzasInOrders().get(1).getPizzaInOrderQuantity() == 1);
	}
	
	@Test
	public void testCalculatePrice() {
		Order newOrder = new Order();
		List<PizzasInOrders> pizzasInOrders = new ArrayList<PizzasInOrders>();
		pizzasInOrders.add(new PizzasInOrders(
				newOrder, new Pizza("P1", 100.0, PizzaType.MEAT), 1));
		newOrder.setPizzasInOrders(pizzasInOrders);
		newOrder.addPizzasInOrders(new Pizza("P2", 150.0, PizzaType.MEAT), 2);
		newOrder.calculatePrice();
		Assert.assertTrue(newOrder.getPrice().doubleValue() == 400.0);
	}
	
	@Test
	public void testSetGetStatus() {
		Order newOrder = new Order();
		newOrder.setStatus(new OrderStatus("STATUS"));
		Assert.assertTrue(newOrder.getStatus().getName().contentEquals("STATUS"));
	}
	
	@Test
	public void testSetGetClient() {
		Order newOrder = new Order();
		newOrder.setClient(new Client("CLIENT ONE", new Address()));
		Assert.assertTrue(newOrder.getClient().getName().contentEquals("CLIENT ONE"));
	}
	
	@Test
	public void testToStringNoClientNoPizzas() {
		Order newOrder = new Order();		
		String res = "{0; ; Anonymous; null; "
				+ new Date(Calendar.getInstance().getTimeInMillis())
				+ "; 0.0; [ NO PIZZAS ]}";
		Assert.assertTrue(newOrder.toString()
				.contentEquals(res));
	}
	
	@Test
	public void testToStringWithClientAndPizzas() {
		Order newOrder = new Order();
		newOrder.setClient(new Client("CLIENT ONE", new Address()));
		newOrder.addPizzasInOrders(new Pizza(), 1);
		String res = "{0; ; CLIENT ONE; null; "
				+ new Date(Calendar.getInstance().getTimeInMillis())
				+ "; 0.0; [ {null; Default Pizza; MEAT; 50.0; [ NO ORDERS ] ; 1; \n ]}";
		Assert.assertTrue(newOrder.toString().contentEquals(res));
	}
}
