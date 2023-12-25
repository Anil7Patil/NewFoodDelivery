package com.example.demo.seiviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Order;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.OrderService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderImpl implements OrderService{

	
	 @Autowired
	    private OrderRepo orderRepository;

	 @Autowired
	    private UserRepo userRepository;
	 
	 @Autowired
	    private ProductRepo productRepository;
	 
	
	  
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
	          
	            orderRepository.save(order);
	        }
	    }

	    @Override
	    public void removeProductFromOrder(int orderId, Product product) {
	        Order order = getOrderById(orderId);
	        if (order != null) {
	            // Additional logic and validation...
	         
	            orderRepository.save(order);
	        }
	    }

	    @Override
	    public String createOrder(int userId, List<Integer> productIds) {
	        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

	        Order order = new Order();
	       order.setUser(user);
	        order.setOrderDateTime(LocalDateTime.now());
	        order.setDeliveryDateTime(LocalDateTime.now().plusDays(2));
	        order.setOrderiId(generateUniqueId());
	        order.setTotalPrise(calculateTotalPrice(productIds));
	        order.setOrderStatus("Processing");
	       order.setProductName(addProductName(productIds));
//	        List<Product> products = productService.getProductsByIds(productIds);
//	        order.setProducts(products);

	        orderRepository.save(order);

	        return "Ordered successfully!";
	    }

	    private String addProductName(List<Integer> productIds) {
	    	String pName="";
	    	 for (Integer x : productIds) {
	        	   Product p = productRepository.findById(x).orElseThrow(() -> new EntityNotFoundException("User not found"));
	            pName += p.getProductName();
	        }
	        return pName;
		}

		private String generateUniqueId() {
	      
	        return UUID.randomUUID().toString();
	    }

	    private double calculateTotalPrice(List<Integer> productIds) {
	    	double totalPrice = 0.0;

	        for (Integer x : productIds) {
	        	   Product p = productRepository.findById(x).orElseThrow(() -> new EntityNotFoundException("User not found"));
	            totalPrice += p.getPrise();
	        }
	        return totalPrice;
	    }
	       
	}