package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.coderhouse.models.Invoice;

public interface InvoicesRepository extends JpaRepository<Invoice,Long> {

}
