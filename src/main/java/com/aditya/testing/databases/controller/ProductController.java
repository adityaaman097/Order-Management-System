package com.aditya.testing.databases.controller;

import com.aditya.testing.databases.entity.Product;
import com.aditya.testing.databases.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    private String newProduct(@RequestBody Product p){
        productService.createProduct(p);
        return "Product Created Successfully";
    }

    @GetMapping("/product")
    private List<Product> getProduct(){
        return productService.getAllProduct();
    }

    @PostMapping("products")
    private List<Product> createProductInBulk(@RequestBody List<Product> prod){
        return productService.addMoreProduct(prod);
    }

    @DeleteMapping("/product/{id}")
    private String deleteProductById(@PathVariable String id){
        productService.deleteProduct(id);
        return "Product with id : "+id+ "deleted successfully";
    }
}
