package ua.epam.rd.domain;

import java.util.ArrayList;
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

import ua.epam.rd.DAOTestsTemplate;
import ua.epam.rd.service.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringConfigTest.xml")
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientDAOJDBCTest extends DAOTestsTemplate {
	
	@Autowired
    @Qualifier("clientServiceJDBC")
    private transient ClientService clientService;
	
	@Test
	public void testSetGetId() {
		Client newClient = new Client();
		newClient.setId(35);
		Assert.assertTrue(newClient.getId() == 35);
	}
	
	@Test
	public void testSetGetName() {
		Client newClient = new Client();
		newClient.setName("Dummy Client");
		Assert.assertTrue(newClient.getName().contentEquals("Dummy Client"));
	}
	
	@Test
	public void testSetGetOrders() {
		Client newClient = new Client();
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order());
		newClient.setOrders(orders);
		Assert.assertTrue(newClient.getOrders().get(0).getName().contentEquals(""));
	}
	
	@Test
	public void testSetGetAddress() {
		Client newClient = new Client();
		Address address = new Address("01001", "Kyiv", "Khreshatyk str.", "1", "123");
		newClient.setAddress(address);
		Assert.assertTrue(newClient.getAddress().getCity().contentEquals("Kyiv"));
	}
	
	@Test
	public void testToStringAddressAndOrdersNotSet() {
		Client newClient = new Client();
		Assert.assertTrue(newClient.toString()
				.contentEquals("{0; Anonymous; ADDRESS NOT AVAILABLE; [ NO ORDERS ]}"));
	}
	
	@Test
	public void testToStringAddressAndOrdersSet() {
		Client newClient = new Client();
		newClient.setAddress(new Address());
		List<Order> newOrders = new ArrayList<Order>();
        newOrders.add(new Order());
		newClient.setOrders(newOrders);
		Assert.assertTrue(newClient.toString()
				.contentEquals("{0; Anonymous; {0; CLIENT UNKNOWN; null; null; null; null; null};"
						+ " [ ; 0.0; \n ]}"));
	}
}
