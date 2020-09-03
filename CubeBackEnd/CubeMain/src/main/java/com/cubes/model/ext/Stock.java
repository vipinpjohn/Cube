package com.cubes.model.ext;

import java.io.Serializable;
import java.util.Date;

public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long stockID;

	public Long getstockID() {
		return stockID;
	}

	public void setstockID(Long stockID) {
		this.stockID = stockID;
	}

	private String stockName;

	public String getstockName() {
		return stockName;
	}

	public void setstockName(String stockName) {
		this.stockName = stockName;
	}

	private String stockDesc;

	private Date createdAt;

	private Date updatedAt;

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

	public String getstockDesc() {
		return stockDesc;
	}

	public void setstockDesc(String stockDesc) {
		this.stockDesc = stockDesc;
	}

	private Long availableStock;

	public Long getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(Long availableStock) {
		this.availableStock = availableStock;
	}

}
