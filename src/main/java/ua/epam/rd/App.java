package ua.epam.rd;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import ua.epam.rd.repository.Order;
import ua.epam.rd.repository.OrderAnnotated;
import ua.epam.rd.repository.OrderInterface;
import ua.epam.rd.repository.Pizza;
import ua.epam.rd.repository.PizzaType;
import ua.epam.rd.service.OrderService;
import ua.epam.rd.service.PizzaService;

@Component
public class App 
{
	private static OrderService orderService;
	private static PizzaService pizzaService;
	
	@Autowired
    public void setOrderService(OrderService orderService){
        App.orderService = orderService;
    }
	
	@Autowired
    public void setPizzaService(PizzaService pizzaService){
        App.pizzaService = pizzaService;
    }
	
    public static void main( String[] args )
    {
        ApplicationContext appContext 
        	= new ClassPathXmlApplicationContext("SpringConfig.xml");
                
        ConfigurableApplicationContext appContextService 
        	= new ClassPathXmlApplicationContext(new String[]{"SpringConfigService.xml"}, appContext);
        
        
        System.out.println("====");
        System.out.println("SIMPLE BEAN INSTANTIATION");
        System.out.println("====");
        
        Pizza p1 = appContext.getBean("pizzaDefaultMeat", Pizza.class);
        Pizza p2 = appContext.getBean("pizzaSea", Pizza.class);
        
        OrderInterface o1 = appContextService.getBean("orderBasic", Order.class);
        o1.addPizza(p1);
                
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(o1);
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("USING SERVICE WITH AUTOWIRED AND XML-CONFIGURED REPOSITORY - ALL PIZZAS");
        System.out.println("====");
        
        List<Pizza> allPizzas = pizzaService.getAllPizzas();
        for(Pizza p : allPizzas){
        	System.out.println(p);
        }
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("USING SERVICE WITH AUTOWIRED AND XML-CONFIGURED REPOSITORY - GET PIZZAS BY TYPE - MEAT");
        System.out.println("====");
        
        List<Pizza> allMeatPizzas = pizzaService.getPizzasByType(PizzaType.MEAT);
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
        
        orderService.placeOrder(o1);
        orderService.placeOrder(o2);
        
        List<OrderInterface> allOrders = orderService.getAllOrders();
        for(OrderInterface o : allOrders) {
        	System.out.println(o);
        }
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("TESTING ORDER REPOSITORY'S getOrderById() METHOD");
        System.out.println("====");
        
        System.out.println(orderService.getOrderById(0));
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("TESTING ORDER ANNOTATION");
        System.out.println("====");
        
        OrderInterface o3 = new OrderAnnotated();
        o3.addPizza(p2);
        o3.setDate(new Date((long) (Calendar.getInstance().getTimeInMillis()*1.1)));
        orderService.placeOrder(o3);
        
        allOrders = orderService.getAllOrders();
        for(OrderInterface o : allOrders) {
        	System.out.println(o);
        }
                
        System.out.println();
        
        
        System.out.println("====");
        
        
        ((ConfigurableApplicationContext)appContext).close();
        appContextService.close();
    }
}