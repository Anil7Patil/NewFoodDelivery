package com.example.demo.seiviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Orders;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.dto.UpdatedOrderDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.OrderService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Order;

@Service
public class OrderImpl implements OrderService{

	
	 @Autowired
	    private OrderRepo orderRepository;

	 @Autowired
	    private UserRepo userRepository;
	 
	 @Autowired
	    private ProductRepo productRepository;
	 
		  
	    @Override
	    public List<Orders> getAllOrders() {
	        return orderRepository.findAll();
	    }


	    @Override
	    public String createOrder(int userId, List<Integer> productIds) {
	        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Order","id",userId)) ;
	        Orders order = new Orders();
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
	        	   Product p = productRepository.findById(x).orElseThrow(()->new ResourceNotFoundException("Order","id",x)) ;
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
	        	   Product p = productRepository.findById(x).orElseThrow(()->new ResourceNotFoundException("Order","id",x)) ;
	 	       	   totalPrice += p.getPrise();
	        }
	        return totalPrice;
	    }


		@Override
		public String updateOrder(String orderId, UpdatedOrderDto updatedOrderDto) {
		Orders order=orderRepository.findByOrderiId(orderId);
		if(order==null)
		{
			return "Order not found";
		}
		// Orders order = new Orders();
		  order.setDeliveryDateTime(updatedOrderDto.getDeliveryDateTime());
	        order.setTotalPrise(updatedOrderDto.getTotalPrise());
	        order.setOrderStatus(updatedOrderDto.getOrderStatus());
	       order.setProductName(updatedOrderDto.getProductName());
	       orderRepository.save(order);
		return "Order update successfully";
		}





		
	}