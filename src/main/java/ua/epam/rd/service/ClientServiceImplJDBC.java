package ua.epam.rd.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.epam.rd.domain.Address;
import ua.epam.rd.domain.Client;
import ua.epam.rd.domain.Order;


@Service("clientServiceJDBC")
public class ClientServiceImplJDBC implements ClientService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Override
	public List<Client> readAllClients() {
		List<Client> result = em.createNamedQuery("Client.findAll", Client.class)
				.getResultList();
		return result;
	}
	
	@Transactional
	@Override
	public Client readClientByName(String clientName) {
		Client result = em.createNamedQuery("Client.findByName", Client.class)
				.setParameter("clientName", clientName).getSingleResult();
		return result;
	}
	
	@Transactional
	@Override
	public Client readClientById(int id) {
		return em.find(Client.class, id);
	}

	@Transactional
	@Override
	public List<Order> readOrdersForAClientByName(String name) {
		@SuppressWarnings("unchecked")
		List<Order> result = em.createNamedQuery("Client.findOrdersForAClientByName")
				.setParameter("clientName", name).getResultList();
		return result;
	}

	@Transactional
	@Override
	public void createClient(String name, Address address) {
		Client createdClient = new Client(name, address);
		em.persist(createdClient);
	}
	
	@Transactional
	@Override
	public int updateClientNameById(int id, String newName) {
		Query query = em.createNamedQuery("Client.updateClientNameById")
				.setParameter("id", id).setParameter("newName", newName);
		return query.executeUpdate();		
	}
	
	@Transactional
	@Override
	public void deleteClientByName(String clientName) {
		Client clientToBeDeleted = em.createNamedQuery("Client.findByName", Client.class)
				.setParameter("clientName", clientName).getSingleResult();
		for (Order o : clientToBeDeleted.getOrders()) {
			o.setClient(null);			
		}
		clientToBeDeleted.getOrders().clear();
		em.remove(clientToBeDeleted);
	}
	
	@Transactional
	@Override
	public List<Address> readAllAddresses() {
		List<Address> result = em.createNamedQuery("Address.findAll", Address.class)
				.getResultList();
		return result;
	}
}
