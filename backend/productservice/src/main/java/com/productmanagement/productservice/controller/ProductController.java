package com.productmanagement.productservice.controller;

import com.productmanagement.productservice.entity.Product;
import com.productmanagement.productservice.exception.customExceptions.ProductNotFoundException;
import com.productmanagement.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController @RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() throws ProductNotFoundException {
        log.debug("getAll called");
        return ResponseEntity.ok(
//                new ArrayList<>()
                productService.findAll()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable int id
    ) throws ProductNotFoundException {
        log.debug("getProductById called with id: {}", id);
        return ResponseEntity.ok(
//                new Product()
                productService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(Product product){
        log.debug("saveProduct called with Product: {}", product);
        return ResponseEntity.ok(
//                new Product()
                productService.save(product)
        );
    }
}
