package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.demo.Entity.Orders;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.dto.UpdatedOrderDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.OrderService;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private OrderRepo orderRepository;


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest request) {
     
        userRepository.findById(request.getUserId()).orElseThrow(()->new ResourceNotFoundException("order controller","id",request.getUserId())) ;
        String result = orderService.createOrder(request.getUserId(), request.getProductIds());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrders() {
    //	System.out.println("-)))))))))))))))))))))))----000000000000-----------)))))))))----000000000");
        List<Orders> o=orderRepository.findAll();
        return new ResponseEntity<>(o,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Orders>> getOrdersByUser(@PathVariable int userId) {
       User user = userRepository.findById(userId).orElse(null);
    	System.out.println("_____________________000000000000__________)))))))0000000000_________00000");
    	
       if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       List<Orders> userOrders = orderRepository.findByUserId(userId);
        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable int orderId) {
        Orders order = orderRepository.findById(orderId);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable int orderId) {
    	 Orders order = orderRepository.findById(orderId);

         if (order == null) {
             return new ResponseEntity<>("order id not found plese check it",HttpStatus.NOT_FOUND);
         }
         orderRepository.deleteById(orderId);
         return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    	
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestParam("orderId") String orderId, @RequestBody UpdatedOrderDto updatedOrderDto) {
       

        String result = orderService.updateOrder(orderId, updatedOrderDto);

        if (result.equals("Order not found")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

   
   
}
