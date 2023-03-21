package com.example.demo.Controllers;

import com.example.demo.Entities.Product;
import com.example.demo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductRepo productRepo;

}
