package com.productmanagement.productservice.service;

import com.productmanagement.productservice.entity.Product;
import com.productmanagement.productservice.exception.customExceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> findAll() throws ProductNotFoundException;
    Product findById(int id) throws ProductNotFoundException;
    Product save(Product product);
}
