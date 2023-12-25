package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Product;

public interface ProductService {
	Product addProductToOrder(Product product, int orderId);

    void removeProductFromOrder(int productId);

    List<Product> getAllProducts();

    Product getProductById(int productId);


}
