package com.example.demo.service;

import java.util.List;


import com.example.demo.Entity.Orders;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.dto.UpdatedOrderDto;

public interface OrderService {
	
	String createOrder(int userId, List<Integer> productIds);
    List<Orders> getAllOrders();//getDate wise//not working
    String updateOrder(String orderId,UpdatedOrderDto updatedOrderDto);

//    Orders getOrderById(int orderId);
//
//    String deleteOrderById(int orderId);

   

//    void addProductToOrder(int orderId, Product product);
//
//	void removeProductFromOrder(int orderId, Product product);

	

	
	

}
