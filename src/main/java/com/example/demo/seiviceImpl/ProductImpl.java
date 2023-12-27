package com.example.demo.seiviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;



@Service
public class ProductImpl implements ProductService{

	@Autowired
    private ProductRepo productRepository;

   

	@Override
	public String addProduct(Product p) {
		 productRepository.save(p);
		return "Product added successfyly";
	}

	@Override
	public String deleteProduct(int id) {
		this.productRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Product","id",id)) ;
		 productRepository.deleteById(id);
		return "Product delete successfyly";
	}

	@Override
	public String updateProduct(int id, Product p) {
		Product product=productRepository.findById(id).orElse(null);
		product.setPrise(p.getPrise());
		product.setProductDetails(p.getProductDetails());
		product.setProductName(p.getProductName());
		
		productRepository.save(product);
		return "Product update successfyly";
	}

	@Override
	public List<Product> allProduct() {
		return productRepository.findAll();
	}

   
}