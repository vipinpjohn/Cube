package com.cubes.model.ext;

import java.io.Serializable;

import com.cubes.model.ProductLine;

public class ProductTemp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public Long getVendorID() {
		return vendorID;
	}

	public void setVendorID(Long vendorID) {
		this.vendorID = vendorID;
	}

	public String getPriceID() {
		return priceID;
	}

	public void setPriceID(String priceID) {
		this.priceID = priceID;
	}

	private String productName;

	private Stock stock;

	private String productDesc;

	private Long vendorID;

	private String priceID; // TODO change it to standalone class

	public ProductLine getProductLineID() {
		return productLineID;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setProductLineID(ProductLine productLineID) {
		this.productLineID = productLineID;
	}

	private ProductLine productLineID;

}
