package ua.epam.rd.service;

import java.util.List;

import ua.epam.rd.domain.Client;
import ua.epam.rd.domain.Order;

public interface ClientService {
	
	List<Client> getAllClients();
	Client getClientByName(String name);
	Client getClientById(int id);
	List<Order> getOrdersForAClientByName(String name);
}
