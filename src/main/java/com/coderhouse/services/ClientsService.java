package com.coderhouse.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.CreateOrUpdateClientDTO;
import com.coderhouse.models.Client;
import com.coderhouse.repositories.ClientsRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientsService {
	
	@Autowired
	ClientsRepository clientsRepository;
	
	public List<Client> getAllClients(){
		return clientsRepository.findAll();
	}
	
	public Client findById(Long clientId) {
		return  clientsRepository.findById(clientId)
				.orElseThrow(()-> new IllegalArgumentException("Cliente no encontrado..."));
		
	}
	
	public Boolean existsClientById(Long clientId) {
		return clientsRepository.existsById(clientId);
	}

	public Client saveClient(CreateOrUpdateClientDTO newClientDTO) {
		//Construyo la entidad client a partir del objeto newClientDTO y se la entrego al repo para su creacion
		Client newClient = new Client();
		newClient.setDni(newClientDTO.getDni());
		newClient.setFirstName(newClientDTO.getFirstName());
		newClient.setLastName(newClientDTO.getLastName());
		//Los siguientes campos podrian venir null asique tengo que comprbbarlo ya que el dto solo pide dni,firstName y lastName.
		if (newClientDTO.getAddressStreet() != null) newClient.setAddressStreet(newClientDTO.getAddressStreet());
		if (newClientDTO.getPostalCode()!= null) newClient.setPostalCode(newClientDTO.getPostalCode());
		if (newClientDTO.getPhoneNumber() != null) newClient.setPhoneNumber(newClientDTO.getPhoneNumber());
		//Finalmente guardamos en el repo  y devolvemos la instan cia de cliente.
		return clientsRepository.save(newClient);
	}
	
	@Transactional
	public Client updateClient(Long clientId,CreateOrUpdateClientDTO clientDetails) {
		Client updatingClient = clientsRepository.findById(clientId)
				.orElseThrow(()-> new IllegalArgumentException("Cliente no encontrado..."));
		
		//Seteo los valores nuevos pero antes los valido.
		if (clientDetails.getAddressStreet() != null) updatingClient.setAddressStreet(clientDetails.getAddressStreet());
		if (clientDetails.getDni() != null) updatingClient.setDni(clientDetails.getDni());
		if (clientDetails.getFirstName() != null) updatingClient.setFirstName(clientDetails.getFirstName());
		if (clientDetails.getLastName() != null) updatingClient.setLastName(clientDetails.getLastName());
		if (clientDetails.getPhoneNumber() != null) updatingClient.setPhoneNumber(clientDetails.getPhoneNumber());
		if (clientDetails.getPostalCode() != null) updatingClient.setPostalCode(clientDetails.getPostalCode());
		
		return clientsRepository.save(updatingClient);	
	}
	
	public void deleteClientById(Long clientId) {
		if (!clientsRepository.existsById(clientId)) throw new IllegalArgumentException("Cliente no encontrado...");
		//Aca vendria solo si existe x lo cual puedo borralos sin problemas.
		clientsRepository.deleteById(clientId);
	}
}
