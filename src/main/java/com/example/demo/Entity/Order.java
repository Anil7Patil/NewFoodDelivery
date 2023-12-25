package com.example.demo.Entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name="OrderId")
	private double orderiId;
	
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
    private User user;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
	
	
	  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	    private payment payment;
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
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
	public double getOrderiId() {
		return orderiId;
	}
	public void setOrderiId(double orderiId) {
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
	public payment getPayment() {
		return payment;
	}
	public void setPayment(payment payment) {
		this.payment = payment;
	}
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
	
	

}
