package ua.epam.rd;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public abstract class DAOTestsTemplate {

    @Autowired
    protected JdbcTemplate jdbcTemplate; 
    
    @Before
    public void prepareDB(){
    	
    	jdbcTemplate.execute("TRUNCATE TABLE Pizzasinorders");
        jdbcTemplate.execute("ALTER TABLE Pizzasinorders "
        		+ "ALTER COLUMN pizzas_in_orders_id RESTART WITH 1");
    	
    	jdbcTemplate.execute("TRUNCATE TABLE Pizzas");
        jdbcTemplate.execute("ALTER TABLE Pizzas ALTER COLUMN pizzas_id RESTART WITH 1");
    	
    	jdbcTemplate.execute("TRUNCATE TABLE Orders");
        jdbcTemplate.execute("ALTER TABLE Orders ALTER COLUMN orders_id RESTART WITH 1");
        
    	jdbcTemplate.execute("TRUNCATE TABLE Orderstatuses");
        jdbcTemplate.execute("ALTER TABLE Orderstatuses "
        		+ "ALTER COLUMN order_statuses_id RESTART WITH 1");        
        
    	jdbcTemplate.execute("TRUNCATE TABLE Clients");
        jdbcTemplate.execute("ALTER TABLE Clients ALTER COLUMN clients_id RESTART WITH 1");
    	
    	jdbcTemplate.execute("TRUNCATE TABLE Addresses");
        jdbcTemplate.execute("ALTER TABLE Addresses ALTER COLUMN addresses_id RESTART WITH 1");        
        
        jdbcTemplate.execute("INSERT INTO Orderstatuses (order_statuses_name) "
        		+ "VALUES ('NEW')");
        jdbcTemplate.execute("INSERT INTO Orderstatuses (order_statuses_name) "
        		+ "VALUES ('IN PROGRESS')");
        jdbcTemplate.execute("INSERT INTO Orderstatuses (order_statuses_name) "
        		+ "VALUES ('CANCELLED')");
        jdbcTemplate.execute("INSERT INTO Orderstatuses (order_statuses_name) "
        		+ "VALUES ('DONE')");
        
        jdbcTemplate.execute("INSERT INTO Addresses (postal_code, city, street, bld, apt) "
        		+ "VALUES ('0123', 'Kyiv', 'Khreschatik', '1B', '3')");
        jdbcTemplate.execute("INSERT INTO Addresses (postal_code, city, street, bld, apt) "
        		+ "VALUES ('01234', 'Kyiv', 'Vokzalna', '221B', '378')");
    	    	
        jdbcTemplate.execute("INSERT INTO Clients (clients_name, address_id) "
        		+ "VALUES ('Ivan Ivanov', 1)");
        jdbcTemplate.execute("INSERT INTO Clients (clients_name, address_id) "
        		+ "VALUES ('Petro Petrov', 2)");
        
        jdbcTemplate.execute("INSERT INTO "
        		+ "Orders (orders_name, orders_date, orders_price, orders_status_id, client_id) "
        		+ "VALUES ('Order1', '2008-08-22', 0.0, 2, 1)");
        jdbcTemplate.execute("INSERT INTO "
        		+ "Orders (orders_name, orders_date, orders_price, orders_status_id, client_id) "
        		+ "VALUES ('Order2', '2015-10-15', 0.0, 4, 2)");
        jdbcTemplate.execute("INSERT INTO "
        		+ "Orders (orders_name, orders_date, orders_price, orders_status_id, client_id) "
        		+ "VALUES ('Order3', '2014-03-12', 0.0, 4, 1)");
        
        jdbcTemplate.execute("INSERT INTO Pizzas (pizzas_name, pizzas_price, pizzas_type) "
        		+ "VALUES ('Big Meat Pizza', 450.00, 2)");
        jdbcTemplate.execute("INSERT INTO Pizzas (pizzas_name, pizzas_price, pizzas_type) "
        		+ "VALUES ('Big Sea Pizza', 500.00, 1)");
        
        jdbcTemplate.execute("INSERT INTO Pizzasinorders (order_id, pizza_id, quantity) "
        		+ "VALUES (1, 1, 1)");
        jdbcTemplate.execute("INSERT INTO Pizzasinorders (order_id, pizza_id, quantity) "
        		+ "VALUES (1, 2, 2)");
    }
}
