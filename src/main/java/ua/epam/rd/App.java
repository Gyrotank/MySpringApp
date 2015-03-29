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
import ua.epam.rd.repository.Pizza;
import ua.epam.rd.repository.PizzaType;
import ua.epam.rd.service.OrderService;
import ua.epam.rd.service.PizzaService;

@Component
public class App 
{
	private static OrderService orderService;
	
	@Autowired
    public void setService(OrderService orderService){
        App.orderService = orderService;
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
        
        Order o1 = appContextService.getBean("orderBasic", Order.class);
        o1.addPizza(p1);
                
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(o1);
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("USING SERVICE WITH AUTOWIRED AND XML-CONFIGURED REPOSITORY - ALL PIZZAS");
        System.out.println("====");
        
        PizzaService ps = (PizzaService)appContextService.getBean("pizzaService");
        
        List<Pizza> allPizzas = ps.getAllPizzas();
        for(Pizza p : allPizzas){
        	System.out.println(p);
        }
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("USING SERVICE WITH AUTOWIRED AND XML-CONFIGURED REPOSITORY - GET PIZZAS BY TYPE - MEAT");
        System.out.println("====");
        
        List<Pizza> allMeatPizzas = ps.getPizzasByType(PizzaType.MEAT);
        for(Pizza p : allMeatPizzas){
        	System.out.println(p);
        }
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("USING ANNOTATED SERVICE WITH AUTOWIRED AND ANNOTATED REPOSITORY - ADDING OLD AND NEW ORDER");
        System.out.println("====");
        
        Order o2 = orderService.createNewOrder();
        o2.addPizza(p1);
        o2.addPizza(p2);
        o2.setDate(new Date((long) (Calendar.getInstance().getTimeInMillis()*0.9)));
        
        orderService.placeOrder(o1);
        orderService.placeOrder(o2);
        
        List<Order> allOrders = orderService.getAllOrders();
        for(Order o : allOrders) {
        	System.out.println(o);
        }
        System.out.println();
        
        
        System.out.println("====");
        System.out.println("TESTING ORDER REPOSITORY'S getOrderById() METHOD");
        System.out.println("====");
        
        System.out.println(orderService.getOrderById(0));
        System.out.println();
        
        
        System.out.println("====");
        
        ((ConfigurableApplicationContext)appContext).close();
        appContextService.close();
    }
}
