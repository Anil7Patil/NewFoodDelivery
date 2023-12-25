package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Order;
import com.example.demo.Entity.User;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer>{
    List<Order> findByUser(User user);
    Order findById(int id);
    
}
