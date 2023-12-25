package com.example.demo.seiviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Product;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;

import jakarta.persistence.criteria.Order;

@Service
public class ProductImpl implements ProductService{

	@Autowired
    private ProductRepo productRepository;

    @Autowired
    private OrderRepo orderRepository;

    @Override
    public Product addProductToOrder(Product product, int orderId) {
        Order order =orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            product.setOrder((com.example.demo.Entity.Order) order);
            return productRepository.save(product);
        }

        return null;
    }

    @Override
    public void removeProductFromOrder(int productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }
}