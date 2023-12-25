package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Order;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;

public interface OrderService {
	
	
    List<Order> getAllOrders();

    List<Order> getOrdersByUser(User user);

    Order getOrderById(int orderId);

    void deleteOrderById(int orderId);

    String updateOrder(Order updatedOrder);

    void addProductToOrder(int orderId, Product product);

	void removeProductFromOrder(int orderId, Product product);

	

	String createOrder(int userId, List<Integer> productIds);
	

}
