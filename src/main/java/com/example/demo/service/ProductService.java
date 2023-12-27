package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Product;

public interface ProductService {
	
	
	String addProduct(Product p);
	String deleteProduct(int id);
	String updateProduct(int id,Product p);
	List<Product>allProduct();
	


}
