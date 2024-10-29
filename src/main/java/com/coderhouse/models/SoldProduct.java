package com.coderhouse.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sales_list")
public class SoldProduct {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long sold_product_id;
	
	@Column(name = "price",nullable = false) //Precio al momneto de la venta.
	private float soldPrice;
	
	@Column(name = "quantity",nullable = false) //Precio al momneto de la venta.
	private float soldQuantity;
	
	@Override
	public String toString() {
		return "SoldProduct [sold_product_id=" + sold_product_id + ", soldPrice=" + soldPrice + ", soldQuantity="
				+ soldQuantity + ", invoice=" + invoice + ", product=" + product + "]";
	}

	public long getSold_product_id() {
		return sold_product_id;
	}

	public void setSold_product_id(long sold_product_id) {
		this.sold_product_id = sold_product_id;
	}

	public float getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(float soldPrice) {
		this.soldPrice = soldPrice;
	}

	public float getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(float soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;
	
	@OneToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	
	
	
}

