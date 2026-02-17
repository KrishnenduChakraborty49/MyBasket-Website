package com.jpa.concepts.jpa_concepts.service;

import com.jpa.concepts.jpa_concepts.DTO.PageResponse;
import com.jpa.concepts.jpa_concepts.DTO.ProductDto;
import com.jpa.concepts.jpa_concepts.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    ProductDto createProduct(ProductDto product);
    Product updateProduct(Long productId,Product product);
    PageResponse<Product> getAll(int page, int size, String sortBy, String sortDir);
    Product get(Long productId);
    void delete(Long productId);
}
