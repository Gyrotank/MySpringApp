package ua.epam.rd.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ua.epam.rd.DAOTestsTemplate;
import ua.epam.rd.domain.Client;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringConfigTest.xml")
@Transactional
public class ClientDAOJDBCTest extends DAOTestsTemplate{
    
    @Autowired
    @Qualifier("clientServiceJDBC")
    private transient ClientService clientService;
    
    final Client cl1 = new Client();
    final Client cl2 = new Client();
            
    @Before
    public void prepareDB(){
    	jdbcTemplate.execute("TRUNCATE TABLE Addresses");
        jdbcTemplate.execute("ALTER TABLE Addresses ALTER COLUMN addresses_id RESTART WITH 1");
        jdbcTemplate.execute("INSERT INTO Addresses (postal_code, city, street, bld, apt) "
        		+ "VALUES ('0123', 'Kyiv', 'Khreschatik', '1B', '3')");
        jdbcTemplate.execute("INSERT INTO Addresses (postal_code, city, street, bld, apt) "
        		+ "VALUES ('01234', 'Kyiv', 'Vokzalna', '221B', '378')");
    	
    	jdbcTemplate.execute("TRUNCATE TABLE Clients");
        jdbcTemplate.execute("ALTER TABLE Clients ALTER COLUMN clients_id RESTART WITH 1");
        jdbcTemplate.execute("INSERT INTO Clients (clients_name, address_id) VALUES ('Ivan Ivanov', 1)");
        jdbcTemplate.execute("INSERT INTO Clients (clients_name, address_id) VALUES ('Petro Petrov', 2)");
    }    
   
    @Test
    public void testGetAllClients() {
    	Assert.assertTrue(clientService.getAllClients().size() == 2);
    }  
       
}
