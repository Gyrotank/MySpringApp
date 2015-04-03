package ua.epam.rd.service;

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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringConfigTest.xml")
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientDAOJDBCTest extends DAOTestsTemplate{
    
    @Autowired
    @Qualifier("clientServiceJDBC")
    private transient ClientService clientService;
       
    @Test
    public void testReadAllClients() {
    	Assert.assertTrue(clientService.readAllClients().size() == 2);
    }
    
    @Test
    public void testReadClientByNameExisting() {
    	Assert.assertTrue(clientService.readClientByName("Ivan Ivanov").getId() == 1);
    	Assert.assertTrue(clientService.readClientByName("Petro Petrov").getId() == 2);
    	
    }
    
    @Test(expected = NoResultException.class)
    public void testReadClientByNameNonExisting() {
    	 Assert.assertTrue(clientService.readClientByName("Sydor Sydorov").getId() == 3);    	
    }
    
    @Test
    public void testReadClientByIdExisting() {
    	Assert.assertTrue(clientService.readClientById(1).getName().contentEquals("Ivan Ivanov"));
    	Assert.assertTrue(clientService.readClientById(2).getName().contentEquals("Petro Petrov"));
    	
    }
    
    @Test
    public void testReadClientByIdNonExisting() {
    	 Assert.assertTrue(clientService.readClientById(3) == null);    	
    }
    
    @Test
    public void testReadOrdersForAClientByNameExisting() {
    	Assert.assertTrue(clientService.readOrdersForAClientByName("Ivan Ivanov").size() == 2);
    	Assert.assertTrue(clientService.readOrdersForAClientByName("Petro Petrov").size() == 1);
    	
    }
    
    @Test
    public void testReadOrdersForAClientByNameNonExisting() {
    	 Assert.assertTrue(clientService.readOrdersForAClientByName("Sydor Sydorov").isEmpty());
    }
}