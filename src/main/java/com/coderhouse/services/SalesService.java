package com.coderhouse.services;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.coderhouse.repositories.InvoicesRepository;
import com.coderhouse.repositories.SalesRepository;


@Service
public class SalesService {
	
	@Autowired
	InvoicesRepository invoicesRepository;
	
	@Autowired
	SalesRepository salesRepository;
	

	
}