package ua.epam.rd;

//import org.springframework.core.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.epam.rd.repository.Order;
import ua.epam.rd.repository.Pizza;
import ua.epam.rd.repository.PizzaType;
import ua.epam.rd.service.OrderRepository;
import ua.epam.rd.service.OrderService;
import ua.epam.rd.service.PizzaRepository;
import ua.epam.rd.service.PizzaService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext appContext 
        	= new ClassPathXmlApplicationContext("SpringConfig.xml");
                
        ConfigurableApplicationContext appContextService 
        	= new ClassPathXmlApplicationContext(new String[]{"SpringConfigService.xml"}, appContext);
        
        //Viewable view = appContext.getBean("webView", Viewable.class);
        
        Application myWebApp = appContext.getBean("applicationWeb", Application.class);
        myWebApp.show();
        
        Application myDesktopApp = appContext.getBean("applicationDesktop", Application.class);
        myDesktopApp.show();
        
        Pizza p1 = appContext.getBean("pizzaDefaultMeat", Pizza.class);
        Pizza p2 = appContext.getBean("pizzaSea", Pizza.class);
        
        Order o1 = appContextService.getBean("orderBasic", Order.class);
        o1.addPizza(p1);
                
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(o1);
        
        System.out.println("====");
        
        PizzaRepository pr = (PizzaRepository)appContextService.getBean("pizzaRepository");
        PizzaService ps = (PizzaService)appContextService.getBean("pizzaService");
        
        //pr.insert(p1);
        //pr.insert(p2);
        
        List<Pizza> allPizzas = ps.getAllPizzas();
        for(Pizza p : allPizzas){
        	System.out.println(p);
        }
        
        System.out.println("====");
        
        List<Pizza> allMeatPizzas = ps.getPizzasByType(PizzaType.MEAT);
        for(Pizza p : allMeatPizzas){
        	System.out.println(p);
        }
        
        System.out.println("====");
        
        OrderRepository or = (OrderRepository)appContextService.getBean("orderRepository");
        OrderService os = (OrderService)appContextService.getBean("orderService");
        
        Order o2 = os.createNewOrder();
        o2.addPizza(p1);
        o2.addPizza(p2);
        o2.setDate(new Date((long) (Calendar.getInstance().getTimeInMillis()*0.9)));
        
        or.insert(o1);
        or.insert(o2);
        
        List<Order> allOrders = os.getAllOrders();
        for(Order o : allOrders) {
        	System.out.println(o);
        }
        
        System.out.println("====");
        
        System.out.println(os.getOrderById(0));
        
        System.out.println("====");
        
        //PizzaRepository pr = (PizzaRepository)appContextService.getBean("pizzaRepository");
        
        ((ConfigurableApplicationContext)appContext).close();
        appContextService.close();
    }
}
