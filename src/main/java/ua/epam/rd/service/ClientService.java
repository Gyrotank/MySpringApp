package ua.epam.rd.service;

import java.util.List;

import ua.epam.rd.domain.Address;
import ua.epam.rd.domain.Client;
import ua.epam.rd.domain.Order;

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
