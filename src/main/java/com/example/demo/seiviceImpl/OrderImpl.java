package com.example.demo.seiviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Order;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.OrderService;

@Service
public class OrderImpl implements OrderService{

	
	 @Autowired
	    private OrderRepo orderRepository;

	    @Override
	    public String createOrder(Order order, User user) {
	        // Set the user for the order
	        order.setUser(user);
	        // Additional logic and validation...
	        orderRepository.save(order);
	        return "Order created successfully!";
	    }

	    @Override
	    public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	    }

	    @Override
	    public List<Order> getOrdersByUser(User user) {
	        return orderRepository.findByUser(user);
	    }

	    @Override
	    public Order getOrderById(int orderId) {
	        return (Order) orderRepository.findById(orderId).orElse(null);
	    }

	    @Override
	    public void deleteOrderById(int orderId) {
	        orderRepository.deleteById(orderId);
	    }

	    @Override
	    public String updateOrder(Order updatedOrder) {
	        // Additional logic and validation...
	        orderRepository.save(updatedOrder);
	        return "Order updated successfully!";
	    }

	    @Override
	    public void addProductToOrder(int orderId, Product product) {
	        Order order = getOrderById(orderId);
	        if (order != null) {
	            // Additional logic and validation...
	            order.getProducts().add(product);
	            orderRepository.save(order);
	        }
	    }

	    @Override
	    public void removeProductFromOrder(int orderId, Product product) {
	        Order order = getOrderById(orderId);
	        if (order != null) {
	            // Additional logic and validation...
	            order.getProducts().remove(product);
	            orderRepository.save(order);
	        }
	    }

}
