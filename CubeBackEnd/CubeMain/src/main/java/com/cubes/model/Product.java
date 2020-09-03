package com.cubes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PRODUCT")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@XmlRootElement
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getVendorID() {
		return vendorID;
	}

	public Long getStockID() {
		return stockID;
	}

	public void setStockID(Long stockID) {
		this.stockID = stockID;
	}

	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}

	public String getPriceID() {
		return priceID;
	}

	public void setPriceID(String priceID) {
		this.priceID = priceID;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private Long stockID; // TODO change it to standalone class

	@Column(nullable = false)
	private String productDesc;

	@Column(nullable = false)
	private String vendorID;

	@Column(nullable = false)
	private String priceID; // TODO change it to standalone class

	public ProductLine getProductLineID() {
		return productLineID;
	}

	public void setProductLineID(ProductLine productLineID) {
		this.productLineID = productLineID;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productLine")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private ProductLine productLineID;

	@Column(nullable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

}
