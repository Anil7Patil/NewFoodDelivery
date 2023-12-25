package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

	User findByEmail(String email);
    void deleteByEmail(String email);
    User findById(Long userId);
}
