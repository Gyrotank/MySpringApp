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
import ua.epam.rd.domain.Address;


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
    
    @Test
    public void testCreateClient() {
    	Assert.assertTrue(clientService.readAllClients().size() == 2);
    	clientService.createClient("John Doe", 
        		new Address("001", "Acapulco", "Main str.", "45", "2-43"));
    	Assert.assertTrue(clientService.readAllClients().size() == 3);
    	Assert.assertTrue(clientService.readClientById(3).getName().contentEquals("John Doe"));
    }
    
    @Test
    public void testUpdateClientNameById() {
    	clientService.createClient("John Doe", 
        		new Address("001", "Acapulco", "Main str.", "45", "2-43"));
    	Assert.assertTrue(clientService.updateClientNameById(
        		clientService.readClientByName("John Doe").getId(), "AB") == 1);
    }
    
    @Test
    public void testDeleteClientByName() {
    	Assert.assertTrue(clientService.readAllClients().size() == 2);
    	clientService.deleteClientByName("Ivan Ivanov");
    	Assert.assertTrue(clientService.readAllClients().size() == 1);    	
    }
    
    @Test
    public void testReadAllAddresses() {
    	Assert.assertTrue(clientService.readAllAddresses().size() == 2);
    }
    
    @Test
    public void testReadAllAddressesAfterClientDelete() {
    	clientService.deleteClientByName("Ivan Ivanov");
    	Assert.assertTrue(clientService.readAllAddresses().size() == 1);
    }
}
