package ua.pizzadelivery.rd.service;

import java.util.List;

import ua.pizzadelivery.rd.domain.Address;
import ua.pizzadelivery.rd.domain.Client;
import ua.pizzadelivery.rd.domain.Order;

public interface ClientService {
	
	List<Client> readAllClients();
	Client readClientByName(String name);
	Client readClientById(int id);
	List<Order> readOrdersForAClientByName(String name);
	void createClient(String name, Address address);
	int updateClientNameById(int id, String newName);
	void deleteClientByName(String clientName);
	List<Address> readAllAddresses();
}
