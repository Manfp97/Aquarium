package com.example.aquarium.Service;

import com.example.aquarium.Entities.Product;
import com.example.aquarium.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServi extends AbstractBusinessService<Product,Integer, ProductRepository> {
    protected ProductServi(ProductRepository productRepository) {super (productRepository);}
}
