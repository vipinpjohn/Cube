package com.cubes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PRODUCTLINE")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@XmlRootElement
public class ProductLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productLineID;

	public Long getProductLineID() {
		return productLineID;
	}

	public void setProductLineID(Long productLineID) {
		this.productLineID = productLineID;
	}

	@Column(nullable = false, updatable = true, name = "Name")
	private String productLineName;

	public String getProductLineName() {
		return productLineName;
	}

	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}

	@Column(nullable = false, updatable = true, name = "textDescription")
	private String productDesc;
	@Column(nullable = true, updatable = true, name = "htmlDescription")
	private String htmlDesc;
	// TODO change to blob later
	@Column(nullable = true, updatable = true)
	private String image;
	@Column(nullable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	@Column(nullable = false)
	private String category;// new column;

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

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getHtmlDesc() {
		return htmlDesc;
	}

	public void setHtmlDesc(String htmlDesc) {
		this.htmlDesc = htmlDesc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
