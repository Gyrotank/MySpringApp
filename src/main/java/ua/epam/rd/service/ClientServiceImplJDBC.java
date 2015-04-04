package ua.epam.rd.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		List<Client> result = em.createNamedQuery("Client.findAll", Client.class).getResultList();
		return result;
	}
	
	@Transactional
	@Override
	public Client readClientByName(String name) {
		Client result = em.createNamedQuery("Client.findByName", Client.class)
				.setParameter("clientName", name).getSingleResult();
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
	
	

}
