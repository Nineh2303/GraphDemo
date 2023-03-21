package com.example.demo.repo;

import com.example.demo.Entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Integer> {
}
