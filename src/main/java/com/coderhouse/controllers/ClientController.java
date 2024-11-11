package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.CreateOrUpdateClientDTO;
import com.coderhouse.models.Client;
import com.coderhouse.services.ClientsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
	
	@Autowired
	ClientsService clientsService;
	
	@GetMapping
	public ResponseEntity<List<Client>> getAllClients(){
		try {
			List<Client> clients = clientsService.getAllClients();
			return ResponseEntity.ok(clients);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") Long clientId){
		try {
			Client searchedClient = clientsService.findById(clientId);
			return ResponseEntity.ok(searchedClient);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Client> createClient(@Valid @RequestBody CreateOrUpdateClientDTO newClient) {
		try {
			Client createdClient = clientsService.saveClient(newClient);
			return ResponseEntity.ok(createdClient);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") Long clientId,@Valid @RequestBody CreateOrUpdateClientDTO clientsNewValues){
		try {
			Client updatedClient = clientsService.updateClient(clientId, clientsNewValues);
			return ResponseEntity.ok(updatedClient);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable("id") Long clientId){
		try {
			clientsService.deleteClientById(clientId);
			return ResponseEntity.noContent().build();
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	

}
