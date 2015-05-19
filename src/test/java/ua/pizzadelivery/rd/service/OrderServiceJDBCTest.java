package ua.pizzadelivery.rd.service;

import static org.mockito.Mockito.*;

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

import ua.pizzadelivery.rd.DAOTestsTemplate;
import ua.pizzadelivery.rd.domain.OrderInterface;
import ua.pizzadelivery.rd.domain.OrderStatus;
import ua.pizzadelivery.rd.domain.Pizza;
import ua.pizzadelivery.rd.repository.OrderRepository;
import ua.pizzadelivery.rd.service.ClientService;
import ua.pizzadelivery.rd.service.OrderService;
import ua.pizzadelivery.rd.service.OrderServiceImplJDBC;
import ua.pizzadelivery.rd.service.PizzaService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringConfigTest.xml")
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderServiceJDBCTest extends DAOTestsTemplate {
	
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
    
    @Test
    public void testCreateOrderControlledNoProblems() {
    	OrderRepository mockOrderRepository = mock(OrderRepository.class);
    	
    	OrderServiceImplJDBC osi = new OrderServiceImplJDBC(mockOrderRepository);
    	OrderServiceImplJDBC spyOsi = spy(osi);
    	doReturn(true).when(spyOsi).isWorkingDay();
    	
    	OrderInterface createdOrder = orderService.createNewOrder();
    	createdOrder.setStatus(new OrderStatus("NEW"));
    	createdOrder.addPizza(new Pizza());
    	
    	when(mockOrderRepository.createOrder(createdOrder)).thenReturn(Boolean.TRUE);
    	
    	spyOsi.createOrderControlled(createdOrder);
    	
    	verify(mockOrderRepository).createOrder(createdOrder);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrderControlledNotWorkingDay() {
    	OrderRepository mockOrderRepository = mock(OrderRepository.class);
    	
    	OrderServiceImplJDBC osi = new OrderServiceImplJDBC(mockOrderRepository);
    	OrderServiceImplJDBC spyOsi = spy(osi);
    	doReturn(false).when(spyOsi).isWorkingDay();
    	
    	OrderInterface createdOrder = orderService.createNewOrder();
    	createdOrder.setStatus(new OrderStatus("NEW"));
    	createdOrder.addPizza(new Pizza());
    	
    	when(mockOrderRepository.createOrder(createdOrder)).thenReturn(Boolean.TRUE);
    	
    	spyOsi.createOrderControlled(createdOrder);
    	
    	verify(mockOrderRepository).createOrder(createdOrder);
    }
}
