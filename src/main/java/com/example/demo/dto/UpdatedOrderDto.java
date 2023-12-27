package com.example.demo.dto;

import java.time.LocalDateTime;


public class UpdatedOrderDto {
	
	
	private  LocalDateTime deliveryDateTime;
	private double totalPrise;
	private String orderStatus;
	private String productName;
	public LocalDateTime getDeliveryDateTime() {
		return deliveryDateTime;
	}
	public void setDeliveryDateTime(LocalDateTime deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	

}
