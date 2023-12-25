package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Order;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
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


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order, @RequestParam int userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        String result = orderService.createOrder(order, user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/byUser")
    public ResponseEntity<List<Order>> getOrdersByUser(@RequestParam Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Order> orders = orderService.getOrdersByUser(user);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
        Order order = orderService.getOrderById(orderId);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable int orderId) {
        Order order = orderService.getOrderById(orderId);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable int orderId, @RequestBody Order updatedOrder) {
        updatedOrder.setId(orderId); // Make sure the ID is set for the updated order

        String result = orderService.updateOrder(updatedOrder);

        if (result.equals("Order not found")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{orderId}/addProduct")
    public ResponseEntity<Void> addProductToOrder(@PathVariable int orderId, @RequestBody Product product) {
        orderService.addProductToOrder(orderId, product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{orderId}/removeProduct")
    public ResponseEntity<Void> removeProductFromOrder(@PathVariable int orderId, @RequestBody Product product) {
        orderService.removeProductFromOrder(orderId, product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
