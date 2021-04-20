package com.example.SampleProject.service;
import com.example.SampleProject.model.Product;

public interface ProductRestService {
    public Product add(Product product);
    public Product get(int id);
}
