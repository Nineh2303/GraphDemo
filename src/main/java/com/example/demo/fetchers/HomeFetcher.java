package com.example.demo.fetchers;

import com.example.demo.Entities.Product;
import com.example.demo.repo.ProductRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.List;

@Component
public class HomeFetcher {
    @Autowired
    ProductRepo productRepo;
    public DataFetcher<String> getStringFetcher(){
        return new DataFetcher<String>() {
            @Override
            public String get(DataFetchingEnvironment environment) throws Exception {
                return "Hello World" ;
            }
        };
    }
    public DataFetcher<List<Product>> getAllProducts() {
        return new DataFetcher<List<Product>>() {
            @Override
            public List<Product> get(DataFetchingEnvironment environment) throws Exception {
                List<Product> products = (List<Product>) productRepo.findAll();
                return products ;
            }
        };
    }
}
