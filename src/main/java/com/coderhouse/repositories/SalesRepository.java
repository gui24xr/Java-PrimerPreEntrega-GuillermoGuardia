package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Sale;


public interface SalesRepository extends JpaRepository<Sale,Long>{

	
}
