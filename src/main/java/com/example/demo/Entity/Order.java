package com.example.demo.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="order")
public class Order {
	
	private int id;
	private double orderiId;
	private Date OrderDateTime;
	private Date DeliveryDateTime;
	private double totalPrise;
	private String orderStatus;
	

}
