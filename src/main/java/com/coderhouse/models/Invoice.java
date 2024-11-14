package com.coderhouse.models;

import java.sql.Date;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

@Entity
@Table(name="invoices")
public class Invoice {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	private long invoiceId;
	
	@Column(name = "invoice_date", nullable=false)
	private Date invoiceDate;
	
	@Column(name="payed_date_last_update",nullable=false)
	@CreationTimestamp //AL crearse el registro el payedLastUpdate debe ser ahora.
	private Date payedDateLastUpdate;
	
	@Column(name="payed",nullable=false, columnDefinition = "BOOLEAN DEFAULT false")
	private boolean payed;
	
	@Column(name = "amount", nullable=false)
	private float amount;
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	 //@JsonBackReference
	private Client client;
	
	@OneToMany(mappedBy="invoice", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<InvoiceDetail> detail = new ArrayList<>();




	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", payedDateLastUpdate="
				+ payedDateLastUpdate + ", payed=" + payed + ", amount=" + amount + ", client=" + client + ", detail="
				+ detail + "]";
	}


	// Constructor sin parámetros (requerido por JPA y Jackson)
    public Invoice() {
    	
    }
    
    	
	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getPayedDateLastUpdate() {
		return payedDateLastUpdate;
	}

	public void setPayedDateLastUpdate(Date payedDate) {
		this.payedDateLastUpdate = payedDate;
	}

	
	
	public Boolean getPayed() {
		return payed;
	}


	public void setPayed(Boolean payed) {
		this.payed = payed;
	}


	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<InvoiceDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<InvoiceDetail> detail) {
		this.detail = detail;
	}
	
	//Suma o resta la variacion al actual amount, Ya la variacion de monton debe venir calculada x la capa de servicio.
	//SI es positiva o negativa pasarla con signo negativo o positivo segun corresponda
	public void adjustAmount(float variationAmount) {
		this.amount = this.amount + variationAmount; 	
	}
	
	
}
