package ua.pizzadelivery.rd;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import ua.pizzadelivery.rd.domain.Address;
import ua.pizzadelivery.rd.domain.Client;
import ua.pizzadelivery.rd.domain.Order;
import ua.pizzadelivery.rd.domain.OrderAnnotated;
import ua.pizzadelivery.rd.domain.OrderAnnotationHandler;
import ua.pizzadelivery.rd.domain.OrderInterface;
import ua.pizzadelivery.rd.domain.Pizza;
import ua.pizzadelivery.rd.domain.PizzaType;
import ua.pizzadelivery.rd.domain.PizzasInOrders;
import ua.pizzadelivery.rd.service.ClientService;
import ua.pizzadelivery.rd.service.OrderService;
import ua.pizzadelivery.rd.service.PizzaService;

@Component
public class App 
{
	private static OrderService orderService;
	private static OrderService orderServiceJDBC;
	private static PizzaService pizzaService;
	private static ClientService clientService;
	
	@Autowired
    public void setOrderService(OrderService orderService){
        App.orderService = orderService;
    }
	
	@Autowired
    public void setOrderServiceJDBC(OrderService orderServiceJDBC){
        App.orderServiceJDBC = orderServiceJDBC;
    }
	
	@Autowired
	@Qualifier("pizzaServiceJDBC")
    public void setPizzaService(PizzaService pizzaService){
        App.pizzaService = pizzaService;
    }
	
	@Autowired
	@Qualifier("clientServiceJDBC")
    public void setClientService(ClientService clientService){
        App.clientService = clientService;
    }
	
    public static void main( String[] args )
    {
        ApplicationContext appContext 
        	= new ClassPathXmlApplicationContext("SpringConfig.xml");
                
        ConfigurableApplicationContext appContextService 
        	= new ClassPathXmlApplicationContext(new String[]{"SpringConfigService.xml"}, appContext);
        
        OrderAnnotationHandler orderAnnotationHandler
        	= (OrderAnnotationHandler) appContextService.getBean("orderAnnotationHandler");
        orderAnnotationHandler.setApplicationContext(appContextService);
        
        System.out.println();
        System.out.println("====");
        System.out.println("SIMPLE BEAN INSTANTIATION");
        System.out.println("====");
        
        Pizza p1 = appContext.getBean("pizzaDefaultMeat", Pizza.class);
        Pizza p2 = appContext.getBean("pizzaSea", Pizza.class);
        
        OrderInterface o1 = appContextService.getBean("orderBasic", Order.class);
        o1.addPizza(p1);
        o1.setClient(clientService.readClientById(1));
                
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(o1);
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("USING SERVICE WITH AUTOWIRED AND XML-CONFIGURED REPOSITORY - ALL PIZZAS");
        System.out.println("====");
        
        List<Pizza> allPizzas = pizzaService.readAllPizzas();
        for(Pizza p : allPizzas){
        	System.out.println(p);
        }
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("USING SERVICE WITH AUTOWIRED AND XML-CONFIGURED REPOSITORY - GET PIZZAS BY TYPE - MEAT");
        System.out.println("====");
        
        List<Pizza> allMeatPizzas = pizzaService.readPizzasByType(PizzaType.MEAT);
        for(Pizza p : allMeatPizzas){
        	System.out.println(p);
        }
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("USING ANNOTATED SERVICE WITH AUTOWIRED AND ANNOTATED REPOSITORY - ADDING OLD AND NEW ORDER");
        System.out.println("====");
        
        OrderInterface o2 = orderService.createNewOrder();
        o2.addPizza(p1);
        o2.addPizza(p2);
        o2.setDate(new Date((long) (Calendar.getInstance().getTimeInMillis()*0.9)));
        
        orderService.createOrder(o1);
        orderService.createOrder(o2);
        
        List<OrderInterface> allOrders = orderService.readAllOrders();
        for(OrderInterface o : allOrders) {
        	System.out.println(o);
        }
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("TESTING ORDER REPOSITORY'S getOrderById() METHOD");
        System.out.println("====");
        
        System.out.println(orderService.readOrderById(0));
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("TESTING ORDER ANNOTATION");
        System.out.println("====");
        
        OrderInterface o3 = new OrderAnnotated();
        o3.addPizza(p2);
        o3.setDate(new Date((long) (Calendar.getInstance().getTimeInMillis()*1.1)));
        orderService.createOrder(o3);
        
        allOrders = orderService.readAllOrders();
        for(OrderInterface o : allOrders) {
        	System.out.println(o);
        }
                
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("TESTING ORDER JDBC ANNOTATION");
        System.out.println("====");
        
        allOrders = orderServiceJDBC.readAllOrders();
        for(OrderInterface o : allOrders) {
        	System.out.println(o);
        }
        
        OrderInterface orderToAdd = new Order();
        orderToAdd.setDate(new Date((long) (Calendar.getInstance().getTimeInMillis())));
        orderToAdd.setName("Added Order");
        orderToAdd.addPizza(pizzaService.readPizzaById(2));
        orderToAdd.addPizza(pizzaService.readPizzaById(1));
        orderToAdd.setClient(clientService.readClientByName("Petro Petrov"));
        orderServiceJDBC.createOrder(orderToAdd);
        
        System.out.println("====");
        
        allOrders = orderServiceJDBC.readAllOrders();
        for(OrderInterface o : allOrders) {
        	System.out.println(o);
        }
        
        System.out.println("====");
        
        allPizzas = pizzaService.readAllPizzas();
        for(Pizza p : allPizzas) {
        	System.out.println(p);
        }
        
        System.out.println("====");
        
        List<Client> allClients = clientService.readAllClients();
        for(Client c : allClients) {
        	System.out.println(c);
        }
        
        System.out.println("====");
        
        List<Order> allMyOrders = clientService.readOrdersForAClientByName("Ivan Ivanov");
        for(Order o : allMyOrders) {
        	System.out.println(o);
        }
        
        System.out.println("====");
        
        clientService.createClient("John Doe", 
        		new Address("001", "Acapulco", "Main str.", "45", "2-43"));
        allClients = clientService.readAllClients();
        for(Client c : allClients) {
        	System.out.println(c.getAddress().getClient());
        }
        
        System.out.println("====");
        
        clientService.updateClientNameById(
        		clientService.readClientByName("John Doe").getId(), "AB");
        allClients = clientService.readAllClients();
        for(Client c : allClients) {
        	System.out.println(c.getAddress().getClient());
        }
        
        System.out.println("====");
        
        clientService.deleteClientByName("Ivan Ivanov");
        allClients = clientService.readAllClients();
        for(Client c : allClients) {
        	System.out.println(c.getAddress().getClient());
        }
        
        System.out.println("====");
        
        orderServiceJDBC.updateOrderNameById(1, "Order1_updated");
        allOrders = orderServiceJDBC.readAllOrders();
        for(OrderInterface o : allOrders) {
        	System.out.println(o);
        }
        
        System.out.println("====");
        
        List<Address> allAddresses = clientService.readAllAddresses();
        for(Address a : allAddresses) {
        	System.out.println(a);
        }
        
        System.out.println("====");
        
        pizzaService.deletePizzaByName("Big Meat Pizza");
        List<PizzasInOrders> allPizzasInOrders = pizzaService.readAllPizzasInOrders();
        for(PizzasInOrders pio : allPizzasInOrders) {
        	System.out.println(pio);
        }
        allOrders = orderServiceJDBC.readAllOrders();
        for(OrderInterface o : allOrders) {
        	System.out.println(o);
        }
        
        System.out.println("====");
        orderServiceJDBC.deleteOrderByName("Order1_updated");
        allOrders = orderServiceJDBC.readAllOrders();
        for(OrderInterface o : allOrders) {
        	System.out.println(o);
        }
        allPizzasInOrders = pizzaService.readAllPizzasInOrders();
        for(PizzasInOrders pio : allPizzasInOrders) {
        	System.out.println(pio);
        }
        
        Client c = new Client(); 
        System.out.println(c);
        c.setAddress(new Address());
        List<Order> newOrders = new ArrayList<Order>();
        newOrders.add(new Order());
        c.setOrders(newOrders);
        System.out.println(c);
        Order o = new Order();
        System.out.println(o);
        o.setClient(new Client("CLIENT ONE", new Address()));
        o.addPizzasInOrders(new Pizza(), 1);
        System.out.println(o);
        
        ((ConfigurableApplicationContext)appContext).close();
        appContextService.close();
    }
}
