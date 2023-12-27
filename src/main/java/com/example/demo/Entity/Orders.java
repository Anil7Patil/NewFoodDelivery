package com.example.demo.Entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.Order;

@Entity
@Table(name="orders")
public class Orders {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name="OxId")
	private String orderiId;
	
	@Column(name="OrderDateTime")
	private  LocalDateTime orderDateTime;
	
	@Column(name="DeliveryDate")
	private  LocalDateTime deliveryDateTime;
	
	@Column(name="TotalPrise")
	private double totalPrise;
	
	@Column(name="OrderStatus")
	private String orderStatus;
	@ManyToOne
    @JoinColumn(name = "user_id")
	 @JsonBackReference
    private User user;

	@Column(name="productName")
	private String productName;
	
//	  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//	    private payment payment;
//	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderiId() {
		return orderiId;
	}
	public void setOrderiId(String orderiId) {
		this.orderiId = orderiId;
	}
	
	public double getTotalPrise() {
		return totalPrise;
	}
	public void setTotalPrise(double totalPrise) {
		this.totalPrise = totalPrise;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public jakarta.persistence.criteria.Order orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
//	public payment getPayment() {
//		return payment;
//	}
//	public void setPayment(payment payment) {
//		this.payment = payment;
//	}
	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public void setDeliveryDateTime(LocalDateTime deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}
	public LocalDateTime getDeliveryDateTime() {
		return deliveryDateTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	

}
