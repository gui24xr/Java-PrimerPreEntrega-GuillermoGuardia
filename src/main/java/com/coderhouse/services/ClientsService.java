package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Client;
import com.coderhouse.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientsService {
	
	@Autowired
	ClientRepository clientRepository;
	
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}
	
	public Client findById(long id) {
		return  clientRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Cliente no encontrado..."));
		
	}

	public Client saveClient(Client newClient) {
		return clientRepository.save(newClient);
	}
	
	@Transactional
	public Client updateClient(Long clientId,Client clientDetails) {
		Client updatingClient = clientRepository.findById(clientId)
				.orElseThrow(()-> new IllegalArgumentException("Cliente no encontrado..."));
		
		//Seteo los valores nuevos pero antes los valido.
		if (clientDetails.getAddressStreet() != null) updatingClient.setAddressStreet(clientDetails.getAddressStreet());
		if (clientDetails.getDni() != null) updatingClient.setDni(clientDetails.getDni());
		if (clientDetails.getFirstName() != null) updatingClient.setFirstName(clientDetails.getFirstName());
		if (clientDetails.getLastName() != null) updatingClient.setLastName(clientDetails.getLastName());
		if (clientDetails.getPhoneNumber() != null) updatingClient.setPhoneNumber(clientDetails.getPhoneNumber());
		if (clientDetails.getPostalCode() != null) updatingClient.setPostalCode(clientDetails.getPostalCode());
		
		return clientRepository.save(updatingClient);	
	}
	
	public void deleteClientById(Long clientId) {
		if (!clientRepository.existsById(clientId)) throw new IllegalArgumentException("Cliente no encontrado...");
		//Aca vendria solo si existe x lo cual puedo borralos sin problemas.
		clientRepository.deleteById(clientId);
	}
}
