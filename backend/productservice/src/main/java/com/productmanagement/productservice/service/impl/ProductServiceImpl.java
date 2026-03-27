package com.productmanagement.productservice.service.impl;

import com.productmanagement.productservice.entity.Product;
import com.productmanagement.productservice.exception.customExceptions.ProductNotFoundException;
import com.productmanagement.productservice.repository.ProductRepository;
import com.productmanagement.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public List<Product> findAll() throws ProductNotFoundException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) throw new ProductNotFoundException("No product excist in products table");
        return products;
    }

    public Product findById(int id) throws ProductNotFoundException {
        return productRepository
                .findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: "+id));
    }

    public Product save(Product product){
        return productRepository.save(product);
    }
}
