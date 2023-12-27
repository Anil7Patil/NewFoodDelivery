package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.demo.Entity.Orders;
import com.example.demo.Entity.User;

@Repository
public interface OrderRepo extends JpaRepository<Orders,Integer>{
   // List<Orders> findByUserId(int id);
    List<Orders> findByUserId(int userId);
    Orders findById(int id);
   
    @Query("SELECT o FROM Orders o WHERE o.orderiId = :orderiId")
    Orders findByOrderiId(@Param("orderiId") String orderiId);
    
}
