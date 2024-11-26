package com.aditya.testing.databases.service;

import com.aditya.testing.databases.entity.Product;
import com.aditya.testing.databases.repo.ProductRepository;
import com.aditya.testing.databases.utility.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id){
        return productRepository.findById(id);
    }

    public void createProduct(Product p){
        p.setProductId(IdGenerator.generateProductId());
        productRepository.save(p);
    }

    public List<Product> addMoreProduct(List<Product> prod){
        for (Product products : prod){
            products.setProductId(IdGenerator.generateProductId());
        }
        return productRepository.saveAll(prod);
    }

    public void deleteProduct(String prodId){
        productRepository.deleteById(prodId);
    }

}
