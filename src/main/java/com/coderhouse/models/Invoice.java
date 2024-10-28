package com.coderhouse.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="invoices")
public class Invoice {
	
	
	
	public Invoice(long invoiceId, Date invoiceDate, Date payedDate, float amount, Client client,
			List<SoldProduct> detail) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.payedDate = payedDate;
		this.amount = amount;
		this.client = client;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", payedDate=" + payedDate
				+ ", amount=" + amount + ", client=" + client + ", detail=" + detail + "]";
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

	public Date getPayedDate() {
		return payedDate;
	}

	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
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

	public List<SoldProduct> getDetail() {
		return detail;
	}

	public void setDetail(List<SoldProduct> detail) {
		this.detail = detail;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	private long invoiceId;
	
	@Column(name = "invoice_date", nullable=false)
	private Date invoiceDate;
	
	@Column(name="payed_date")
	private Date payedDate;
	
	@Column(name = "amount", nullable=false)
	private float amount;
	
	@OneToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;
	
	@OneToMany(mappedBy="invoice", cascade=CascadeType.ALL)
	private List<SoldProduct> detail = new ArrayList<>();
	
}
